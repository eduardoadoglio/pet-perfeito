package com.esi.petperfeito.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esi.petperfeito.model.Pet;
import com.esi.petperfeito.repository.PetRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PetController {

    private static final Logger logger = LoggerFactory.getLogger(InteresseController.class);

    @Autowired
    PetRepository PetRepository;

    @Operation(summary = "Retorna todos os pets no banco de dados")
    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> getAllPets(@RequestParam(required = false) String nome) {

        logger.info("Obtendo lista de pets cadastrados");

        try {
            List<Pet> pets = new ArrayList<>();

            if (nome == null)
                pets.addAll(PetRepository.findAll());
            else
                pets.addAll(PetRepository.findByNome(nome));

            if (pets.isEmpty()) {
                logger.error("Nenhum pet encontrado no banco de dados.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pets, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao retornar todos os pets.");
            logger.error(""+e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Busca pet por id")
    @GetMapping("/pets/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable("id") long id) {

        logger.info("Obtendo pet de id "+id);

        Optional<Pet> PetData = PetRepository.findById(id);

        return PetData.map(pet -> new ResponseEntity<>(pet, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Cria pet")
    @PostMapping("/pets")
    public ResponseEntity<Pet> createPet(@RequestBody Pet Pet) {

        logger.info("Criando pet "+Pet.getNome());

        try {
            Pet _Pet = PetRepository
                    .save(new Pet(
                            Pet.getNome(),
                            Pet.getDescricao(),
                            Pet.getEspecie(),
                            Pet.getRaca(),
                            Pet.getSexo(),
                            Pet.getDataNascimento(),
                            Pet.getPeso1(),
                            Pet.getPeso2(),
                            Pet.getPeso3(),
                            Pet.getPeso4(),
                            Pet.getPeso5(),
                            Pet.getPeso6(),
                            Pet.getPeso7(),
                            Pet.getPeso8()
                    ));
            return new ResponseEntity<>(_Pet, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro ao criar pet "+Pet.getNome());
            logger.error(""+e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Atualiza pet")
    @PutMapping("/pets/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable("id") long id, @RequestBody Pet Pet) {

        logger.info("Atualizando pet "+Pet.getId());

        Optional<Pet> PetData = PetRepository.findById(id);

        if (PetData.isPresent()) {
            Pet _Pet = PetData.get();
            _Pet.setNome(Pet.getNome());
            _Pet.setDescricao(Pet.getDescricao());
            _Pet.setEspecie(Pet.getEspecie());
            _Pet.setRaca(Pet.getRaca());
            _Pet.setSexo(Pet.getSexo());
            _Pet.setDataNascimento(Pet.getDataNascimento());
            _Pet.setPeso1(Pet.getPeso1());
            _Pet.setPeso2(Pet.getPeso2());
            _Pet.setPeso3(Pet.getPeso3());
            _Pet.setPeso4(Pet.getPeso4());
            _Pet.setPeso5(Pet.getPeso5());
            _Pet.setPeso6(Pet.getPeso6());
            _Pet.setPeso7(Pet.getPeso7());
            _Pet.setPeso8(Pet.getPeso8());
            return new ResponseEntity<>(PetRepository.save(_Pet), HttpStatus.OK);
        } else {
            logger.error("Pet "+Pet.getId()+" não encontrado.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Deleta pet por id")
    @DeleteMapping("/pets/{id}")
    public ResponseEntity<HttpStatus> deletePet(@PathVariable("id") long id) {

        logger.info("Deletando pet "+id);

        try {
            PetRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao deletar pet "+id);
            logger.error(""+e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
