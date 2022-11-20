package com.esi.petperfeito.repository;

import java.util.Optional;

import com.esi.petperfeito.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);
}
