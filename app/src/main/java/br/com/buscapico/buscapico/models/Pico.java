package br.com.buscapico.buscapico.models;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Allan on 23/05/2017.
 */

public class Pico implements Serializable {

    // Nome pelo qual o pico é conhecido
    private String nome;
    // Endereço da local
    private Endereco endereco;
    // Descrição da local
    private String descricao;
    // Nota da local de 1 a 5
    private float nota;
    // Nível de segurança da local de 1 a 5
    private float seguranca;
    // Nível de conservação da local de 1 a 5
    private float conservacao;
    // Foto do pico
    private Bitmap foto;
    //UrlFoto
    private String urlFoto;
    //Tipo de pico
    private String tipo;
    //Usuário que adicionou o pico
    private String usuario;


    public Pico() {
    }

    public Pico(String nome, Endereco endereco, String descricao, int nota, int conservacao, Bitmap foto) {
        this.nome = nome;
        this.endereco = endereco;
        this.descricao = descricao;
        this.nota = nota;
        this.conservacao = conservacao;
        this.foto = foto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap url) {
        this.foto = foto;
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

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public float getSeguranca() {
        return seguranca;
    }

    public void setSeguranca(float seguranca) {
        this.seguranca = seguranca;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public float getConservacao() {
        return conservacao;
    }

    public void setConservacao(float conservacao) {
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
                ", tipo='" + tipo + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getUrlFoto() {
        return urlFoto;
    }
}
