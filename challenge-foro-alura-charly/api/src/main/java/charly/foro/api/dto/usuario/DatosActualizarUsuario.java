package charly.foro.api.dto.usuario;

import charly.foro.api.modelo.arreglo.Roles;

public record DatosActualizarUsuario(Long id, String nombre, String email, String contrasena, Roles roles) {
}
