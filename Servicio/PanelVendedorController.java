package com.viveflores.blogturistico.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/panelvendedor")
public class PanelVendedorController {

    @GetMapping
    public String mostrarPanel() {
        return "panelVendedor";
    }
}