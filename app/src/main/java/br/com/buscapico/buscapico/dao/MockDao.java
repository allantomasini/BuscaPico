package br.com.buscapico.buscapico.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.buscapico.buscapico.models.Endereco;
import br.com.buscapico.buscapico.models.SkateSpot;

/**
 * Created by Allan on 27/05/2017.
 */

public class MockDao {

    private static List<SkateSpot> skateSpots;

    public MockDao() {
    }

    public static List<SkateSpot> getSkateSpots() {

        String pistaDoGaucho = "https://i.ytimg.com/vi/EcIjIjt2QAQ/maxresdefault.jpg";
        String pistaDoAtletico = "http://campeonatosdeskate.com.br/wp-content/uploads/2015/12/pista-atletico-281215.jpg";
        String jardimAmbiental = "https://focanawebufpr.files.wordpress.com/2015/04/image-2.jpeg";
        String pistaCentral = "http://2.bp.blogspot.com/-wOBujt_ztMo/Tll19PNSYtI/AAAAAAAAEn0/2tkKO-dsY_k/s1600/missionarios%2Bb.JPG";


        skateSpots = new ArrayList<SkateSpot>();
        skateSpots.add(new SkateSpot("Pista do Gaúcho", new Endereco("Paraná", "Curitiba", "Praça do Redentor", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaDoGaucho));
        skateSpots.add(new SkateSpot("Pista do Atlético", new Endereco("Paraná", "Curitiba", "Praça Afonso Botelho", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaDoAtletico));
        skateSpots.add(new SkateSpot("Jardim Ambiental", new Endereco("Paraná", "Curitiba", "Rua Schiller", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, jardimAmbiental));
        skateSpots.add(new SkateSpot("Pista Central", new Endereco("Paraná", "São José do Pinhais", "Praça dos Missionários", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaCentral));

        skateSpots.add(new SkateSpot("Pista do Gaúcho", new Endereco("Paraná", "Curitiba", "Praça do Redentor", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaDoGaucho));
        skateSpots.add(new SkateSpot("Pista do Atlético", new Endereco("Paraná", "Curitiba", "Praça Afonso Botelho", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaDoAtletico));
        skateSpots.add(new SkateSpot("Jardim Ambiental", new Endereco("Paraná", "Curitiba", "Rua Schiller", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, jardimAmbiental));
        skateSpots.add(new SkateSpot("Pista Central", new Endereco("Paraná", "São José do Pinhais", "Praça dos Missionários", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaCentral));

        skateSpots.add(new SkateSpot("Pista do Gaúcho", new Endereco("Paraná", "Curitiba", "Praça do Redentor", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaDoGaucho));
        skateSpots.add(new SkateSpot("Pista do Atlético", new Endereco("Paraná", "Curitiba", "Praça Afonso Botelho", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaDoAtletico));
        skateSpots.add(new SkateSpot("Jardim Ambiental", new Endereco("Paraná", "Curitiba", "Rua Schiller", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, jardimAmbiental));
        skateSpots.add(new SkateSpot("Pista Central", new Endereco("Paraná", "São José do Pinhais", "Praça dos Missionários", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaCentral));


        return skateSpots;
    }
}
