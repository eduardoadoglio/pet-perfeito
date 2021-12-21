package com.esi.petperfeito.repository;

import java.util.List;
import java.util.Optional;

import com.esi.petperfeito.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import com.esi.petperfeito.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByCpf(String cpf);
    List<Usuario> findByNome(String nome);

    Optional<Usuario> findById(Long id);
}
