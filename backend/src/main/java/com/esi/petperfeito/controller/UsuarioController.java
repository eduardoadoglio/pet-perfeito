package com.esi.petperfeito.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.esi.petperfeito.model.Avaliacao;
import com.esi.petperfeito.repository.AvaliacaoRepository;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esi.petperfeito.model.Usuario;
import com.esi.petperfeito.repository.UsuarioRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(InteresseController.class);

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    @Operation(summary = "Retorna todos os usuarios")
    @GetMapping("/users")
    public ResponseEntity<List<Usuario>> getAllUsers() {

        logger.info("Obtendo usuários cadastrados.");

        try {
            List<Usuario> users = new ArrayList<>();
            users.addAll(usuarioRepository.findAll());

            if (users.isEmpty()) {
                logger.warn("Nenhum usuário encontrado.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao obter usuarios.");
            logger.error(String.valueOf(e));
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Busca usuario por id")
    @GetMapping("/users/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable("id") long id) {

        logger.info("Buscando usuário de id "+id);

        Optional<Usuario> usuarioData = usuarioRepository.findById(id);

        return usuarioData.map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Cria usuario")
    @PostMapping("/users")
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario) {

        logger.info("Cadastrando usuário "+usuario.getNome());

        try {
            Usuario _usuario = usuarioRepository
                    .save(new Usuario(
                            usuario.getNome(),
                            usuario.getCpf(),
                            usuario.getTelefone(),
                            usuario.getCep(),
                            usuario.getDataNascimento()
                    ));
            return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro ao cadastrar usuário "+usuario.getNome());
            logger.error(String.valueOf(e));
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Cria formulario do usuario")
    @PostMapping("/user/{id}/form")
    public ResponseEntity<Usuario> createUserForm(@RequestBody Avaliacao avaliacao, @PathVariable("id") long id) {

        logger.info("Atualizando cadastro do usuário "+id);

        Optional<Usuario> usuarioData = usuarioRepository.findById(id);

        if (usuarioData.isPresent()) {
            try {

                Avaliacao form = new Avaliacao();

                try {
                    form = avaliacaoRepository.save(avaliacao);
                } catch (Exception e) {
                    logger.error("Erro ao tentar salvar avaliação "+avaliacao.getId());
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }

                Usuario _usuario = usuarioData.get();
                _usuario.setAvaliacao(avaliacao);
                return new ResponseEntity<>(usuarioRepository.save(_usuario), HttpStatus.OK);
            }
            catch (Exception e){
                logger.error("Erro ao atualizar dados do usuario de id "+id);
                logger.error(String.valueOf(e));
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            logger.error("Erro ao atualizar cadastro do usuário "+id+", não encontrado.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Atualiza usuario")
    @PutMapping("/users/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable("id") long id, @RequestBody Usuario usuario) {

        logger.info("Atualizando cadastro do usuário "+id);

        Optional<Usuario> usuarioData = usuarioRepository.findById(id);

        if (usuarioData.isPresent()) {
            try {
                Usuario _usuario = usuarioData.get();
                _usuario.setNome(usuario.getNome());
                _usuario.setCpf(usuario.getCpf());
                _usuario.setTelefone(usuario.getTelefone());
                _usuario.setCep(usuario.getCep());
                _usuario.setDataNascimento(usuario.getDataNascimento());
                _usuario.setReturnFlag(usuario.getReturnFlag());
                _usuario.setAvaliacao(usuario.getAvaliacao());
                return new ResponseEntity<>(usuarioRepository.save(_usuario), HttpStatus.OK);
            }
            catch (Exception e){
                logger.error("Erro ao atualizar dados do usuario "+usuario.getNome());
                logger.error(String.valueOf(e));
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            logger.error("Erro ao atualizar cadastro do usuário "+id+", não encontrado.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Deleta usuario")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {

        logger.info("Deletando cadastro do usuário "+id);

        try {
            usuarioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao remover usuário "+id);
            logger.error(String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
