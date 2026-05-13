package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Reportes;
import com.viveflores.blogturistico.Repository.ReportesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportesServiceImplements implements ReportesService {

    @Autowired
    private ReportesRepository repo;

    @Override
    public List<Reportes> listarReportes() {
        return (List<Reportes>) repo.findAll();
    }

    @Override
    public Reportes agregarReportes(Reportes reportes) {
        return repo.save(reportes);
    }

    @Override
    public Reportes BuscarPorId(int id) {
        Optional<Reportes> reporte = repo.findById(id);
        return reporte.orElse(null);
    }

    @Override
    public void eliminarReportes(int id) {
        repo.deleteById(id);
    }
}