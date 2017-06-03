package br.com.buscapico.buscapico.models;

import java.io.Serializable;

/**
 * Created by Allan on 23/05/2017.
 */

public class Endereco implements Serializable {
    private String estado;
    private String cidade;
    private String rua;
    private String numero;
    private double latitude;
    private double longitude;
    private double haversine;

    public Endereco() {

    }


    public double getHaversine() {
        return haversine;
    }

    public void setHaversine(double haversine) {
        this.haversine = haversine;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "estado='" + estado + '\'' +
                ", cidade='" + cidade + '\'' +
                ", rua='" + rua + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
