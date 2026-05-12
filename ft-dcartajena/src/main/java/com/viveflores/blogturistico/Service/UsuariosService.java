package com.viveflores.blogturistico.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.viveflores.blogturistico.Entity.Usuarios;

@Service
public interface UsuariosService {
    List<Usuarios> listar();
    Usuarios guardar(Usuarios usuarios);
    Usuarios buscarPorId(int id);
    void eliminar(int id);
}