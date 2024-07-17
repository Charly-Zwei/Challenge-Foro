package charly.foro.api.controllers;

import charly.foro.api.dto.respuesta.*;
import charly.foro.api.repositorio.RespuestaRepositorio;
import charly.foro.api.repositorio.TopicoRepositorio;
import charly.foro.api.repositorio.UsuarioRepositorio;
import charly.foro.api.modelo.arreglo.Estado;
import charly.foro.api.modelo.Respuesta;
import charly.foro.api.modelo.Topico;
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
@RequestMapping("/respuestas")
public class RespuestaController {

    private final RespuestaRepositorio respuestaRepositorio;
    private final TopicoRepositorio topicoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    public RespuestaController(RespuestaRepositorio respuestaRepositorio, TopicoRepositorio topicoRepositorio, UsuarioRepositorio usuarioRepositorio) {
        this.respuestaRepositorio = respuestaRepositorio;
        this.topicoRepositorio = topicoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @PostMapping
    public ResponseEntity<DatosRetornoRespuesta> registrar(@RequestBody DatosRegistroRespuesta datosRegistro, UriComponentsBuilder uri) {
        Topico topico = topicoRepositorio.getReferenceById(datosRegistro.topicoId());
        if (topico.getEstado() == Estado.CERRADO) {
           return ResponseEntity.unprocessableEntity().build();
        }

        Usuario autor = usuarioRepositorio.getReferenceById(datosRegistro.autorId());
        Respuesta respuesta = respuestaRepositorio.save(new Respuesta(datosRegistro, topico, autor));
        topico.agregarRespuesta(respuesta);
        DatosRetornoRespuesta datosRespuesta = new DatosRetornoRespuesta(respuesta);
        URI url = uri.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoRespuesta>> listar(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepositorio.findAll(paginacion).map(DatosListadoRespuesta::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRetornoRespuestaId> retornaDatos(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepositorio.getReferenceById(id);
        return ResponseEntity.ok(new DatosRetornoRespuestaId(respuesta));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRetornoRespuesta> actualizar(@RequestBody DatosActualizarRespuesta datosActualizar) {
        Respuesta respuesta = respuestaRepositorio.getReferenceById(datosActualizar.id());
        Topico topico = topicoRepositorio.getReferenceById(datosActualizar.topicoId());
        Usuario autor = usuarioRepositorio.getReferenceById(datosActualizar.autorId());
        respuesta.actualizacionDeDatos(datosActualizar, topico, autor);
        return ResponseEntity.ok( new DatosRetornoRespuesta(respuesta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepositorio.getReferenceById(id);
        respuestaRepositorio.delete(respuesta);
        return ResponseEntity.noContent().build();
    }
}
