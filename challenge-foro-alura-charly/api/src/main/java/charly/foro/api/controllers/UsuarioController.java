package charly.foro.api.controllers;

import charly.foro.api.dto.usuario.*;
import charly.foro.api.repositorio.UsuarioRepositorio;
import com.alura.foro.api.dto.usuario.*;
import charly.foro.api.modelo.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioController(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrar(@RequestBody DatosRegistroUsuario datosRegistro, UriComponentsBuilder uri) {
        Usuario usuario = usuarioRepositorio.save(new Usuario(datosRegistro));
        DatosRespuestaUsuario datosRespuesta = new DatosRespuestaUsuario(usuario);
        URI url = uri.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> listar(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepositorio.findAll(paginacion).map(DatosListadoUsuario::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuarioId> retornaDatos(@PathVariable Long id) {
        Usuario usuario = usuarioRepositorio.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaUsuarioId(usuario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> actualizar(@RequestBody DatosActualizarUsuario datosActualizar) {
        Usuario usuario = usuarioRepositorio.getReferenceById(datosActualizar.id());
        usuario.actualizacionDeDatos(datosActualizar);
        return ResponseEntity.ok( new DatosRespuestaUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Usuario usuario = usuarioRepositorio.getReferenceById(id);
        usuarioRepositorio.delete(usuario);
        return ResponseEntity.noContent().build();
    }
}
