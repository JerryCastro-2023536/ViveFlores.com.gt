package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Resena;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResenaService {
    List<Resena> getAllResenas();
    Resena getResenaById (Integer id);
    Resena saveResena (Resena resena) throws RuntimeException;
    Resena updateResena (Integer id, Resena resena);
    void deleteResena (Integer id);
}
