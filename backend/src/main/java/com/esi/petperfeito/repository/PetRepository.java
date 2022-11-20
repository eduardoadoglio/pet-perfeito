package com.esi.petperfeito.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.esi.petperfeito.model.Ong;
import org.springframework.data.jpa.repository.JpaRepository;
import com.esi.petperfeito.model.Pet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByEspecie(String especie);
    List<Pet> findByRaca(String raca);
    List<Pet> findBySexo(String sexo);
    List<Pet> findAllByDataNascimentoBetween(LocalDate startDate, LocalDate endDate);
    @Query("select p from pets p where p.data_nascimento <= :selectedDate")
    List<Pet> findAllWithDataNascimentoBefore(@Param("selectedDate") LocalDate selectedDate);
    @Query("select p from pets p where p.data_nascimento >= :selectedDate")
    List<Pet> findAllWithDataNascimentoAfter(@Param("selectedDate") LocalDate selectedDate);
    List<Pet> findByOng(Ong ong);
    Optional<Pet> findById(Long id);
}
