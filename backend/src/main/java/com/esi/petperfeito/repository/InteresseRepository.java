package com.esi.petperfeito.repository;

import com.esi.petperfeito.model.Interesse;
import com.esi.petperfeito.model.Pet;
import com.esi.petperfeito.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InteresseRepository  extends JpaRepository<Interesse, Long> {
    Optional<Interesse> findByUsuario(Usuario usuario);
    Optional<Interesse> findByPet(Pet pet);
}
