package charly.foro.api.dto.usuario;

import charly.foro.api.modelo.Usuario;

public record DatosListadoUsuario(Long id, String nombre, String email, String tipo) {

    public DatosListadoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getRoles().toString());
    }
}
