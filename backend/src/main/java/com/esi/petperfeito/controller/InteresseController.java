package com.esi.petperfeito.controller;

import com.esi.petperfeito.model.Interesse;
import com.esi.petperfeito.model.Pet;
import com.esi.petperfeito.model.Usuario;
import com.esi.petperfeito.repository.InteresseRepository;
import com.esi.petperfeito.repository.PetRepository;
import com.esi.petperfeito.repository.UsuarioRepository;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class InteresseController {

    @Autowired
    InteresseRepository interesseRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PetRepository petRepository;

    @GetMapping("/interesses/usuario/{id}")
    public ResponseEntity<Interesse> getInteressesByUsuario(@PathVariable("id") long id) {
        Usuario usuario = usuarioRepository.getById(id);
        Optional<Interesse> InteresseData = interesseRepository.findByUsuario(usuario);

        return InteresseData.map(interesse -> new ResponseEntity<>(interesse, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/interesses/pet/{id}")
    public ResponseEntity<Interesse> getInteressesByPet(@PathVariable("id") long id) {
        Pet pet = petRepository.getById(id);
        Optional<Interesse> InteresseData = interesseRepository.findByPet(pet);

        return InteresseData.map(interesse -> new ResponseEntity<>(interesse, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/interesses/{id}")
    public ResponseEntity<Interesse> getInteressesById(@PathVariable("id") long id) {
        Optional<Interesse> InteresseData = interesseRepository.findById(id);

        return InteresseData.map(interesse -> new ResponseEntity<>(interesse, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/interesses")
    public ResponseEntity<Interesse> createInteresses(@RequestBody Interesse Interesse) {
        try {
            Interesse _Interesse = interesseRepository
                    .save(new Interesse(Interesse.getPet(), Interesse.getUsuario()));
            return new ResponseEntity<>(_Interesse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/interesses/{id}")
    public ResponseEntity<Interesse> updateInteresses(@PathVariable("id") long id, @RequestBody Interesse Interesse) {
        Optional<Interesse> InteresseData = interesseRepository.findById(id);

        if (InteresseData.isPresent()) {
            Interesse _Interesse = InteresseData.get();
            _Interesse.setPet(Interesse.getPet());
            _Interesse.setUsuario(Interesse.getUsuario());
            return new ResponseEntity<>(interesseRepository.save(_Interesse), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/interesses/{id}")
    public ResponseEntity<HttpStatus> deleteInteressesById(@PathVariable("id") long id) {
        try {
            interesseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/interesses/usuario/{id}")
    public ResponseEntity<HttpStatus> deleteInteressesByUsuario(@PathVariable("id") long id) {
        try {
            interesseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/interesses/pet/{id}")
    public ResponseEntity<HttpStatus> deleteInteressesByPet(@PathVariable("id") long id) {
        try {
            interesseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/interesses")
    public ResponseEntity<HttpStatus> deleteAllInteresses() {
        try {
            interesseRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
