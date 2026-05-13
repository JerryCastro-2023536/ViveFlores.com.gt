package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Reportes;
import java.util.List;

public interface ReportesService {
    List<Reportes> listarReportes();
    Reportes agregarReportes(Reportes reportes);
    Reportes BuscarPorId(int id);
    void eliminarReportes(int id);
}