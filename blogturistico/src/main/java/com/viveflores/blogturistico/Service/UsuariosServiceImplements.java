package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Usuarios;
import com.viveflores.blogturistico.Exception.CorreoValidar;
import com.viveflores.blogturistico.Exception.FechasValidar;
import com.viveflores.blogturistico.Exception.NotFoundExcepcion;
import com.viveflores.blogturistico.Exception.Validation;
import com.viveflores.blogturistico.Repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosServiceImplements implements UsuariosService{
    private final UsuariosRepository UsuarioRepository;
    Validation v = new Validation();
    FechasValidar fv = new FechasValidar();
    CorreoValidar cv = new CorreoValidar();

    public UsuariosServiceImplements(UsuariosRepository usuarioRepository) {
        UsuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuarios> getAllUsuarios() {
        return UsuarioRepository.findAll();
    }

    @Override
    public Usuarios getUsuariosById(Integer id) {
        return UsuarioRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));
    }

    @Override
    public Usuarios saveUsuarios(Usuarios usuarios) throws RuntimeException {
        cv.formatoCorreo(usuarios.getEmail_usuario());
        fv.validarLocalDate(usuarios.getFecha_registro());
        v.validarRol(usuarios.getRol());
        return UsuarioRepository.save(usuarios);
    }

    @Override
    public Usuarios updateUsuarios(Integer id, Usuarios usuarios) {
        return UsuarioRepository.save(usuarios);
    }

    @Override
    public void deleteUsuarios(Integer id) {
        UsuarioRepository.deleteById(id);
    }

    @Override
    public Usuarios login(String username, String contrasena) {

        Usuarios u = UsuarioRepository.findByUsername(username);

        if(u != null && contrasena.equals(u.getContrasena_usuario())){
            return u;
        }

        return null;
    }
}
