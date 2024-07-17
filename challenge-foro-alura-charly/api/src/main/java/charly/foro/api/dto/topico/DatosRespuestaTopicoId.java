package charly.foro.api.dto.topico;

import charly.foro.api.dto.curso.DatosRespuestaCurso;
import charly.foro.api.dto.usuario.DatosRespuestaUsuario;
import charly.foro.api.modelo.Topico;

public record DatosRespuestaTopicoId(Long id, String titulo, String mensaje, String fechaCreacion, String estado, DatosRespuestaUsuario autor,
                                     DatosRespuestaCurso curso) {

    public DatosRespuestaTopicoId(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaDeCreacion().toString(),
                topico.getEstado().toString(), new DatosRespuestaUsuario(topico.getAutor()),
                new DatosRespuestaCurso(topico.getCurso()));
    }
}
