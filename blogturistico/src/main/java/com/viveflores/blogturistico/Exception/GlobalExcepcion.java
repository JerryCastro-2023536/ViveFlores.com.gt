package com.viveflores.blogturistico.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import com.viveflores.blogturistico.Exception.CorreoExistenteExcepcion;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice(basePackages = "com.viveflores.blogturistico.Controller")
public class GlobalExcepcion {

    @ExceptionHandler(NotFoundExcepcion.class)
    public String notFound(NotFoundExcepcion e, HttpServletRequest request, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("errorGlobal", e.getMessage());
        return getRedirectUrl(request);
    }

    @ExceptionHandler(CorreoExistenteExcepcion.class)
    public String correoExistente(CorreoExistenteExcepcion e, HttpServletRequest request, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("errorGlobal", e.getMessage());
        return getRedirectUrl(request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String validarNotaciones(MethodArgumentNotValidException e, HttpServletRequest request, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("errorGlobal", "Error de validación en los campos del formulario.");
        return getRedirectUrl(request);
    }

    @ExceptionHandler({SQLIntegrityConstraintViolationException.class, DataIntegrityViolationException.class})
    public String validarLlavesForaneas(Exception e, HttpServletRequest request, RedirectAttributes redirectAttributes){
        String mensaje = "No se puede realizar esta acción porque el registro está vinculado a otros datos importantes del sistema.";
        redirectAttributes.addFlashAttribute("errorGlobal", mensaje);
        return getRedirectUrl(request);
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class, HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public String validarFormatos(Exception e, HttpServletRequest request, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("errorGlobal", "El formato de los datos o el tipo de petición no es correcto.");
        return getRedirectUrl(request);
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorGlobal", "Ha ocurrido un error inesperado al procesar la solicitud: " + ex.getMessage());
        return getRedirectUrl(request);
    }

    private String getRedirectUrl(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        if (referer != null && !referer.isEmpty()) {
            return "redirect:" + referer;
        }
        return "redirect:/paneladmin";
    }
}
