package com.viveflores.blogturistico.Service;

import com.viveflores.blogturistico.Entity.Contactar;
import com.viveflores.blogturistico.Exception.FechasValidar;
import com.viveflores.blogturistico.Exception.NotFoundExcepcion;
import com.viveflores.blogturistico.Repository.ContactarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactarServiceImplements implements ContactarService {

    private final ContactarRepository contactarRepository;
    FechasValidar fv = new FechasValidar();

    public ContactarServiceImplements(ContactarRepository contactarRepository) {
        this.contactarRepository = contactarRepository;
    }

    @Override
    public List<Contactar> getAllContactar() {
        return contactarRepository.findAll();
    }

    @Override
    public Contactar getContactarById(Integer id) {
        return contactarRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));
    }

    @Override
    public Contactar saveContactar(Contactar contactar) {
        fv.validarLocalDateTime(contactar.getFecha_envio());
        return contactarRepository.save(contactar);
    }

    @Override
    public Contactar updateContactar(Integer id, Contactar contactar) {
        Contactar contactar1 = contactarRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));;

        contactar1.setAsunto(contactar.getAsunto());
        contactar1.setMensaje(contactar.getMensaje());
        contactar1.setFecha_envio(contactar.getFecha_envio());
        contactar1.setId_usuario(contactar.getId_usuario());
        contactar1.setId_publicacion(contactar.getId_publicacion());
        fv.validarLocalDateTime(contactar1.getFecha_envio());

        return contactarRepository.save(contactar1);
    }

    @Override
    public void deleteContactar(Integer id) {
        Contactar contactar = contactarRepository.findById(id).orElseThrow(() ->
                new NotFoundExcepcion("El id no existe"));;
        contactarRepository.delete(contactar);
    }
}