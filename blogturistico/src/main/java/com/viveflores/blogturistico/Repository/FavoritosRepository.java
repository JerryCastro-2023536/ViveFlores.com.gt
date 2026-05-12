package com.viveflores.blogturistico.Repository;

import com.viveflores.blogturistico.Entity.Favoritos;
import com.viveflores.blogturistico.Entity.Publicaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritosRepository extends JpaRepository <Favoritos, Integer>{
    @Query("SELECT f FROM Favoritos f WHERE f.id_usuario = :idUsuario AND f.id_publicacion = :idPublicacion")
    Optional<Favoritos> findByUsuarioAndPublicacion(@Param("idUsuario") Integer idUsuario,
                                                    @Param("idPublicacion") Integer idPublicacion);

    @Query("SELECT p FROM Publicaciones p JOIN Favoritos f ON p.id_publicacion = f.id_publicacion WHERE f.id_usuario = :idUsuario")
    List<Publicaciones> findFavoritosByUsuario(@Param("idUsuario") Integer idUsuario);

}
