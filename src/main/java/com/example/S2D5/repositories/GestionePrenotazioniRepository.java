package com.example.S2D5.repositories;

import com.example.S2D5.entities.Dipendente;
import com.example.S2D5.entities.GestionePrenotazioni;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface GestionePrenotazioniRepository extends JpaRepository<GestionePrenotazioni, Integer> {

    Optional<GestionePrenotazioni> findById(int id);

    // per verificare che un dipendente non prenoti lo stesso giorno
    Optional<GestionePrenotazioni> findByDipendenteAndDataPrenotazione(Dipendente dipendente, LocalDate dataPrenotazione);

}
