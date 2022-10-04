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

import com.esi.petperfeito.model.Ong;
import com.esi.petperfeito.repository.OngRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class OngController {

    private static final Logger logger = LoggerFactory.getLogger(InteresseController.class);

    @Autowired
    OngRepository ongRepository;

    @Operation(summary = "Retorna todas as ongs")
    @GetMapping("/ongs")
    public ResponseEntity<List<Ong>> getAllOngs(@RequestParam(required = false) String denominacao) {

        logger.info("Obtendo informações de todas as ONGs cadastradas");

        try {
            List<Ong> ongs = new ArrayList<>();

            if (denominacao == null)
                ongs.addAll(ongRepository.findAll());
            else
                ongs.addAll(ongRepository.findByDenominacao(denominacao));

            if (ongs.isEmpty()) {
                logger.warn("Nenhuma ONG encontrada na base de dados.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(ongs, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao obter todas as ONGs cadastradas: "+e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Busca ong por id")
    @GetMapping("/ongs/{id}")
    public ResponseEntity<Ong> getOngById(@PathVariable("id") long id) {
        logger.info("Obtendo informações da ONG "+id);

        Optional<Ong> ongData = ongRepository.findById(id);

        return ongData.map(ong -> new ResponseEntity<>(ong, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Cria ong")
    @PostMapping("/ongs")
    public ResponseEntity<Ong> createOng(@RequestBody Ong ong) {
        logger.info("Criando ONG "+ong.getDenominacao());
        try {
            Ong _ong = ongRepository
                    .save(new Ong(ong.getDenominacao(), ong.getCnpj(), ong.getTelefone(), ong.getCep(), ong.getNatureza(), ong.getAreaAtuacao(), ong.getDataFundacao()));
            return new ResponseEntity<>(_ong, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Não foi possível registrar ONG "+ong.getDenominacao());
            logger.error("Erro: "+e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Atualiza ong")
    @PutMapping("/ongs/{id}")
    public ResponseEntity<Ong> updateOng(@PathVariable("id") long id, @RequestBody Ong ong) {

        logger.info("Atualizando ONG "+ong.getDenominacao());

        Optional<Ong> ongData = ongRepository.findById(id);

        try {

            if (ongData.isPresent()) {
                Ong _ong = ongData.get();
                _ong.setDenominacao(ong.getDenominacao());
                _ong.setCnpj(ong.getCnpj());
                _ong.setTelefone(ong.getTelefone());
                _ong.setCep(ong.getCep());
                _ong.setNatureza(ong.getNatureza());
                _ong.setAreaAtuacao(ong.getAreaAtuacao());
                _ong.setDataFundacao(ong.getDataFundacao());
                return new ResponseEntity<>(ongRepository.save(_ong), HttpStatus.OK);
            } else {
                logger.warn("Não foi possível encontrar a ONG " + ong.getDenominacao());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            logger.error("Não foi possível atualizar a ONG "+ong.getDenominacao());
            logger.error("Erro: "+e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Deleta ong por id")
    @DeleteMapping("/ongs/{id}")
    public ResponseEntity<HttpStatus> deleteOng(@PathVariable("id") long id) {

        logger.info("Deletando ONG " + id);

        try {
            ongRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Não foi possível deletar a ONG " + id);
            logger.error("Erro: " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
