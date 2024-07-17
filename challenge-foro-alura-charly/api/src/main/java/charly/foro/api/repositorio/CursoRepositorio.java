package charly.foro.api.repositorio;

import charly.foro.api.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepositorio extends JpaRepository<Curso, Long> {
}
