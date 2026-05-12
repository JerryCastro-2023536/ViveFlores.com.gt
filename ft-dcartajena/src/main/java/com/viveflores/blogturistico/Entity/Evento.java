package com.viveflores.blogturistico.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table (name = "Eventos")
public class Evento {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_evento")
    private Integer id_evento;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column (name = "nombre_evento")
    private String nombre_evento;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column (name = "descripcion")
    private String descripcion;

    @Lob
    @Column (name = "foto")
    private byte[] foto;

    @NotNull(message = "El campo no debe ir vacio")
    @Column (name = "fecha_inicio")
    private LocalDateTime fecha_inicio;

    @NotNull(message = "El campo no debe ir vacio")
    @Column (name = "fecha_fin")
    private LocalDateTime fecha_fin;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column (name = "ubicacion")
    private String ubicacion;

    @NotNull(message = "El campo no debe ir vacio")
    @Column (name = "id_usuario")
    private Integer id_usuario;

    public Integer getId_evento() {
        return id_evento;
    }

    public void setId_evento(Integer id_evento) {
        this.id_evento = id_evento;
    }

    public String getNombre_evento() {
        return nombre_evento;
    }

    public void setNombre_evento(String nombre_evento) {
        this.nombre_evento = nombre_evento;
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

    public LocalDateTime getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDateTime fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDateTime getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDateTime fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}