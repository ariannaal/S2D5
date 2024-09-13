package com.example.S2D5.controllers;

import com.example.S2D5.entities.GestionePrenotazioni;
import com.example.S2D5.entities.Viaggio;
import com.example.S2D5.exceptions.BadRequestEx;
import com.example.S2D5.payloads.NewPrenotazioneDTO;
import com.example.S2D5.payloads.NewViaggioDTO;
import com.example.S2D5.services.DipendenteService;
import com.example.S2D5.services.GestionePrenotazioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class GestionePrenotazioneController {

    @Autowired
    private GestionePrenotazioniService gestionePrenotazioniService;

    // POST http://localhost:3001/prenotazioni
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GestionePrenotazioni savePrenotazione(@RequestBody @Validated NewPrenotazioneDTO body, BindingResult validation) {

        if (validation.hasErrors()) {
            throw new BadRequestEx(validation.getAllErrors());
        }
        return gestionePrenotazioniService.save(body);
    }

    // GET http://localhost:3001/prenotazioni / {id}
    @GetMapping("/{id}")
    private GestionePrenotazioni getSinglePrenotazione(@PathVariable int id){
        return gestionePrenotazioniService.findById(id);
    }

    // GET http://localhost:3001/prenotazioni
    @GetMapping
    public List<GestionePrenotazioni> getAllPrenotazioni() {
        return gestionePrenotazioniService.listaPrenotazioni();
    }
}
