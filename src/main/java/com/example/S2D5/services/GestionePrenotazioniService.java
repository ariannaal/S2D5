package com.example.S2D5.services;

import com.example.S2D5.entities.Dipendente;
import com.example.S2D5.entities.GestionePrenotazioni;
import com.example.S2D5.entities.Viaggio;
import com.example.S2D5.exceptions.BadRequestEx;
import com.example.S2D5.exceptions.NotFoundEx;
import com.example.S2D5.payloads.NewPrenotazioneDTO;
import com.example.S2D5.payloads.NewViaggioDTO;
import com.example.S2D5.repositories.DipendenteRepository;
import com.example.S2D5.repositories.GestionePrenotazioniRepository;
import com.example.S2D5.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionePrenotazioniService {

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private ViaggioRepository viaggiRepository;

    @Autowired
    private GestionePrenotazioniRepository gestionePrenotazioniRepository;

    public GestionePrenotazioni save(NewPrenotazioneDTO body) {

        Viaggio viaggio = viaggiRepository.findById(body.viaggioId())
                .orElseThrow(() -> new NotFoundEx("Viaggio con id  " + body.viaggioId() + " non trovato"));
        Dipendente dipendente = dipendenteRepository.findById(body.dipendenteId())
                .orElseThrow(() -> new NotFoundEx("Dipendente con id " + body.dipendenteId() + " non trovato"));

        // verifica se esiste gia una prenotazione per lo stesso dipendente e data
        gestionePrenotazioniRepository.findByDipendenteAndDataPrenotazione(dipendente, body.dataPrenotazione())
                .ifPresent(p -> {
                    throw new BadRequestEx("Il dipendente ha già una prenotazione per la data " + body.dataPrenotazione());
                });

        GestionePrenotazioni newPrenotazione = new GestionePrenotazioni();
        newPrenotazione.setViaggio(viaggio);
        newPrenotazione.setDipendente(dipendente);
        newPrenotazione.setDataPrenotazione(body.dataPrenotazione());
        newPrenotazione.setNoteEPreferenze(body.noteEPreferenze());

        return gestionePrenotazioniRepository.save(newPrenotazione);
    }


    public List<GestionePrenotazioni> listaPrenotazioni() {
        return gestionePrenotazioniRepository.findAll();
    }

    public GestionePrenotazioni findById(int id) {
        return gestionePrenotazioniRepository.findById(id)
                .orElseThrow(() -> new NotFoundEx("Prenotazione con id " + id + " non trovata"));
    }

    public void deleteById(int id) {
        GestionePrenotazioni found = findById(id);
        gestionePrenotazioniRepository.delete(found);
    }

    public GestionePrenotazioni updateById(int id, NewPrenotazioneDTO body) {
        GestionePrenotazioni found = findById(id);

        Viaggio viaggio = viaggiRepository.findById(body.viaggioId())
                .orElseThrow(() -> new NotFoundEx("Viaggio con id  " + body.viaggioId() + " non trovato"));

        Dipendente dipendente = dipendenteRepository.findById(body.dipendenteId())
                .orElseThrow(() -> new NotFoundEx("Dipendente con id " + body.dipendenteId() + " non trovato"));

        found.setViaggio(viaggio);
        found.setDipendente(dipendente);
        found.setDataPrenotazione(body.dataPrenotazione());
        found.setNoteEPreferenze(body.noteEPreferenze());

        return gestionePrenotazioniRepository.save(found);
    }

}
