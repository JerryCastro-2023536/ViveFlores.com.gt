package com.viveflores.blogturistico.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
@Entity
@Table(name="solicitud_publicacion")
public class SolicitudPublicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_solicitud")
    private Integer id_solicitud;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "fecha_solicitud")
    private LocalDate fecha_solicitud;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "estado")
    private String estado;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "descripcion")
    private String descripcion;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "id_publicacion")
    private Integer id_publicacion;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "id_usuario")
    private Integer id_usuario;

    public Integer getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(Integer id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public LocalDate getFecha_solicitud() {
        return fecha_solicitud;
    }

    public void setFecha_solicitud(LocalDate fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(Integer id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}