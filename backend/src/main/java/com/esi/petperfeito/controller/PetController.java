package com.esi.petperfeito.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esi.petperfeito.model.Pet;
import com.esi.petperfeito.repository.PetRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PetController {

    @Autowired
    PetRepository PetRepository;

    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> getAllPets(@RequestParam(required = false) String nome) {
        try {
            List<Pet> pets = new ArrayList<Pet>();

            if (nome == null)
                PetRepository.findAll().forEach(pets::add);
            else
                PetRepository.findByNome(nome).forEach(pets::add);

            if (pets.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable("id") long id) {
        Optional<Pet> PetData = PetRepository.findById(id);

        if (PetData.isPresent()) {
            return new ResponseEntity<>(PetData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/pets")
    public ResponseEntity<Pet> createPet(@RequestBody Pet Pet) {
        try {
            Pet _Pet = PetRepository
                    .save(new Pet(Pet.getNome(), Pet.getDescricao(), Pet.getEspecie(), Pet.getSexo(), Pet.getDataNascimento()));
            return new ResponseEntity<>(_Pet, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable("id") long id, @RequestBody Pet Pet) {
        Optional<Pet> PetData = PetRepository.findById(id);

        if (PetData.isPresent()) {
            Pet _Pet = PetData.get();
            _Pet.setNome(Pet.getNome());
            _Pet.setDescricao(Pet.getDescricao());
            _Pet.setEspecie(Pet.getEspecie());
            _Pet.setSexo(Pet.getSexo());
            _Pet.setDataNascimento(Pet.getDataNascimento());
            return new ResponseEntity<>(PetRepository.save(_Pet), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/pets/{id}")
    public ResponseEntity<HttpStatus> deletePet(@PathVariable("id") long id) {
        try {
            PetRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/pets")
    public ResponseEntity<HttpStatus> deleteAllPets() {
        try {
            PetRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
