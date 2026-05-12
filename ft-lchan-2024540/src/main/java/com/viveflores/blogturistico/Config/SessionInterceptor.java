package com.viveflores.blogturistico.Config;

import com.viveflores.blogturistico.Entity.Usuarios;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        if (uri.startsWith("/css/") || uri.startsWith("/js/") || uri.startsWith("/img/")) {
            return true;
        }

        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("/acceder");
            return false;
        }

        Usuarios usuario = (Usuarios) session.getAttribute("usuarioLogueado");

        boolean isAdminRoute = uri.startsWith("/paneladmin") || 
                               uri.startsWith("/publicaciones") || 
                               uri.startsWith("/savePublicacion") ||
                               uri.startsWith("/updatePublicacion") ||
                               uri.startsWith("/deletePublicacion") ||
                               uri.startsWith("/reportes") || 
                               uri.startsWith("/saveReporte") ||
                               uri.startsWith("/updateReporte") ||
                               uri.startsWith("/deleteReporte") ||
                               uri.startsWith("/favoritos") || 
                               uri.startsWith("/categorias") || 
                               uri.startsWith("/servicios");

        if (isAdminRoute) {
            if (usuario.getRol() == null || !usuario.getRol().equalsIgnoreCase("admin")) {
                response.sendRedirect("/index");
                return false;
            }
        }

        return true;
    }
}
