package com.viveflores.blogturistico.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.viveflores.blogturistico.Entity.servicio;

@Service
public interface ServicioService {
    List<servicio> listar();
    servicio agregarServicio(servicio servicio);
    servicio buscarPorId(int id);
    void eliminar(int id);

}
