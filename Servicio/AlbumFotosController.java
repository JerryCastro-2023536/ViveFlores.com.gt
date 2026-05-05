package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Fotos;
import com.viveflores.blogturistico.Service.FotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AlbumFotosController {

    @Autowired
    private FotosService fotosService;

    @GetMapping("/album")
    public String verAlbum(Model model) {
        List<Fotos> listaFotos = fotosService.getAllFotos();
        model.addAttribute("fotos", listaFotos);
        return "album";
    }

    @GetMapping("/foto/{id}")
    @ResponseBody
    public byte[] obtenerFoto(@PathVariable Integer id) {
        Fotos foto = fotosService.getFotoById(id);
        return foto.getFoto();
    }
}