package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Reportes;
import com.viveflores.blogturistico.Exception.FechasValidar;
import com.viveflores.blogturistico.Exception.NotFoundExcepcion;
import com.viveflores.blogturistico.Repository.ReportesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportesServiceImplements implements ReportesService{
    @Autowired
    private ReportesRepository repo;

    @Override
    public List<Reportes> listarReportes() {
        return List.of();
    }

    @Override
    public Reportes agregarReportes(Reportes reportes) {
        return repo.save(reportes);
    }

    @Override
    public Reportes BuscarPorId(int id) {
        return null;
    }

    @Override
    public void eliminarReportes(int id) {

    }
}
