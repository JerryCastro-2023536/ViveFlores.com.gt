package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Fotos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FotosService {
    List<Fotos> getAllFotos();
    Fotos getFotoById(Integer id);
    Fotos saveFotos(Fotos fotos);
    Fotos updateFotos(Integer id, Fotos fotos);
    void deleteFotos(Integer id);
}