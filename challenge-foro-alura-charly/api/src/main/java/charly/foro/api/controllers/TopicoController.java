package charly.foro.api.controllers;

import charly.foro.api.dto.topico.*;
import charly.foro.api.repositorio.TopicoRepositorio;
import charly.foro.api.repositorio.UsuarioRepositorio;
import charly.foro.api.modelo.Curso;
import charly.foro.api.modelo.Topico;
import charly.foro.api.modelo.Usuario;
import charly.foro.api.repositorio.CursoRepositorio;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepositorio topicoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final CursoRepositorio cursoRepositorio;

    public TopicoController(TopicoRepositorio topicoRepositorio, UsuarioRepositorio usuarioRepositorio, CursoRepositorio cursoRepositorio) {
        this.topicoRepositorio = topicoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.cursoRepositorio = cursoRepositorio;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrar(@RequestBody @Valid DatosRegistroTopico datosRegistro, UriComponentsBuilder uri) {
        Usuario autor = usuarioRepositorio.getReferenceById(datosRegistro.autorId());
        Curso curso = cursoRepositorio.getReferenceById(datosRegistro.cursoId());
        Topico topico = topicoRepositorio.save(new Topico(datosRegistro, autor, curso));
        DatosRespuestaTopico datosRespuesta = new DatosRespuestaTopico(topico);
        URI url = uri.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listar(@PageableDefault(size = 10)Pageable paginacion) {
        return ResponseEntity.ok(topicoRepositorio.findAll(paginacion).map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopicoId> retornaDatos(@PathVariable Long id) {
        Topico topico = topicoRepositorio.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaTopicoId(topico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizar(@RequestBody @Valid DatosActualizarTopico datosActualizar) {
        Usuario autor = usuarioRepositorio.getReferenceById(datosActualizar.autorId());
        Curso curso = cursoRepositorio.getReferenceById(datosActualizar.cursoId());
        Topico topico = topicoRepositorio.getReferenceById(datosActualizar.id());
        topico.actualizacionDeDatos(datosActualizar, autor, curso);
        return ResponseEntity.ok( new DatosRespuestaTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Topico topico = topicoRepositorio.getReferenceById(id);
        //topicoRepository.delete(topico);
        topico.cerrarTopico();
        return ResponseEntity.noContent().build();
    }
}
