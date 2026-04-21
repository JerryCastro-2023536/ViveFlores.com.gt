package com.viveflores.blogturistico.Exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FechasValidar {
    public void validarLocalDate(LocalDate fecha){
        if(fecha.isAfter(LocalDate.now())){
            throw new NotFoundExcepcion("La fecha de creacion es distinta a la de hoy");
        }
    }

    public void validarLocalDateTime(LocalDateTime fecha1){
        if(fecha1.isAfter(LocalDateTime.now())){
            throw new NotFoundExcepcion("La fecha de creacion es distinta a la de hoy");
        }
    }

    public void validarDosFechas(LocalDateTime fecha1, LocalDateTime fecha2){
        if(fecha1.isAfter(LocalDateTime.now())){
            throw new NotFoundExcepcion("La fecha de creacion es distinta a la de hoy");
        }

        if(fecha1.isAfter(fecha2)){
            throw new NotFoundExcepcion("Las fechas no tienen logica");
        }
    }
}
