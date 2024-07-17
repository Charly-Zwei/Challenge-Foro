package charly.foro.api.dto.usuario;

import charly.foro.api.modelo.arreglo.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String contrasena,
        @NotNull
        Roles roles) {
}
