package com.example.S2D5.repositories;

import com.example.S2D5.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ViaggioRepository extends JpaRepository<Viaggio, Integer>  {

    Optional<Viaggio> findById(int id);

}
