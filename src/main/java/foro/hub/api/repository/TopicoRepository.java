package foro.hub.api.repository;

import foro.hub.api.domain.topico.Curso;
import foro.hub.api.domain.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTitulo(String titulo);

    Page<Topico> findAll(Pageable paginacion);

    @Query("""
        SELECT t FROM Topico t 
        WHERE t.curso = :curso 
        AND t.fechaCreacion BETWEEN :fechaInicio AND :fechaFin
        """)
    Page<Topico> findByCursoAndFechaCreacionBetween(
            Curso curso,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            Pageable paginacion
    );
}