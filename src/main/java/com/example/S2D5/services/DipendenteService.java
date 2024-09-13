package com.example.S2D5.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.S2D5.entities.Dipendente;
import com.example.S2D5.entities.Viaggio;
import com.example.S2D5.exceptions.NotFoundEx;
import com.example.S2D5.payloads.NewDipendenteDTO;
import com.example.S2D5.repositories.DipendenteRepository;
import com.example.S2D5.repositories.GestionePrenotazioniRepository;
import com.example.S2D5.repositories.ViaggioRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private ViaggioRepository viaggiRepository;

    @Autowired
    private GestionePrenotazioniRepository gestionePrenotazioniRepository;

    @Autowired
    private Cloudinary cloudinary;

    public List<Dipendente> listaDipendenti() {

        return dipendenteRepository.findAll();
    }

    public Dipendente save(NewDipendenteDTO body) throws IOException {
        dipendenteRepository.findByEmail(body.email()).ifPresent(user -> {
            try {
                throw new BadRequestException("L'email " + body.email() + " è già stata utilizzata");
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });
        Dipendente newDipendente = new Dipendente();
        newDipendente.setUsername(body.username());
        newDipendente.setNome(body.nome());
        newDipendente.setCognome(body.cognome());
        newDipendente.setEmail(body.email());

        Dipendente dipendenteSalvato = dipendenteRepository.save(newDipendente);
        System.out.println("Dipendente salvato con successo: " + dipendenteSalvato);

        return dipendenteSalvato;

    }

    public Dipendente findById(int id) {
        return dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundEx(id));
    }

    public void findByIdAndDelete(int id) {
        Dipendente found = this.findById(id);
        dipendenteRepository.delete(found);
    }

    public Dipendente findByIdAndUpdate(int id, Dipendente body) {
        Dipendente found = this.findById(id);
        found.setUsername(body.getUsername());
        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        found.setEmail(body.getEmail());
        found.setImmagineProfilo(body.getImmagineProfilo());
        return dipendenteRepository.save(found);
    }

    // upload immaginme profilo
    public void uploadImmagineProfilo(int id, MultipartFile image) throws IOException {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundEx("Dipendente con id " + id + " non trovato."));

        Map<String, Object> uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
        String immagineCover = uploadResult.get("url").toString();
        System.out.println("URL: " + immagineCover);

        dipendente.setImmagineProfilo(immagineCover);
        dipendenteRepository.save(dipendente);
    }


}
