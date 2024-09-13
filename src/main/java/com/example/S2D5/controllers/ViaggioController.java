package com.example.S2D5.controllers;

import com.example.S2D5.entities.Viaggio;
import com.example.S2D5.exceptions.BadRequestEx;
import com.example.S2D5.payloads.NewViaggioDTO;
import com.example.S2D5.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    ViaggioService viaggioService;

    // POST http://localhost:3001/viaggi
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Viaggio saveViaggio(@RequestBody @Validated NewViaggioDTO body, BindingResult validation) {

        if (validation.hasErrors()) {
            throw new BadRequestEx(validation.getAllErrors());
        }
        return viaggioService.save(body);
    }

    // GET http://localhost:3001/blogposts / {id}
        @GetMapping("/{id}")
        private Viaggio getSingleViaggio(@PathVariable int id){
            return viaggioService.findById(id);
        }

    // GET http://localhost:3001/viaggi**
    @GetMapping
    public List<Viaggio> getAllViaggi() {
        return viaggioService.listaViaggi();
    }



}
