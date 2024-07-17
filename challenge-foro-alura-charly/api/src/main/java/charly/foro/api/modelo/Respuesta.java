package charly.foro.api.modelo;

import charly.foro.api.dto.respuesta.DatosActualizarRespuesta;
import charly.foro.api.dto.respuesta.DatosRegistroRespuesta;
import charly.foro.api.modelo.arreglo.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    private Boolean solucion = false;

    public Respuesta(DatosRegistroRespuesta datos, Topico topico, Usuario autor) {
        this.mensaje = datos.mensaje();
        this.topico = topico;
        this.autor = autor;
        this.solucion = datos.solucion();
        if (datos.solucion()) {
            this.topico.setEstado(Estado.SOLUCIONADO);
        } else {
            this.topico.setEstado(Estado.NO_SOLUCIONADO);
        }
    }

    //Actualizacion de datos
    public void actualizacionDeDatos(DatosActualizarRespuesta datosPorActualizar, Topico topico, Usuario user) {
        if (datosPorActualizar.mensaje() != null) {
            this.mensaje = datosPorActualizar.mensaje();
        }
        if (topico != null) {
            this.topico = topico;
        }
        if (user != null) {
            this.autor = user;
        }
        if (datosPorActualizar.solucion() != this.solucion) {
            this.solucion = datosPorActualizar.solucion();
        }
    }
}
