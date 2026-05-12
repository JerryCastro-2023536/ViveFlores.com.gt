package com.viveflores.blogturistico.Exception;

import com.viveflores.blogturistico.Entity.Publicaciones;
import com.viveflores.blogturistico.Repository.PublicacionesRepository;

import java.util.List;

public class CorreoValidar {

    private PublicacionesRepository publicacionesRepository;

    public void formatoCorreo(String email){
        if(!(email.contains("@gmail.com") || email.contains("@yahoo.com") || email.contains("@outlook.com"))){
            throw new NotFoundExcepcion("El correo no tiene el formato establecido");
        }
    }


}
