package com.esi.petperfeito.controller;

import com.esi.petperfeito.model.Interesse;
import com.esi.petperfeito.model.Pet;
import com.esi.petperfeito.model.Usuario;
import com.esi.petperfeito.repository.InteresseRepository;
import com.esi.petperfeito.repository.PetRepository;
import com.esi.petperfeito.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
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

import java.util.ArrayList;
import java.util.List;
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

    @Operation(summary = "Retorna interesses do usuario")
    @GetMapping("/interesses/usuario/{id}")
    public ResponseEntity<List<Interesse>> getInteressesByUsuario(@PathVariable("id") long id) {
        Usuario usuario = usuarioRepository.getById(id);
        List<Interesse> InteresseData = new ArrayList<>(interesseRepository.findByUsuario(usuario));

        return new ResponseEntity<>(InteresseData, HttpStatus.OK);
    }

    @Operation(summary = "Retorna interesses do pet")
    @GetMapping("/interesses/pet/{id}")
    public ResponseEntity<List<Interesse>> getInteressesByPet(@PathVariable("id") long id) {
        Pet pet = petRepository.getById(id);
        List<Interesse> InteresseData = new ArrayList<>(interesseRepository.findByPet(pet));

        return new ResponseEntity<>(InteresseData, HttpStatus.OK);
    }

    @Operation(summary = "Busca interesse por id")
    @GetMapping("/interesses/{id}")
    public ResponseEntity<Interesse> getInteressesById(@PathVariable("id") long id) {
        Optional<Interesse> InteresseData = interesseRepository.findById(id);

        return InteresseData.map(interesse -> new ResponseEntity<>(interesse, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Cria interesse a partir de usuario e pet")
    @PostMapping("/interesses/{pet_id}/{user_id}")
    public ResponseEntity<Interesse> createInteresses(@PathVariable("pet_id") int pet_id, @PathVariable("user_id") int user_id) {
        try {
            Interesse _Interesse = interesseRepository
                    .save(new Interesse(petRepository.getById((long) pet_id), usuarioRepository.getById((long) user_id)));
            return new ResponseEntity<>(_Interesse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Atualiza interesse pelo id")
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

    @Operation(summary = "Deleta interesse pelo id")
    @DeleteMapping("/interesses/{id}")
    public ResponseEntity<HttpStatus> deleteInteressesById(@PathVariable("id") long id) {
        try {
            interesseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Deleta todos os interesses do usuario")
    @DeleteMapping("/interesses/usuario/{id}")
    public ResponseEntity<HttpStatus> deleteInteressesByUsuario(@PathVariable("id") long id) {
        try {
            interesseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Deleta todos os interesses do pet")
    @DeleteMapping("/interesses/pet/{id}")
    public ResponseEntity<HttpStatus> deleteInteressesByPet(@PathVariable("id") long id) {
        try {
            interesseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Deleta todos os interesses do banco de dados")
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
