package com.viveflores.blogturistico.Exception;

public class CorreoValidar {

    public void formatoCorreo(String email){
        if(!(email.contains("@gmail.com") || email.contains("@yahoo.com") || email.contains("@outlook.com"))){
            throw new NotFoundExcepcion("El correo no tiene el formato establecido");
        }
    }

}
