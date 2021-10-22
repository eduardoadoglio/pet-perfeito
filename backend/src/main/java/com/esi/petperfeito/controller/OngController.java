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

import com.esi.petperfeito.model.Ong;
import com.esi.petperfeito.repository.OngRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class OngController {

    @Autowired
    OngRepository ongRepository;

    @GetMapping("/ongs")
    public ResponseEntity<List<Ong>> getAllOngs(@RequestParam(required = false) String nome) {
        try {
            List<Ong> ongs = new ArrayList<Ong>();

            if (nome == null)
                ongRepository.findAll().forEach(ongs::add);
            else
                ongRepository.findByNome(nome).forEach(ongs::add);

            if (ongs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(ongs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ongs/{id}")
    public ResponseEntity<Ong> getOngById(@PathVariable("id") long id) {
        Optional<Ong> ongData = ongRepository.findById(id);

        if (ongData.isPresent()) {
            return new ResponseEntity<>(ongData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/ongs")
    public ResponseEntity<Ong> createOng(@RequestBody Ong ong) {
        try {
            Ong _ong = ongRepository
                    .save(new Ong(ong.getNome(), ong.getCpf(), ong.getTelefone(), ong.getCep(), Ong.getDataNascimento()));
            return new ResponseEntity<>(_ong, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ongs/{id}")
    public ResponseEntity<Ong> updateOng(@PathVariable("id") long id, @RequestBody Ong ong) {
        Optional<Ong> ongData = ongRepository.findById(id);

        if (ongData.isPresent()) {
            Ong _ong = ongData.get();
            _ong.setNome(ong.getNome());
            _ong.setCpf(ong.getCpf());
            _ong.setTelefone(ong.getTelefone());
            _ong.setCep(ong.getCep());
            _ong.setDataNascimento(ong.getDataNascimento());
            return new ResponseEntity<>(ongRepository.save(_ong), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/ongs/{id}")
    public ResponseEntity<HttpStatus> deleteOng(@PathVariable("id") long id) {
        try {
            ongRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/ongs")
    public ResponseEntity<HttpStatus> deleteAllOngs() {
        try {
            ongRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
