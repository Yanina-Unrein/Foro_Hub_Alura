package foro.hub.api.dto;

import foro.hub.api.domain.topico.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegistrarTopicoDTO(
        @NotBlank(message = "El título es obligatorio")
        String titulo,

        @NotBlank(message = "El mensaje es obligatorio")
        @Size(min = 10, message = "El mensaje debe tener al menos 10 caracteres")
        String mensaje,

        @NotBlank(message = "El autor es obligatorio")
        String autor,

        @NotNull(message = "El curso es obligatorio")
        Curso curso
) {}