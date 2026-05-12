package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Evento;
import com.viveflores.blogturistico.Exception.FechasValidar;
import com.viveflores.blogturistico.Exception.NotFoundExcepcion;
import com.viveflores.blogturistico.Repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoServiceImplements implements EventoService{
    private final EventoRepository eventoRepository;
    FechasValidar fv = new FechasValidar();

    public EventoServiceImplements (EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Override
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    @Override
    public Evento getEventoById(Integer id) {
        return eventoRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));
    }

    @Override
    public Evento saveEvento(Evento evento) throws RuntimeException {
        fv.validarDosFechas(evento.getFecha_inicio(), evento.getFecha_fin());
        return eventoRepository.save(evento);
    }

    @Override
    public void deleteEvento(Integer id) {
        if (!eventoRepository.existsById(id)) {
            throw new NotFoundExcepcion("El id no existe");
        }
        eventoRepository.deleteById(id);
    }

    @Override
    public Evento updateEvento(Integer id, Evento evento){
        Evento existingEvento = eventoRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));

        existingEvento.setNombre_evento(evento.getNombre_evento());
        existingEvento.setDescripcion(evento.getDescripcion());
        existingEvento.setFoto(evento.getFoto());
        existingEvento.setFecha_inicio(evento.getFecha_inicio());
        existingEvento.setFecha_fin(evento.getFecha_fin());
        existingEvento.setUbicacion(evento.getUbicacion());
        existingEvento.setId_usuario(evento.getId_usuario());
        fv.validarDosFechas(existingEvento.getFecha_inicio(), existingEvento.getFecha_fin());

        return eventoRepository.save(existingEvento);

    }
}
