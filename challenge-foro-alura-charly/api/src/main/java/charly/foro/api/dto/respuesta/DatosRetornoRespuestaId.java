package charly.foro.api.dto.respuesta;

import charly.foro.api.modelo.Respuesta;
import charly.foro.api.dto.topico.DatosRespuestaTopico;
import charly.foro.api.dto.usuario.DatosRespuestaUsuario;

public record DatosRetornoRespuestaId(String mensaje, DatosRespuestaTopico topico, String fechaCreacion, DatosRespuestaUsuario autor, String solucion) {

    public DatosRetornoRespuestaId(Respuesta respuesta) {
        this(respuesta.getMensaje(), new DatosRespuestaTopico(respuesta.getTopico()), respuesta.getFechaCreacion().toString(),
                new DatosRespuestaUsuario(respuesta.getAutor()), respuesta.getSolucion().toString());
    }
}
