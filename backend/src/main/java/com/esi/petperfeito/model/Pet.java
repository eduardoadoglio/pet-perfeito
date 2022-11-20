package com.esi.petperfeito.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "pets")
public class Pet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ongs", referencedColumnName = "id")
    private Ong ong;

    @Column(name = "nome")
    private String nome;

    @Column(name = "status")
    private boolean status;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "especie")
    private String especie;

    @Column(name = "raca")
    private String raca;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "data_nascimento")
    private String dataNascimento;

    @Column(name = "peso1")
    private int peso1;

    @Column(name = "peso2")
    private int peso2;

    @Column(name = "peso3")
    private int peso3;

    @Column(name = "peso4")
    private int peso4;

    @Column(name = "peso5")
    private int peso5;

    @Column(name = "peso6")
    private int peso6;

    @Column(name = "peso7")
    private int peso7;

    @Column(name = "peso8")
    private int peso8;

    public Pet(){

    }

    public Pet(Ong ong, String nome, boolean status, String descricao, String especie, String raca, String sexo, String dataNascimento, int peso1, int peso2, int peso3, int peso4, int peso5, int peso6, int peso7, int peso8) {
        this.ong = ong;
        this.nome = nome;
        this.status = status;
        this.descricao = descricao;
        this.especie = especie;
        this.raca = raca;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.peso1 = peso1;
        this.peso2 = peso2;
        this.peso3 = peso3;
        this.peso4 = peso4;
        this.peso5 = peso5;
        this.peso6 = peso6;
        this.peso7 = peso7;
        this.peso8 = peso8;
    }

    public Ong getOng() {
        return ong;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public long getId() {
        return id;
    }

    public int getPeso1() {
        return peso1;
    }

    public void setPeso1(int peso1) {
        this.peso1 = peso1;
    }

    public int getPeso2() {
        return peso2;
    }

    public void setPeso2(int peso2) {
        this.peso2 = peso2;
    }

    public int getPeso3() {
        return peso3;
    }

    public void setPeso3(int peso3) {
        this.peso3 = peso3;
    }

    public int getPeso4() {
        return peso4;
    }

    public void setPeso4(int peso4) {
        this.peso4 = peso4;
    }

    public int getPeso5() {
        return peso5;
    }

    public void setPeso5(int peso5) {
        this.peso5 = peso5;
    }

    public int getPeso6() {
        return peso6;
    }

    public void setPeso6(int peso6) {
        this.peso6 = peso6;
    }

    public int getPeso7() {
        return peso7;
    }

    public void setPeso7(int peso7) {
        this.peso7 = peso7;
    }

    public int getPeso8() {
        return peso8;
    }

    public void setPeso8(int peso8) {
        this.peso8 = peso8;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", ongId='" + ong.getId() + '\'' +
                ", nome='" + nome + '\'' +
                ", status='" + (status ? "disponível" : "indisponível") + '\'' +
                ", descricao='" + descricao + '\'' +
                ", especie='" + especie + '\'' +
                ", raça='" + raca + '\'' +
                ", sexo='" + sexo + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                '}';
    }

}