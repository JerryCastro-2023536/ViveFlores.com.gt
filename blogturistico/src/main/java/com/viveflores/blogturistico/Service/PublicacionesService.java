package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Publicaciones;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PublicacionesService {
    List<Publicaciones> getAllPublicaciones();
    List<Publicaciones> getPublicacionesByUsuarioId(Integer idUsuario);
    Publicaciones getPublicacionesById(Integer id);
    void savePublicaciones(Publicaciones publicaciones);
    void updatePublicaciones(Integer id, Publicaciones publicaciones);
    void deletePublicaciones(Integer id);
}
