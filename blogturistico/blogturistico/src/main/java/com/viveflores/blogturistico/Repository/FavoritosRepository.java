package com.viveflores.blogturistico.Repository;

import com.viveflores.blogturistico.Entity.Favoritos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritosRepository extends JpaRepository <Favoritos, Integer>{
}
