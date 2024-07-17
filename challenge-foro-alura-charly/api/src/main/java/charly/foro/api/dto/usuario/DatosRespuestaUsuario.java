package charly.foro.api.dto.usuario;

import charly.foro.api.modelo.Usuario;

public record DatosRespuestaUsuario(String nombre, String email, String tipo) {

    public DatosRespuestaUsuario(Usuario usuario) {
        this(usuario.getNombre(), usuario.getEmail(), usuario.getRoles().toString());
    }
}
