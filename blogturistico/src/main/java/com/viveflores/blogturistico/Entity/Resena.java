package com.viveflores.blogturistico.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table (name = "Resenas")
public class Resena {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_resena")
    private Integer id_resena;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column (name = "titulo_resena")
    private String titulo_resena;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column (name = "comentario")
    private String comentario;

    @NotNull(message = "El campo no debe ir vacio")
    @Column (name = "calificacion")
    private Integer calificacion;

    @NotNull(message = "El campo no debe ir vacio")
    @Column (name = "fecha_creacion")
    private LocalDate fecha_creacion;

    @NotNull(message = "El campo no debe ir vacio")
    @Column (name = "id_usuario")
    private Integer id_usuario;

    @NotNull(message = "El campo no debe ir vacio")
    @Column (name = "id_publicacion")
    private Integer id_publicacion;

    public Integer getId_resena() {
        return id_resena;
    }

    public void setId_resena(Integer id_resena) {
        this.id_resena = id_resena;
    }

    public String getTitulo_resena() {
        return titulo_resena;
    }

    public void setTitulo_resena(String titulo_resena) {
        this.titulo_resena = titulo_resena;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(Integer id_publicacion) {
        this.id_publicacion = id_publicacion;
    }
}
