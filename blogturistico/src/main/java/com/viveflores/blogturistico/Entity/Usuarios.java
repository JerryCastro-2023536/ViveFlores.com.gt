package com.viveflores.blogturistico.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_usuario")
    private Integer id_usuario;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "nombre_usuario")
    private String nombre_usuario;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "apellido_usuario")
    private String apellido_usuario;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "email_usuario")
    private String email_usuario;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "contrasena_usuario")
    private String contrasena_usuario;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "rol")
    private String rol;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "fecha_registro")
    private LocalDate fecha_registro;

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getApellido_usuario() {
        return apellido_usuario;
    }

    public void setApellido_usuario(String apellido_usuario) {
        this.apellido_usuario = apellido_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getContrasena_usuario() {
        return contrasena_usuario;
    }

    public void setContrasena_usuario(String contrasena_usuario) {
        this.contrasena_usuario = contrasena_usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public LocalDate getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDate fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}