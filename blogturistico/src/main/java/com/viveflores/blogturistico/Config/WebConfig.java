package com.viveflores.blogturistico.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns(
                        "/paneladmin/**",
                        "/publicaciones/**", "/savePublicacion/**", "/updatePublicacion/**", "/deletePublicacion/**",
                        "/reportes/**", "/saveReporte/**", "/updateReporte/**", "/deleteReporte/**",
                        "/favoritos/**", "/saveFavorito/**", "/updateFavorito/**", "/deleteFavorito/**",
                        "/categorias/**", "/saveCategoria/**", "/updateCategoria/**", "/deleteCategoria/**",
                        "/servicios/**", "/saveServicio/**", "/updateServicio/**", "/deleteServicio/**",
                        "/perfilUsuario/**",
                        "/favoritosUsuario/**",
                        "/post/*/comentar",
                        "/favorito/*",
                        "/vendedor/**",
                        "/panelvendedor",
                        "/adminpublicaciones/**",
                        "/mensajes",
                        "/subir"
                )
                .excludePathPatterns(
                        "/", "/index", "/acceder", "/login", "/register", "/saveUsuario",
                        "/quienessomos", "/contacto",
                        "/css/**", "/js/**", "/img/**",
                        "/publicaciones/foto/**", "/servicios/foto/**", "/eventos/foto/**", "/fotos/foto/**"
                );
    }
}
