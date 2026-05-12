package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Servicio;
import com.viveflores.blogturistico.Exception.FechasValidar;
import com.viveflores.blogturistico.Exception.NotFoundExcepcion;
import com.viveflores.blogturistico.Repository.ServicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioServiceImplements implements ServicioService{
    private final ServicioRepository servicioRepository;
    FechasValidar fv = new FechasValidar();

    public ServicioServiceImplements (ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public List<Servicio> getAllServicios() {
        return servicioRepository.findAll();
    }

    @Override
    public Servicio getServicioById(Integer id) {
        return servicioRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));
    }

    @Override
    public Servicio saveServicio(Servicio servicio) throws RuntimeException {
        fv.validarLocalDate(servicio.getFecha_creacion());
        return servicioRepository.save(servicio);
    }

    @Override
    public void deleteServicio(Integer id) {
        if (!servicioRepository.existsById(id)) {
            throw new NotFoundExcepcion("El id no existe");
        }
        servicioRepository.deleteById(id);
    }

    @Override
    public Servicio updateServicio(Integer id, Servicio servicio){
        Servicio existingServicio = servicioRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));

        existingServicio.setNombre_servicio(servicio.getNombre_servicio());
        existingServicio.setDescripcion(servicio.getDescripcion());
        existingServicio.setTelefono(servicio.getTelefono());
        existingServicio.setFoto(servicio.getFoto());
        existingServicio.setFecha_creacion(servicio.getFecha_creacion());
        existingServicio.setId_usuario(servicio.getId_usuario());
        fv.validarLocalDate(existingServicio.getFecha_creacion());

        return servicioRepository.save(existingServicio);

    }
}
