package charly.foro.api.repositorio;

import charly.foro.api.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String username);
}
