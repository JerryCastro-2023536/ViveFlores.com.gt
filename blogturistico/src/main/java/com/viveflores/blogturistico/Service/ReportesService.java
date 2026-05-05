package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Reportes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportesService {
    List<Reportes> listarReportes();
    Reportes agregarReportes(Reportes reportes);
    Reportes BuscarPorId(int id);
    void eliminarReportes(int id);
}
