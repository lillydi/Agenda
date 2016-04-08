package com.cacere.lillydi.agenda.modelo;

import java.io.Serializable;

/**
 * Created by lillydi on 04/04/2016.
 */
public class Aluno implements Serializable{

    private Long id;

    private String nome;

    private String endereco;

    private String email;

    private String telefone;

    private Double nota;

    public Aluno() {
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + " - " + nome +"\n Nota: " + nota.toString();
    }
}
