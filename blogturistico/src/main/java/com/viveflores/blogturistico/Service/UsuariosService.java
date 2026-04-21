package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Usuarios;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuariosService {
    List<Usuarios> getAllUsuarios();
    Usuarios getUsuariosById(Integer id);
    Usuarios saveUsuarios(Usuarios usuarios)throws RuntimeException;
    Usuarios updateUsuarios(Integer id, Usuarios usuarios) ;
    void deleteUsuarios(Integer id);
    Usuarios login(String username, String contrasena);
}