package com.esi.petperfeito.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.esi.petperfeito.model.Ong;

public interface OngRepository extends JpaRepository<Ong, Long> {
    List<Ong> findByCpf(String cpf);
    List<Ong> findByNome(String nome);
}
