package com.esi.petperfeito.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "interessesForm")
public class InteresseForm implements Serializable {

    public InteresseForm(int pergunta1, int pergunta2, int pergunta3, int pergunta4, int pergunta5, int pergunta6, int pergunta7, int pergunta8) {
        this.pergunta1 = pergunta1;
        this.pergunta2 = pergunta2;
        this.pergunta3 = pergunta3;
        this.pergunta4 = pergunta4;
        this.pergunta5 = pergunta5;
        this.pergunta6 = pergunta6;
        this.pergunta7 = pergunta7;
        this.pergunta8 = pergunta8;
    }

    public InteresseForm() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "pergunta1")
    private int pergunta1;

    @Column(name = "pergunta2")
    private int pergunta2;

    @Column(name = "pergunta3")
    private int pergunta3;

    @Column(name = "pergunta4")
    private int pergunta4;

    @Column(name = "pergunta5")
    private int pergunta5;

    @Column(name = "pergunta6")
    private int pergunta6;

    @Column(name = "pergunta7")
    private int pergunta7;

    @Column(name = "pergunta8")
    private int pergunta8;

    public long getId() {
        return id;
    }

    public int getPergunta1() {
        return pergunta1;
    }

    public void setPergunta1(int pergunta1) {
        this.pergunta1 = pergunta1;
    }

    public int getPergunta2() {
        return pergunta2;
    }

    public void setPergunta2(int pergunta2) {
        this.pergunta2 = pergunta2;
    }

    public int getPergunta3() {
        return pergunta3;
    }

    public void setPergunta3(int pergunta3) {
        this.pergunta3 = pergunta3;
    }

    public int getPergunta4() {
        return pergunta4;
    }

    public void setPergunta4(int pergunta4) {
        this.pergunta4 = pergunta4;
    }

    public int getPergunta5() {
        return pergunta5;
    }

    public void setPergunta5(int pergunta5) {
        this.pergunta5 = pergunta5;
    }

    public int getPergunta6() {
        return pergunta6;
    }

    public void setPergunta6(int pergunta6) {
        this.pergunta6 = pergunta6;
    }

    public int getPergunta7() {
        return pergunta7;
    }

    public void setPergunta7(int pergunta7) {
        this.pergunta7 = pergunta7;
    }

    public int getPergunta8() {
        return pergunta8;
    }

    public void setPergunta8(int pergunta8) {
        this.pergunta8 = pergunta8;
    }

    @Override
    public String toString() {
        return pergunta1 +
                "," + pergunta2 +
                "," + pergunta3 +
                "," + pergunta4 +
                "," + pergunta5 +
                "," + pergunta6 +
                "," + pergunta7 +
                "," + pergunta8;
    }
}
