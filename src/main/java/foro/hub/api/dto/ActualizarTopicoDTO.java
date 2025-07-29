package foro.hub.api.dto;

import foro.hub.api.domain.topico.Curso;
import foro.hub.api.domain.topico.StatusTopico;

public record ActualizarTopicoDTO(
        String titulo,
        String mensaje,
        StatusTopico status,
        String autor,
        Curso curso
) {}
