package com.example.S2D5.repositories;

import com.example.S2D5.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DipendenteRepository extends JpaRepository<Dipendente, Integer> {

    Optional<Dipendente> findByEmail(String email);

}
