package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Fotos;
import com.viveflores.blogturistico.Exception.FechasValidar;
import com.viveflores.blogturistico.Exception.NotFoundExcepcion;
import com.viveflores.blogturistico.Repository.FotosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FotosServiceImplements implements FotosService {

    private final FotosRepository fotosRepository;
    FechasValidar fv = new FechasValidar();

    public FotosServiceImplements(FotosRepository fotosRepository) {
        this.fotosRepository = fotosRepository;
    }

    @Override
    public List<Fotos> getAllFotos() {
        return fotosRepository.findAll();
    }

    @Override
    public Fotos getFotoById(Integer id) {
        return fotosRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));
    }

    @Override
    public Fotos saveFotos(Fotos fotos) {
        return fotosRepository.save(fotos);
    }

    @Override
    public Fotos updateFotos(Integer id, Fotos fotos) {
        Fotos existente = fotosRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));

        existente.setTitulo_foto(fotos.getTitulo_foto());
        existente.setDescripcion(fotos.getDescripcion());
        existente.setFecha_creacion(fotos.getFecha_creacion());
        existente.setId_usuario(fotos.getId_usuario());

        if (fotos.getFoto() != null && fotos.getFoto().length > 0) {
            existente.setFoto(fotos.getFoto());
        }

        fv.validarLocalDate(existente.getFecha_creacion());

        return fotosRepository.save(existente);
    }

    @Override
    public void deleteFotos(Integer id) {
        Fotos fotos = fotosRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));
        fotosRepository.delete(fotos);
    }
}
