package com.viveflores.blogturistico.Repository;

import com.viveflores.blogturistico.Entity.Publicaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionesRepository extends JpaRepository <Publicaciones, Integer> {
    @Query("SELECT p FROM Publicaciones p WHERE LOWER(p.nombre_publicacion) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Publicaciones> buscarPorNombre(@Param("nombre") String nombre);
}
