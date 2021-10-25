package com.esi.petperfeito.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.esi.petperfeito.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByNome(String nome);
    List<Pet> findByEspecie(String nome);
}
