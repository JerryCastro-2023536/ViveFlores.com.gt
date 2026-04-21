package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.SolicitudPublicacion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SolicitudPublicacionService {

    List<SolicitudPublicacion> getAllSolicitud();
    SolicitudPublicacion getSolicitudById(Integer id);
    SolicitudPublicacion saveSolicitud(SolicitudPublicacion solicitudPublicacion) throws RuntimeException;
    SolicitudPublicacion updateSolicitud(Integer id,SolicitudPublicacion SolicitudPublicacion);
    void deleteSolicitud(Integer id);
}
