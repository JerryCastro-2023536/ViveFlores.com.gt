package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Fotos;
import com.viveflores.blogturistico.Service.FotosService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping("/fotos")
public class FotosViewController {

    private final FotosService fotosService;

    public FotosViewController(FotosService fotosService) {
        this.fotosService = fotosService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("fotos", fotosService.getAllFotos());
        model.addAttribute("foto", new Fotos());
        model.addAttribute("modoEdicion", false);
        return "fotos";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Fotos fotos,
                          @RequestParam(value = "archivo", required = false) MultipartFile archivo)
            throws IOException {

        if (archivo != null && !archivo.isEmpty()) {
            fotos.setFoto(archivo.getBytes());
        }
        if (fotos.getFecha_creacion() == null) {
            fotos.setFecha_creacion(LocalDate.now());
        }
        fotosService.saveFotos(fotos);
        return "redirect:/fotos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("foto", fotosService.getFotoById(id));
        model.addAttribute("fotos", fotosService.getAllFotos());
        model.addAttribute("modoEdicion", true);
        return "fotos";
    }
    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id,
                             @ModelAttribute Fotos fotos,
                             @RequestParam(value = "archivo", required = false) MultipartFile archivo)
            throws IOException {

        if (archivo != null && !archivo.isEmpty()) {
            fotos.setFoto(archivo.getBytes());
        } else {
            Fotos existente = fotosService.getFotoById(id);
            fotos.setFoto(existente.getFoto());
        }
        fotosService.updateFotos(id, fotos);
        return "redirect:/fotos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        fotosService.deleteFotos(id);
        return "redirect:/fotos";
    }

    @GetMapping("/imagen/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getImagen(@PathVariable Integer id) {
        Fotos foto = fotosService.getFotoById(id);
        byte[] bytes = foto.getFoto();

        if (bytes == null || bytes.length == 0) {
            return ResponseEntity.notFound().build();
        }

        MediaType tipo = detectarTipoImagen(bytes);

        return ResponseEntity.ok()
                .contentType(tipo)
                .body(bytes);
    }

    private MediaType detectarTipoImagen(byte[] bytes) {
        if (bytes.length >= 3
                && (bytes[0] & 0xFF) == 0xFF
                && (bytes[1] & 0xFF) == 0xD8
                && (bytes[2] & 0xFF) == 0xFF) {
            return MediaType.IMAGE_JPEG;
        }
        if (bytes.length >= 4
                && (bytes[0] & 0xFF) == 0x89
                && bytes[1] == 'P'
                && bytes[2] == 'N'
                && bytes[3] == 'G') {
            return MediaType.IMAGE_PNG;
        }
        if (bytes.length >= 3
                && bytes[0] == 'G'
                && bytes[1] == 'I'
                && bytes[2] == 'F') {
            return MediaType.IMAGE_GIF;
        }
        if (bytes.length >= 12
                && bytes[0] == 'R' && bytes[1] == 'I' && bytes[2] == 'F' && bytes[3] == 'F'
                && bytes[8] == 'W' && bytes[9] == 'E' && bytes[10] == 'B' && bytes[11] == 'P') {
            return MediaType.parseMediaType("image/webp");
        }
        return MediaType.IMAGE_JPEG;
    }
}
