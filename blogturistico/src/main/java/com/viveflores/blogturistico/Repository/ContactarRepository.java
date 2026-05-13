package com.viveflores.blogturistico.Repository;

import com.viveflores.blogturistico.Entity.Contactar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactarRepository extends JpaRepository<Contactar, Integer> {
    @Query("SELECT c, p.nombre_publicacion FROM Contactar c JOIN Publicaciones p ON c.id_publicacion = p.id_publicacion WHERE p.id_usuario = :idUsuario")
    List<Object[]> findMensajesYPublicacionPorVendedor(@Param("idUsuario") Integer idUsuario);
}