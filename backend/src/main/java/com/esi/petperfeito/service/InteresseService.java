package com.esi.petperfeito.service;

import com.esi.petperfeito.model.Interesse;
import com.esi.petperfeito.model.Avaliacao;
import com.esi.petperfeito.model.Pet;
import org.springframework.stereotype.Service;

@Service
public class InteresseService {

    public Integer generateUserRating(Pet pet, Avaliacao avaliacao) {

        Integer[] weights = collectPetWeights(pet);
        Integer totalWeights = 0;

        String[] results = (avaliacao.toString()).split(",");

        Integer notaFinal = 0;
        Integer notaParcial = 0;

        for (int i = 0; i<8; i++){
            notaParcial += Integer.parseInt(results[i]) * weights[i];
            totalWeights += weights[i];
        }

        notaFinal = notaParcial / totalWeights;
        return notaFinal;

    }

    private Integer[] collectPetWeights(Pet pet) {
        Integer[] weights = new Integer[8];

        weights[0] = pet.getPeso1();
        weights[1] = pet.getPeso2();
        weights[2] = pet.getPeso3();
        weights[3] = pet.getPeso4();
        weights[4] = pet.getPeso5();
        weights[5] = pet.getPeso6();
        weights[6] = pet.getPeso7();
        weights[7] = pet.getPeso8();

        return weights;
    }

}
