package com.viveflores.blogturistico.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "favoritos")
public class Favoritos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_favorito")
    private Integer id_favorito;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "id_usuario")
    private Integer id_usuario;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "id_publicacion")
    private Integer id_publicacion;

    @NotNull(message = "El campo no de ir vacio")
    @Column(name = "id_categoria")
    private Integer id_categoria;

    public Integer getId_favorito() {
        return id_favorito;
    }

    public void setId_favorito(Integer id_favorito) {
        this.id_favorito = id_favorito;
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

    public Integer getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }
}
