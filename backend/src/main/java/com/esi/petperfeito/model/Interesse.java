package com.esi.petperfeito.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "interesses")
public class Interesse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "pets", referencedColumnName = "id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "usuarios", referencedColumnName = "id")
    private Usuario usuario;

    public Interesse(Pet pet, Usuario usuario) {
        this.pet = pet;
        this.usuario = usuario;
    }

    public Interesse() {

    }

    public long getId() {
        return id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Interesse{" +
                "pet=" + pet +
                ", usuario=" + usuario +
                '}';
    }
}
