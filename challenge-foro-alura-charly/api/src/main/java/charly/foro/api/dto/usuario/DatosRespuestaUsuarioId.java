package charly.foro.api.dto.usuario;

import charly.foro.api.modelo.Usuario;

public record DatosRespuestaUsuarioId(String nombre, String email, String contrasena, String tipo) {

    public DatosRespuestaUsuarioId(Usuario usuario) {
        this(usuario.getNombre(), usuario.getEmail(), usuario.getPassword(), usuario.getRoles().toString());
    }
}
