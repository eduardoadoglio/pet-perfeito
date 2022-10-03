package com.esi.petperfeito.repository;

import com.esi.petperfeito.model.Interesse;
import com.esi.petperfeito.model.Pet;
import com.esi.petperfeito.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InteresseRepository  extends JpaRepository<Interesse, Long> {
    List<Interesse> findByUsuario(Usuario usuario);
    List<Interesse> findByPet(Pet pet);
}
