package foro.hub.api.controller;

import foro.hub.api.domain.topico.Curso;
import foro.hub.api.dto.ActualizarTopicoDTO;
import foro.hub.api.dto.RegistrarTopicoDTO;
import foro.hub.api.dto.RespuestaTopicoDTO;
import foro.hub.api.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;

    @Autowired
    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    // Registro de nuevo tópico (POST)
    @PostMapping
    @Transactional
    public ResponseEntity<RespuestaTopicoDTO> registrar(
            @RequestBody @Valid RegistrarTopicoDTO datos,
            UriComponentsBuilder uriBuilder
    ) {
        RespuestaTopicoDTO respuesta = topicoService.crearTopico(datos);
        URI url = uriBuilder.path("/topicos/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    // Listado de tópicos con paginación y ordenamiento (GET)
    @GetMapping
    public ResponseEntity<Page<RespuestaTopicoDTO>> listar(
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion
    ) {
        Page<RespuestaTopicoDTO> pagina = topicoService.listarTopicos(paginacion);
        return ResponseEntity.ok(pagina);
    }

    // Listado opcional por curso y año (GET)
    @GetMapping("/buscar")
    public ResponseEntity<Page<RespuestaTopicoDTO>> listarPorCursoYAnio(
            @RequestParam Curso curso,
            @RequestParam int anio,
            @PageableDefault(size = 10) Pageable paginacion
    ) {
        Page<RespuestaTopicoDTO> pagina = topicoService.listarPorCursoYAnio(curso, anio, paginacion);
        return ResponseEntity.ok(pagina);
    }

    // Detalle de tópico específico (GET)
    @GetMapping("/{id}")
    public ResponseEntity<RespuestaTopicoDTO> detalle(@PathVariable Long id) {
        RespuestaTopicoDTO topico = topicoService.obtenerTopico(id);
        return ResponseEntity.ok(topico);
    }

    // Actualización de tópico (PUT)
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<RespuestaTopicoDTO> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid ActualizarTopicoDTO datos
    ) {
        RespuestaTopicoDTO respuesta = topicoService.actualizarTopico(id, datos);
        return ResponseEntity.ok(respuesta);
    }

    // Eliminación de tópico (DELETE)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
