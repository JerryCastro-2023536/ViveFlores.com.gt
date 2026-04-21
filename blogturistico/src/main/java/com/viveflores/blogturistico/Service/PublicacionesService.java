package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Publicaciones;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PublicacionesService {
    List<Publicaciones> getAllPublicaciones();
    Publicaciones getPublicacionesById(Integer id);
    Publicaciones savePublicaciones(Publicaciones publicaciones);
    Publicaciones updatePublicaciones(Integer id, Publicaciones publicaciones);
    void deletePublicaciones(Integer id);
}
