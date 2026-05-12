package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Evento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventoService {
    List<Evento> getAllEventos();
    Evento getEventoById (Integer id);
    Evento saveEvento (Evento evento) throws RuntimeException;
    Evento updateEvento(Integer id, Evento evento);
    void deleteEvento (Integer id);
}
