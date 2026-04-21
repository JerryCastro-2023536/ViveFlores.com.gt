package com.viveflores.blogturistico.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table (name = "Servicios")
public class Servicio {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_servicio")
    private Integer id_servicio;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column (name = "nombre_servicio")
    private String nombre_servicio;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column (name = "descripcion")
    private String descripcion;

    @NotNull(message = "El campo no debe ir vacio")
    @Column (name = "telefono")
    private Integer telefono;

    @Lob
    @Column (name = "foto")
    private byte[] foto;

    @NotNull(message = "El campo no debe ir vacio")
    @Column (name = "fecha_creacion")
    private LocalDate fecha_creacion;

    @NotNull(message = "El campo no debe ir vacio")
    @Column (name = "id_usuario")
    private Integer id_usuario;

    public Integer getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(Integer id_servicio) {
        this.id_servicio = id_servicio;
    }

    public String getNombre_servicio() {
        return nombre_servicio;
    }

    public void setNombre_servicio(String nombre_servicio) {
        this.nombre_servicio = nombre_servicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
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
