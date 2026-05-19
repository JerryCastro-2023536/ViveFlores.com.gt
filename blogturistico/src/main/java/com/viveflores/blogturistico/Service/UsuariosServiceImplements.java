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

        Usuarios existingEmail = UsuarioRepository.findByEmailUsuario(usuarios.getEmail_usuario());
        if (existingEmail != null && !existingEmail.getId_usuario().equals(usuarios.getId_usuario())) {
            throw new NotFoundExcepcion("El correo ya existe");
        }

        Usuarios existingUsername = UsuarioRepository.findByUsername(usuarios.getUsername());
        if (existingUsername != null && !existingUsername.getId_usuario().equals(usuarios.getId_usuario())) {
            throw new NotFoundExcepcion("El nombre de usuario ya existe");
        }

        return UsuarioRepository.save(usuarios);
    }

    @Override
    public Usuarios updateUsuarios(Integer id, Usuarios usuarios) {
        Usuarios existing = getUsuariosById(id);

        existing.setUsername(usuarios.getUsername());
        existing.setNombre_usuario(usuarios.getNombre_usuario());
        existing.setApellido_usuario(usuarios.getApellido_usuario());
        existing.setEmail_usuario(usuarios.getEmail_usuario());
        existing.setContrasena_usuario(usuarios.getContrasena_usuario());
        existing.setRol(usuarios.getRol());
        existing.setFecha_registro(usuarios.getFecha_registro());

        cv.formatoCorreo(existing.getEmail_usuario());
        fv.validarLocalDate(existing.getFecha_registro());
        v.validarRol(existing.getRol());

        Usuarios existingEmail = UsuarioRepository.findByEmailUsuario(existing.getEmail_usuario());
        if (existingEmail != null && !existingEmail.getId_usuario().equals(id)) {
            throw new NotFoundExcepcion("El correo ya existe");
        }

        Usuarios existingUsername = UsuarioRepository.findByUsername(existing.getUsername());
        if (existingUsername != null && !existingUsername.getId_usuario().equals(id)) {
            throw new NotFoundExcepcion("El nombre de usuario ya existe");
        }

        return UsuarioRepository.save(existing);
    }

    @Override
    public void deleteUsuarios(Integer id) {
        Usuarios existing = getUsuariosById(id);
        UsuarioRepository.delete(existing);
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
