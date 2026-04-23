package com.viveflores.blogturistico.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.viveflores.blogturistico.Entity.SolicitudPublicacion;

@Service
public interface SolicitudPublicacionService {
    List<SolicitudPublicacion> listar();
    SolicitudPublicacion guardar(SolicitudPublicacion solicitudPublicacion);
    SolicitudPublicacion buscarPorId(int id);
    void eliminar(int id);
}
