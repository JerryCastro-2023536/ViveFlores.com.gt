package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Contactar;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactarService {
    List<Contactar> getAllContactar();
    Contactar getContactarById(Integer id);
    Contactar saveContactar(Contactar contactar);
    Contactar updateContactar(Integer id, Contactar contactar);
    void deleteContactar(Integer id);
}