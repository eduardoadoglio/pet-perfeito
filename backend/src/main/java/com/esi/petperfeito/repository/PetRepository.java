package com.esi.petperfeito.repository;

import java.util.List;
import java.util.Optional;

import com.esi.petperfeito.model.Ong;
import org.springframework.data.jpa.repository.JpaRepository;
import com.esi.petperfeito.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByEspecie(String especie);
    List<Pet> findByRaca(String raca);
    List<Pet> findBySexo(String sexo);
    List<Pet> findByIdade(String dataNascimento);
    List<Pet> findByOng(Ong ong);
    Optional<Pet> findById(Long id);
}
