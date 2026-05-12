package com.viveflores.blogturistico.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "Contactar")
public class Contactar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contactar")
    private Integer id_contactar;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "asunto")
    private String asunto;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "mensaje")
    private String mensaje;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "fecha_envio")
    private LocalDateTime fecha_envio;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "id_usuario")
    private Integer id_usuario;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "id_publicacion")
    private Integer id_publicacion;

    public Integer getId_contactar() {
        return id_contactar;
    }

    public void setId_contactar(Integer id_contactar) {
        this.id_contactar = id_contactar;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha_envio() {
        return fecha_envio;
    }

    public void setFecha_envio(LocalDateTime fecha_envio) {
        this.fecha_envio = fecha_envio;
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
