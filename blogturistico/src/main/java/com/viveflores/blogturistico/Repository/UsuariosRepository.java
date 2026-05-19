package com.viveflores.blogturistico.Repository;

import com.viveflores.blogturistico.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios,Integer> {
    Usuarios findByUsername(String username);

    @Query("SELECT u FROM Usuarios u WHERE u.email_usuario = :email")
    Usuarios findByEmailUsuario(@Param("email") String email);
}

