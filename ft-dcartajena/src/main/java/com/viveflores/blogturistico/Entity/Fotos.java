package com.viveflores.blogturistico.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "Fotos")
public class Fotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_foto")
    private Integer id_foto;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "titulo_foto")
    private String titulo_foto;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "descripcion")
    private String descripcion;

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "fecha_creacion")
    private LocalDate fecha_creacion;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "id_usuario")
    private Integer id_usuario;

    public Integer getId_foto() {
        return id_foto;
    }

    public void setId_foto(Integer id_foto) {
        this.id_foto = id_foto;
    }

    public String getTitulo_foto() {
        return titulo_foto;
    }

    public void setTitulo_foto(String titulo_foto) {
        this.titulo_foto = titulo_foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
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
}