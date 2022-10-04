package com.esi.petperfeito.repository;

import com.esi.petperfeito.model.InteresseForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InteresseFormRepository extends JpaRepository<InteresseForm, Long> {

    Optional<InteresseForm> findById(Long id);

}
