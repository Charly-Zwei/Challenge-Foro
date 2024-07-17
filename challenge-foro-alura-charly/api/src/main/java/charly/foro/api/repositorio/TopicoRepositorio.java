package charly.foro.api.repositorio;

import charly.foro.api.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepositorio extends JpaRepository<Topico, Long> {
}
