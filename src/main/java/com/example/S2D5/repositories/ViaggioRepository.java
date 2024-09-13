package com.example.S2D5.repositories;

import com.example.S2D5.entities.Dipendente;
import com.example.S2D5.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ViaggioRepository extends JpaRepository<Viaggio, Integer>  {

    Optional<Viaggio> findById(int id);


}
