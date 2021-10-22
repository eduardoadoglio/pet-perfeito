package com.esi.petperfeito.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.esi.petperfeito.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByCep(String cep);
    List<Usuario> findByCpf(String cpf);
    List<Usuario> findByNameContaining(String nome);
}
