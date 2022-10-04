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

import com.esi.petperfeito.model.Usuario;
import com.esi.petperfeito.repository.UsuarioRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Operation(summary = "Retorna todos os usuarios")
    @GetMapping("/users")
    public ResponseEntity<List<Usuario>> getAllUsers(@RequestParam(required = false) String nome) {
        try {
            List<Usuario> users = new ArrayList<>();

            if (nome == null)
                users.addAll(usuarioRepository.findAll());
            else
                users.addAll(usuarioRepository.findByNome(nome));

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Busca usuario por id")
    @GetMapping("/users/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable("id") long id) {
        Optional<Usuario> usuarioData = usuarioRepository.findById(id);

        return usuarioData.map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Cria usuario")
    @PostMapping("/users")
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario) {
        try {
            Usuario _usuario = usuarioRepository
                    .save(new Usuario(usuario.getNome(), usuario.getCpf(), usuario.getTelefone(), usuario.getCep(), usuario.getDataNascimento()));
            return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Atualiza usuario")
    @PutMapping("/users/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable("id") long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioData = usuarioRepository.findById(id);

        if (usuarioData.isPresent()) {
            Usuario _usuario = usuarioData.get();
            _usuario.setNome(usuario.getNome());
            _usuario.setCpf(usuario.getCpf());
            _usuario.setTelefone(usuario.getTelefone());
            _usuario.setCep(usuario.getCep());
            _usuario.setDataNascimento(usuario.getDataNascimento());
            return new ResponseEntity<>(usuarioRepository.save(_usuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Deleta usuario")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            usuarioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Deleta todos os usuarios")
    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            usuarioRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
