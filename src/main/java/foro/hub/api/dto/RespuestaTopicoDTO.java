package foro.hub.api.dto;

import foro.hub.api.domain.topico.Curso;
import foro.hub.api.domain.topico.StatusTopico;
import foro.hub.api.domain.topico.Topico;

import java.time.LocalDateTime;

public record RespuestaTopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        String autor,
        Curso curso
) {
    public RespuestaTopicoDTO(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso()
        );
    }
}
