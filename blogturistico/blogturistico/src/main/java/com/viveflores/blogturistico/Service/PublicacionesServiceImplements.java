package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Publicaciones;
import com.viveflores.blogturistico.Exception.CorreoValidar;
import com.viveflores.blogturistico.Exception.FechasValidar;
import com.viveflores.blogturistico.Exception.NotFoundExcepcion;
import com.viveflores.blogturistico.Repository.PublicacionesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacionesServiceImplements implements PublicacionesService {
    private final PublicacionesRepository publicacionesRepository;

    CorreoValidar cv = new CorreoValidar();
    FechasValidar fv = new FechasValidar();

    public PublicacionesServiceImplements(PublicacionesRepository publicacionesRepository) {
        this.publicacionesRepository = publicacionesRepository;
    }

    @Override
    public List<Publicaciones> getAllPublicaciones() {
        return publicacionesRepository.findAll();
    }

    @Override
    public Publicaciones getPublicacionesById(Integer id) {
        return publicacionesRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));
    }

    @Override
    public Publicaciones savePublicaciones(Publicaciones publicaciones) {
        cv.formatoCorreo(publicaciones.getEmail_publicacion());
        fv.validarLocalDate(publicaciones.getFecha_creacion());

        List<Publicaciones> publicacionesList = publicacionesRepository.findAll();
        for(Publicaciones p : publicacionesList){
            if(p.getEmail_publicacion().equals(publicaciones.getEmail_publicacion())){
                throw new NotFoundExcepcion("El correo ya existe");
            }
        }

        return publicacionesRepository.save(publicaciones);
    }

    @Override
    public Publicaciones updatePublicaciones(Integer id, Publicaciones publicaciones) {
        Publicaciones publicaciones1 = publicacionesRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));

        publicaciones1.setNombre_publicacion(publicaciones.getNombre_publicacion());
        publicaciones1.setDescripcion(publicaciones.getDescripcion());
        publicaciones1.setDireccion(publicaciones.getDireccion());
        publicaciones1.setFoto(publicaciones.getFoto());
        publicaciones1.setTelefono(publicaciones.getTelefono());
        publicaciones1.setEmail_publicacion(publicaciones.getEmail_publicacion());
        publicaciones1.setHorario(publicaciones.getHorario());
        publicaciones1.setFecha_creacion(publicaciones.getFecha_creacion());
        publicaciones1.setEstado_publicacion(publicaciones.getEstado_publicacion());
        publicaciones1.setId_categoria(publicaciones.getId_categoria());
        publicaciones1.setId_usuario(publicaciones.getId_usuario());

        cv.formatoCorreo(publicaciones1.getEmail_publicacion());
        fv.validarLocalDate(publicaciones1.getFecha_creacion());

        List<Publicaciones> publicacionesList = publicacionesRepository.findAll();

        for(Publicaciones p : publicacionesList){
            if(p.getEmail_publicacion().equals(publicaciones1.getEmail_publicacion())){
                throw new NotFoundExcepcion("El correo ya existe");
            }
        }

        return publicacionesRepository.save(publicaciones1);
    }

    @Override
    public void deletePublicaciones(Integer id) {
        Publicaciones publicaciones = publicacionesRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));
        publicacionesRepository.delete(publicaciones);
    }
}
