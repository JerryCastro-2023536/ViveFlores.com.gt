package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Favoritos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FavoritosService {
    List<Favoritos> getFavoritosAll();
    Favoritos getFavoritosById(Integer id);
    Favoritos saveFavoritos(Favoritos favoritos);
    Favoritos updateFavoritos(Integer id, Favoritos favoritos);
    void deleteFavoritos(Integer id);
}
