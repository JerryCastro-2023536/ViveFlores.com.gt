package com.viveflores.blogturistico.Controller;

import com.viveflores.blogturistico.Entity.Contactar;
import com.viveflores.blogturistico.Service.ContactarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contactar")
public class ContactarController {

    private final ContactarService contactarService;

    public ContactarController(ContactarService contactarService) {
        this.contactarService = contactarService;
    }

    @GetMapping
    public List<Contactar> getAllContactar() {
        return contactarService.getAllContactar();
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getContactarById(@PathVariable Integer id) {
        try {
            Contactar contactar = contactarService.getContactarById(id);
            return new ResponseEntity<>(contactar, HttpStatus.FOUND);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveContactar(@Valid @RequestBody Contactar contactar) {
        try {
            Contactar saveContactar = contactarService.saveContactar(contactar);
            return new ResponseEntity<>(saveContactar, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateContactar(@PathVariable Integer id, @Valid @RequestBody Contactar contactar) {
        try {
            Contactar updateContactar = contactarService.updateContactar(id, contactar);
            return new ResponseEntity<>(updateContactar, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteContactar(@PathVariable Integer id) {
        try {
            contactarService.deleteContactar(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}