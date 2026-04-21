package com.viveflores.blogturistico.Repository;

import com.viveflores.blogturistico.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios,Integer> {
}
