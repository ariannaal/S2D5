package com.example.S2D5.repositories;

import com.example.S2D5.entities.Dipendente;
import com.example.S2D5.entities.GestionePrenotazioni;
import com.example.S2D5.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GestionePrenotazioniRepository extends JpaRepository<GestionePrenotazioni, Integer> {

    Optional<GestionePrenotazioni> findById(int id);

}
