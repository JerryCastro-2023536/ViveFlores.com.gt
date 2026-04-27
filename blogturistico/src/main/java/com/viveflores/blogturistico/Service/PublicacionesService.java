package com.viveflores.blogturistico.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.viveflores.blogturistico.Entity.Publicaciones;

@Service
public interface PublicacionesService {
    List<Publicaciones> listar();
    Publicaciones guardar(Publicaciones publicaciones);
    Publicaciones buscarporId(int id);
    void eliminar(int id);
}
