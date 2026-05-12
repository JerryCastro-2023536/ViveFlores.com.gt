package com.viveflores.blogturistico.Exception;

public class Validation {
    public void validarRol(String rol){
        if(!(rol.equals("usuario") || rol.equals("vendedor"))){
            throw new NotFoundExcepcion("El rol no existe a los acordados");
        }
    }

    public void validarCalificacion(Integer calificacion){
        if(calificacion <= 0 || calificacion > 5){
            throw new NotFoundExcepcion("La calificación no es valida");
        }
    }

}
