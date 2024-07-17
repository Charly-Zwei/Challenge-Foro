package charly.foro.api.modelo;

import charly.foro.api.dto.usuario.DatosActualizarUsuario;
import charly.foro.api.dto.usuario.DatosRegistroUsuario;
import charly.foro.api.modelo.arreglo.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles roles = Roles.ROLE_USER;

    public Usuario(DatosRegistroUsuario datos) {
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.password = datos.contrasena();
        if (datos.roles() != this.roles) {
            this.roles = datos.roles();
        }
    }

    public void actualizacionDeDatos(DatosActualizarUsuario datosPorActualizar) {
        if (datosPorActualizar.nombre() != null) {
            this.nombre = datosPorActualizar.nombre();
        }
        if (datosPorActualizar.email() != null) {
            this.email = datosPorActualizar.email();
        }
        if (datosPorActualizar.contrasena() != null) {
            this.password = datosPorActualizar.contrasena();
        }
        if (datosPorActualizar.roles() != datosPorActualizar.roles()) {
            this.roles = datosPorActualizar.roles();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.toString()));
    }

    //No ñ por si las dudas
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
