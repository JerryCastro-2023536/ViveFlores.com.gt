package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.SolicitudPublicacion;
import com.viveflores.blogturistico.Exception.FechasValidar;
import com.viveflores.blogturistico.Exception.NotFoundExcepcion;
import com.viveflores.blogturistico.Repository.SolicitudPublicacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudPublicacionServiceImplements implements SolicitudPublicacionService{
    private final SolicitudPublicacionRepository SolicitudPublicacionRepository;
    FechasValidar fv = new FechasValidar();

    public SolicitudPublicacionServiceImplements(SolicitudPublicacionRepository solicitudPublicacionRepository) {
        SolicitudPublicacionRepository = solicitudPublicacionRepository;
    }


    @Override
    public List<SolicitudPublicacion> getAllSolicitud() {return SolicitudPublicacionRepository.findAll();}

    @Override
    public SolicitudPublicacion getSolicitudById(Integer id) { return SolicitudPublicacionRepository.findById(id).orElseThrow(() ->
            new NotFoundExcepcion("El id no existe"));}

    @Override
    public SolicitudPublicacion saveSolicitud(SolicitudPublicacion solicitudPublicacion) throws RuntimeException {
        fv.validarLocalDate(solicitudPublicacion.getFecha_solicitud());
        return SolicitudPublicacionRepository.save(solicitudPublicacion);
    }

    @Override
    public SolicitudPublicacion updateSolicitud(Integer id, SolicitudPublicacion SolicitudPublicacion) {
        return SolicitudPublicacionRepository.save(SolicitudPublicacion);
    }

    @Override
    public void deleteSolicitud(Integer id) {
        SolicitudPublicacionRepository.deleteById(id);
    }


}

