package com.esi.petperfeito.service;

import com.esi.petperfeito.model.Interesse;
import com.esi.petperfeito.model.InteresseForm;
import com.esi.petperfeito.repository.InteresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InteresseService {

    @Autowired
    private InteresseRepository interesseRepository;

    //total de pontos -> 160

    private int[] weights = new int[]{3, 2, 1, 2, 2, 1, 3, 2};
    private int totalWeight= 16;

    public void generateUserRating(Interesse interesse) {

        InteresseForm form = interesse.getFormulario();
        String[] results = (form.toString()).split(",");

        int notaFinal = 0;
        int notaParcial = 0;

        for (int i = 0; i<8; i++){
            notaParcial += Integer.parseInt(results[i]) * weights[i];
        }

        notaFinal = notaParcial / totalWeight;

        interesse.setNotaUsuario(notaFinal);

    }

}
