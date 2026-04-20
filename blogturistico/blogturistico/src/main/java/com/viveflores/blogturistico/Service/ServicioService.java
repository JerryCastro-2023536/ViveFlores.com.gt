package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Servicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicioService {
    List<Servicio> getAllServicios();
    Servicio getServicioById (Integer id);
    Servicio saveServicio (Servicio servicio) throws RuntimeException;
    Servicio updateServicio (Integer id, Servicio servicio);
    void deleteServicio (Integer id);
}
