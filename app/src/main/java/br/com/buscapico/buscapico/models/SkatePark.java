package br.com.buscapico.buscapico.models;

import java.io.Serializable;

/**
 * Created by Allan on 23/05/2017.
 */

public class SkatePark implements Serializable {

    // Nome pelo qual a pista é conhecida
    private String nome;
    // Endereço da pista
    private Endereco endereco;
    // Descrição da pista
    private String descricao;
    // Nota da pista de 1 a 5
    private int nota;
    // Nível de segurança da pista de 1 a 5
    private int seguranca;
    // Nível de conservação da pista de 1 a 5
    private int conservacao;
    // URL da foto da pista
    private String url;
    //Tipo de pista
    private String tipo;

    public SkatePark() {
    }

    public SkatePark(String nome, Endereco endereco, String descricao, int nota, int conservacao, String url) {
        this.nome = nome;
        this.endereco = endereco;
        this.descricao = descricao;
        this.nota = nota;
        this.conservacao = conservacao;
        this.url = url;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getSeguranca() {
        return seguranca;
    }

    public void setSeguranca(int seguranca) {
        this.seguranca = seguranca;
    }

    @Override
    public String toString() {
        return "SkatePark{" +
                "nome='" + nome + '\'' +
                ", endereco=" + endereco +
                ", descricao='" + descricao + '\'' +
                ", nota=" + nota +
                ", seguranca=" + seguranca +
                ", conservacao=" + conservacao +
                '}';
    }

    public int getConservacao() {
        return conservacao;
    }

    public void setConservacao(int conservacao) {
        this.conservacao = conservacao;
    }


}
