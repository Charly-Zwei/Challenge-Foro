package charly.foro.api.repositorio;

import charly.foro.api.modelo.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespuestaRepositorio extends JpaRepository<Respuesta, Long> {
}
