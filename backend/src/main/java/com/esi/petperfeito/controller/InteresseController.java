package com.esi.petperfeito.controller;

import com.esi.petperfeito.model.Interesse;
import com.esi.petperfeito.model.InteresseForm;
import com.esi.petperfeito.model.Pet;
import com.esi.petperfeito.model.Usuario;
import com.esi.petperfeito.repository.InteresseFormRepository;
import com.esi.petperfeito.repository.InteresseRepository;
import com.esi.petperfeito.repository.PetRepository;
import com.esi.petperfeito.repository.UsuarioRepository;
import com.esi.petperfeito.service.InteresseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class InteresseController {

    private static final Logger logger = LoggerFactory.getLogger(InteresseController.class);

    @Autowired
    InteresseRepository interesseRepository;

    @Autowired
    InteresseFormRepository interesseFormRepository;

    @Autowired
    InteresseService interesseService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PetRepository petRepository;

    @Operation(summary = "Retorna interesses do usuario")
    @GetMapping("/interesses/usuario/{id}")
    public ResponseEntity<List<Interesse>> getInteressesByUsuario(@PathVariable("id") long id) {

        logger.info("Obtendo interesses do usuário "+id);

        Usuario usuario = new Usuario();

        try {
            usuario = usuarioRepository.getById(id);
        } catch (Exception e) {
            logger.error("Usuário de id "+id+" não encontrado no banco de dados.");
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            List<Interesse> InteresseData = new ArrayList<>(interesseRepository.findByUsuario(usuario));
            return new ResponseEntity<>(InteresseData, HttpStatus.OK);
        }catch (Exception e) {
            logger.error("Erro ao obter interesses do usuário "+id);
            logger.error(String.valueOf(e));
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Retorna interesses do pet")
    @GetMapping("/interesses/pet/{id}")
    public ResponseEntity<List<Interesse>> getInteressesByPet(@PathVariable("id") long id) {

        logger.info("Obtendo interesses do pet "+id);

        Pet pet = new Pet();

        try {
            pet = petRepository.getById(id);
        } catch (Exception e) {
            logger.warn("Pet de id "+id+" não encontrado no banco de dados.");
            logger.error("Erro: "+e);
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            List<Interesse> InteresseData = new ArrayList<>(interesseRepository.findByPet(pet));
            return new ResponseEntity<>(InteresseData, HttpStatus.OK);
        }catch (Exception e) {
            logger.error("Erro ao obter interesses do pet "+id);
            logger.error(String.valueOf(e));
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Busca interesse por id")
    @GetMapping("/interesses/{id}")
    public ResponseEntity<Interesse> getInteressesById(@PathVariable("id") long id) {

        logger.info("Obtendo interesse "+id);

        Optional<Interesse> InteresseData = interesseRepository.findById(id);

        return InteresseData.map(interesse -> new ResponseEntity<>(interesse, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Cadastra formulário de interesse em pet")
    @PostMapping("/interesses/pet/{pet_id}/usuario/{user_id}")
    public ResponseEntity<Interesse> createInteresses(@PathVariable("pet_id") int pet_id, @PathVariable("user_id") int user_id, @RequestBody InteresseForm interesseForm) {

        logger.info("Registrando interesse do usuário "+user_id+" no pet "+pet_id);

        InteresseForm form = new InteresseForm();

        try {
            form = interesseFormRepository.save(interesseForm);
        } catch (Exception e) {
            logger.error("Erro ao tentar salvar formulário de interesse "+interesseForm.getId());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Interesse interesse = new Interesse(
                petRepository.getById((long) pet_id),
                usuarioRepository.getById((long) user_id),
                form);

        try {
            interesseService.generateUserRating(interesse);
        } catch (Exception e) {
            logger.error("Erro ao calcular pontos do usuário "+user_id);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            Interesse _Interesse = interesseRepository.save(interesse);
            return new ResponseEntity<>(_Interesse, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro ao registrar interesse do usuario "+user_id+" no pet "+pet_id);
            logger.error(String.valueOf(e));
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Deleta interesse pelo id")
    @DeleteMapping("/interesses/{id}")
    public ResponseEntity<HttpStatus> deleteInteressesById(@PathVariable("id") long id) {

        logger.info("Deletando interesse de id "+id);

        try {
            interesseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao deletar interesse de id "+id);
            logger.error(String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Deleta todos os interesses do usuario")
    @DeleteMapping("/interesses/usuario/{id}")
    public ResponseEntity<HttpStatus> deleteInteressesByUsuario(@PathVariable("id") long id) {
        logger.info("Deletando todos os interesse do usuário "+id);
        try {
            interesseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao deletar interesses do usuario "+id);
            logger.error(String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Deleta todos os interesses do pet")
    @DeleteMapping("/interesses/pet/{id}")
    public ResponseEntity<HttpStatus> deleteInteressesByPet(@PathVariable("id") long id) {
        logger.info("Deletando todos os interesse do pet "+id);
        try {
            interesseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao deletar interesses do pet "+id);
            logger.error(String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
