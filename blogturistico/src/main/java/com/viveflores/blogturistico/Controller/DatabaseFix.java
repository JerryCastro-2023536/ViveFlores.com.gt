package com.viveflores.blogturistico.Controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseFix {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseFix(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void fixBlobColumn() {
        try {
            jdbcTemplate.execute("ALTER TABLE publicaciones MODIFY COLUMN foto LONGBLOB");
            try {
                jdbcTemplate.execute("SET GLOBAL max_allowed_packet=33554432");
                System.out.println("EXITO: max_allowed_packet ajustado a 32MB");
            } catch (Exception e) {
                System.out.println("Nota: No se pudo ajustar max_allowed_packet automáticamente: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un detalle alterando la columna foto: " + e.getMessage());
        }
    }

}
