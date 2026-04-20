package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Resena;
import com.viveflores.blogturistico.Exception.FechasValidar;
import com.viveflores.blogturistico.Exception.NotFoundExcepcion;
import com.viveflores.blogturistico.Exception.Validation;
import com.viveflores.blogturistico.Repository.ResenaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenaServiceImplements implements ResenaService{

    private final ResenaRepository resenaRepository;
    Validation v = new Validation();
    FechasValidar fv = new FechasValidar();

    public ResenaServiceImplements (ResenaRepository resenaRepository){
        this.resenaRepository = resenaRepository;
    }

    @Override
    public List<Resena> getAllResenas(){
        return resenaRepository.findAll();
    }

    @Override
    public Resena getResenaById (Integer id){
        return resenaRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));
    }

    @Override
    public Resena saveResena (Resena resena) throws RuntimeException{
        fv.validarLocalDate(resena.getFecha_creacion());
        v.validarCalificacion(resena.getCalificacion());
        return resenaRepository.save(resena);
    }

    @Override
    public void deleteResena(Integer id){
        if (!resenaRepository.existsById(id)){
            throw new NotFoundExcepcion("El id no existe");
        }
        resenaRepository.deleteById(id);
    }

    @Override
    public Resena updateResena (Integer id, Resena resena){
        Resena existingResena= resenaRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));

        existingResena.setTitulo_resena(resena.getTitulo_resena());
        existingResena.setComentario(resena.getComentario());
        existingResena.setCalificacion(resena.getCalificacion());
        existingResena.setFecha_creacion(resena.getFecha_creacion());
        existingResena.setId_usuario(resena.getId_usuario());
        existingResena.setId_publicacion(resena.getId_publicacion());
        fv.validarLocalDate(existingResena.getFecha_creacion());
        v.validarCalificacion(existingResena.getCalificacion());

        return resenaRepository.save(existingResena);
    }
}
