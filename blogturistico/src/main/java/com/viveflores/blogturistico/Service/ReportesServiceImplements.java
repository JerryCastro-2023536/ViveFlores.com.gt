package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Reportes;
import com.viveflores.blogturistico.Exception.FechasValidar;
import com.viveflores.blogturistico.Exception.NotFoundExcepcion;
import com.viveflores.blogturistico.Repository.ReportesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportesServiceImplements implements ReportesService{

    private final ReportesRepository reportesRepository;
    FechasValidar fv = new FechasValidar();

    public ReportesServiceImplements(ReportesRepository reportesRepository) {
        this.reportesRepository = reportesRepository;
    }

    @Override
    public List<Reportes> getAllReportes() {
        return reportesRepository.findAll();
    }

    @Override
    public Reportes getReporteById(Integer id) {
        return reportesRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));
    }

    @Override
    public Reportes saveReportes(Reportes reportes) {
        fv.validarLocalDateTime(reportes.getFecha_envio());
        return reportesRepository.save(reportes);
    }

    @Override
    public Reportes updateReportes(Integer id, Reportes reportes) {
        Reportes reportes1 = reportesRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));;

        reportes1.setAsunto(reportes.getAsunto());
        reportes1.setMensaje(reportes.getMensaje());
        reportes1.setFecha_envio(reportes.getFecha_envio());
        reportes1.setId_usuario(reportes.getId_usuario());
        fv.validarLocalDateTime(reportes1.getFecha_envio());

        return reportesRepository.save(reportes1);
    }

    @Override
    public void deleteReportes(Integer id) {
        Reportes reportes = reportesRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));;
        reportesRepository.delete(reportes);
    }
}
