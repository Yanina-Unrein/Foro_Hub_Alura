package foro.hub.api.service;

import foro.hub.api.domain.topico.Curso;
import foro.hub.api.domain.topico.Topico;
import foro.hub.api.dto.ActualizarTopicoDTO;
import foro.hub.api.dto.RegistrarTopicoDTO;
import foro.hub.api.dto.RespuestaTopicoDTO;
import foro.hub.api.repository.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Year;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;

    @Autowired
    public TopicoService(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    // Crear nuevo tópico con validaciones
    @Transactional
    public RespuestaTopicoDTO crearTopico(RegistrarTopicoDTO datos) {
        if (topicoRepository.existsByTitulo(datos.titulo())) {
            throw new ValidationException("El título ya existe");
        }

        Topico topico = new Topico();
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setAutor(datos.autor());
        topico.setCurso(datos.curso());

        topicoRepository.save(topico);
        return new RespuestaTopicoDTO(topico);
    }

    // Actualizar tópico con validaciones
    @Transactional
    public RespuestaTopicoDTO actualizarTopico(Long id, ActualizarTopicoDTO datos) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        if (datos.titulo() != null && !datos.titulo().isBlank()) {
            // Validar título único solo si cambió
            if (!datos.titulo().equals(topico.getTitulo()) &&
                    topicoRepository.existsByTitulo(datos.titulo())) {
                throw new ValidationException("El nuevo título ya existe");
            }
            topico.setTitulo(datos.titulo());
        }

        if (datos.mensaje() != null && !datos.mensaje().isBlank()) {
            topico.setMensaje(datos.mensaje());
        }

        if (datos.status() != null) {
            topico.setStatus(datos.status());
        }

        if (datos.autor() != null && !datos.autor().isBlank()) {
            topico.setAutor(datos.autor());
        }

        if (datos.curso() != null) {
            topico.setCurso(datos.curso());
        }

        return new RespuestaTopicoDTO(topico);
    }

    // Listar tópicos con paginación
    @Transactional(readOnly = true)
    public Page<RespuestaTopicoDTO> listarTopicos(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(RespuestaTopicoDTO::new);
    }

    // Listar por curso y año (opcional)
    @Transactional(readOnly = true)
    public Page<RespuestaTopicoDTO> listarPorCursoYAnio(Curso curso, int anio, Pageable paginacion) {
        LocalDateTime inicioAnio = Year.of(anio).atDay(1).atStartOfDay();
        LocalDateTime finAnio = Year.of(anio).atDay(365).atTime(23, 59, 59);

        return topicoRepository.findByCursoAndFechaCreacionBetween(
                curso, inicioAnio, finAnio, paginacion
        ).map(RespuestaTopicoDTO::new);
    }

    // Obtener tópico por ID
    @Transactional(readOnly = true)
    public RespuestaTopicoDTO obtenerTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));
        return new RespuestaTopicoDTO(topico);
    }

    // Eliminar tópico con validación de existencia
    @Transactional
    public void eliminarTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Tópico no encontrado");
        }
        topicoRepository.deleteById(id);
    }
}
