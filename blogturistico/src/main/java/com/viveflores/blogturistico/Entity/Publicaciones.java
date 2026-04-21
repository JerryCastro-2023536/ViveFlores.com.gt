package com.viveflores.blogturistico.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "publicaciones")
public class Publicaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publicacion")
    private Integer id_publicacion;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "nombre_publicacion")
    private String nombre_publicacion;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "descripcion")
    private String descripcion;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "direccion")
    private String direccion;

    @Lob
    @Column(name = "foto", columnDefinition = "blob")
    private byte[] foto;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "telefono")
    private Integer telefono;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "email_publicacion")
    private String email_publicacion;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "horario")
    private String horario;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "fecha_creacion")
    private LocalDate fecha_creacion;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "estado_publicacion")
    private String estado_publicacion;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "id_categoria")
    private Integer id_categoria;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "id_usuario")
    private Integer id_usuario;

    public Integer getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(Integer id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public String getNombre_publicacion() {
        return nombre_publicacion;
    }

    public void setNombre_publicacion(String nombre_publicacion) {
        this.nombre_publicacion = nombre_publicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getEmail_publicacion() {
        return email_publicacion;
    }

    public void setEmail_publicacion(String email_publicacion) {
        this.email_publicacion = email_publicacion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getEstado_publicacion() {
        return estado_publicacion;
    }

    public void setEstado_publicacion(String estado_publicacion) {
        this.estado_publicacion = estado_publicacion;
    }

    public Integer getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}
