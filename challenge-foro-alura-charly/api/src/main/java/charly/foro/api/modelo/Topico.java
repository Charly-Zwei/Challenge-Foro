package charly.foro.api.modelo;

import charly.foro.api.dto.topico.DatosActualizarTopico;
import charly.foro.api.dto.topico.DatosRegistroTopico;
import charly.foro.api.modelo.arreglo.Estado;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name= "topicos")
@Entity(name="Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaDeCreacion = LocalDateTime.now();
    @Setter
    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.NO_RESPONDIDO;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "topico_id", referencedColumnName = "id")
    private List<Respuesta> respuestas = new ArrayList<>();

    public Topico(DatosRegistroTopico datos, Usuario autor, Curso curso) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = autor;
        this.curso = curso;
    }

    public void agregarRespuesta(Respuesta respuesta) {
        this.respuestas.add(respuesta);
        if (respuesta.getSolucion()) {
            this.estado = Estado.SOLUCIONADO;
        } else {
            this.estado = Estado.NO_SOLUCIONADO;
        }
    }

    public void cerrarTopico() {
        this.estado = Estado.CERRADO;
    }

    public void actualizacionDeDatos(DatosActualizarTopico datosParaActualizar, Usuario autor, Curso curso) {
        if (datosParaActualizar.titulo() != null) {
            this.titulo = datosParaActualizar.titulo();
        }
        if (datosParaActualizar.mensaje() != null) {
            this.mensaje = datosParaActualizar.mensaje();
        }
        if (datosParaActualizar.estado() != datosParaActualizar.estado()) {
            this.estado = datosParaActualizar.estado();
        }
        if (autor != null) {
            this.autor = autor;
        }
        if (curso != null) {
            this.curso = curso;
        }
    }

}

