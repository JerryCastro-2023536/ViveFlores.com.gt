package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.SolicitudVendedor;
import java.util.List;

public interface SolicitudVendedorService {
    List<SolicitudVendedor> listar();
    SolicitudVendedor guardar(SolicitudVendedor solicitudVendedor);
    SolicitudVendedor buscarPorId(int id);
    SolicitudVendedor buscarPorIdUsuario(int idUsuario);
    void eliminar(int id);
}
