package com.viveflores.blogturistico.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExcepcion {

    @ExceptionHandler(NotFoundExcepcion.class)
    public ResponseEntity<Object> notFound(NotFoundExcepcion e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Error: ", e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> validarNotaciones(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = ((FieldError) error).getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> validarLlavesForaneas(SQLIntegrityConstraintViolationException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Error", "La llave foranea no existe"));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Object> validarFormato(HttpMediaTypeNotSupportedException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Error", "El formato no es tipo JSON"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> validarTipoDato(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Error", "Tipo de dato incorrecto"));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> validarDatos(MethodArgumentTypeMismatchException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Error", "Tipo de dato incorrecto"));
    }

    @ExceptionHandler(Exception.class)
    public Object manejarExcepcionGlobal(
            Exception e,
            jakarta.servlet.http.HttpServletRequest request,
            org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes
    ) {
        String uri = request.getRequestURI();

        // Si es una llamada API REST, retornamos JSON
        if (uri.startsWith("/api/")) {
            java.util.Map<String, String> errorResponse = new java.util.HashMap<>();
            errorResponse.put("Error", e.getMessage() != null ? e.getMessage() : "Error interno del servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Si es un CRUD de Thymeleaf:
        e.printStackTrace(); // Log en la consola del servidor para depuración

        // Guardamos el mensaje exacto del error en Flash Attributes
        redirectAttributes.addFlashAttribute(
                "errorGlobal",
                e.getMessage() != null ? e.getMessage() : "Ocurrió un error inesperado al procesar la solicitud."
        );

        // Intentamos obtener la cabecera 'Referer' para redireccionar exactamente a la misma pantalla del CRUD
        String referer = request.getHeader("Referer");
        if (referer != null && !referer.isEmpty()) {
            return "redirect:" + referer;
        }

        // Si no hay Referer, volvemos al panel principal de administración
        return "redirect:/paneladmin";
    }
}
