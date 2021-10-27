package com.esi.petperfeito.model;

import javax.persistence.*;

@Entity
@Table(name = "ongs")
public class Ong {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "denominacao")
    private String denominacao;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cep")
    private String cep;

    @Column(name = "natureza")
    private String natureza;

    @Column(name = "area_atuacao")
    private String areaAtuacao;

    @Column(name = "data_fundacao")
    private String dataFundacao;

    public Ong() {

    }

    public Ong(String denominacao, String cnpj, String telefone, String cep, String natureza, String areaAtuacao, String dataFundacao) {
        this.denominacao = denominacao;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.cep = cep;
        this.natureza = natureza;
        this.areaAtuacao = areaAtuacao;
        this.dataFundacao = dataFundacao;
    }

    public String getDenominacao() {
        return denominacao;
    }

    public void setDenominacao(String denominacao) {
        this.denominacao = denominacao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public String getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(String dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    @Override
    public String toString() {
        return "Ong{" +
                "id=" + id +
                ", denominacao='" + denominacao + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", natureza='" + natureza + '\'' +
                ", areaAtuacao='" + areaAtuacao + '\'' +
                ", dataFundacao='" + dataFundacao + '\'' +
                '}';
    }
}