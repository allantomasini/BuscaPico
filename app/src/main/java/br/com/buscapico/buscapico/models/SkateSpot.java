package br.com.buscapico.buscapico.models;

import java.io.Serializable;

/**
 * Created by Allan on 23/05/2017.
 */

public class SkateSpot implements Serializable {

    // Nome pelo qual o pico é conhecido
    private String nome;
    // Endereço da local
    private Endereco endereco;
    // Descrição da local
    private String descricao;
    // Nota da local de 1 a 5
    private int nota;
    // Nível de segurança da local de 1 a 5
    private int seguranca;
    // Nível de conservação da local de 1 a 5
    private int conservacao;
    // URL da foto do pico
    private String url;
    //Tipo de pico
    private String tipo;
    //Usuário que adicionou o pico
    private String usuario;

    public SkateSpot() {
    }

    public SkateSpot(String nome, Endereco endereco, String descricao, int nota, int conservacao, String url) {
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getConservacao() {
        return conservacao;
    }

    public void setConservacao(int conservacao) {
        this.conservacao = conservacao;
    }

    @Override
    public String toString() {
        return "SkateSpot{" +
                "nome='" + nome + '\'' +
                ", endereco=" + endereco +
                ", descricao='" + descricao + '\'' +
                ", nota=" + nota +
                ", seguranca=" + seguranca +
                ", conservacao=" + conservacao +
                ", url='" + url + '\'' +
                ", tipo='" + tipo + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}
