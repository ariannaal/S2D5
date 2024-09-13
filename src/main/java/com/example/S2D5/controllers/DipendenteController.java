package com.example.S2D5.controllers;

import com.example.S2D5.entities.Dipendente;
import com.example.S2D5.exceptions.BadRequestEx;
import com.example.S2D5.payloads.NewDipendenteDTO;
import com.example.S2D5.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    // POST http://localhost:3001/dipendenti
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipendente(@RequestBody @Validated NewDipendenteDTO body, BindingResult validation) throws IOException {

        if (validation.hasErrors()) {
            throw new BadRequestEx(validation.getAllErrors());
        }
        return dipendenteService.save(body);
    }

    // GET http://localhost:3001/dipendenti / {id}
    @GetMapping("/{id}")
    private Dipendente getSingleDipendente(@PathVariable int id){
        return dipendenteService.findById(id);
    }
}
