package br.com.buscapico.buscapico.utils;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import br.com.buscapico.buscapico.R;
import br.com.buscapico.buscapico.adapters.SkateParkAdapter;
import br.com.buscapico.buscapico.models.Endereco;
import br.com.buscapico.buscapico.models.SkatePark;

public class SkateParkListActivity extends AppCompatActivity {
    private List<SkatePark> skateParks;
    private static final String TAG = "SkateParksAct";
    private Toolbar toolbar;
    private FloatingActionButton facAddPark;
    private RecyclerView rviParks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skate_park_list);
        getSkateParks();
        findViews();
        setRecyclerView();
    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rviParks = (RecyclerView) findViewById(R.id.rvi_skate_park_list);
        facAddPark = (FloatingActionButton) findViewById(R.id.facAddSkatePark);
    }

    private void setRecyclerView() {
        rviParks.setHasFixedSize(true);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rviParks.setLayoutManager(mLayoutManager);

        SkateParkAdapter skateParkAdapter = new SkateParkAdapter(skateParks, SkateParkListActivity.this);
        skateParkAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = rviParks.getChildLayoutPosition(view);
                ImageView img = (ImageView) view.findViewById(R.id.ivi_foto);
                Bundle bundle = new Bundle();
//                bundle.putSerializable("skate", skateParks.get(position));
//                goToPlaySong(img, bundle);
            }
        });

        rviParks.setAdapter(skateParkAdapter);
        rviParks.setItemAnimator(new DefaultItemAnimator());
    }


    private void getSkateParks() {

        String pistaDoGaucho = "https://i.ytimg.com/vi/EcIjIjt2QAQ/maxresdefault.jpg";
        String pistaDoAtletico = "http://campeonatosdeskate.com.br/wp-content/uploads/2015/12/pista-atletico-281215.jpg";
        String jardimAmbiental = "https://focanawebufpr.files.wordpress.com/2015/04/image-2.jpeg";
        String pistaCentral = "http://2.bp.blogspot.com/-wOBujt_ztMo/Tll19PNSYtI/AAAAAAAAEn0/2tkKO-dsY_k/s1600/missionarios%2Bb.JPG";
        String pistaCreche = "https://i.ytimg.com/vi/EcIjIjt2QAQ/maxresdefault.jpg";


        skateParks = new ArrayList<SkatePark>();
        skateParks.add(new SkatePark("Pista do Gaúcho", new Endereco("Paraná", "Curitiba", "Praça do Redentor", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaDoGaucho));
        skateParks.add(new SkatePark("Pista do Atlético", new Endereco("Paraná", "Curitiba", "Praça Afonso Botelho", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaDoAtletico));
        skateParks.add(new SkatePark("Jardim Ambiental", new Endereco("Paraná", "Curitiba", "Rua Schiller", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, jardimAmbiental));
        skateParks.add(new SkatePark("Pista Central", new Endereco("Paraná", "São José do Pinhais", "Praça dos Missionários", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaCentral));
        skateParks.add(new SkatePark("Pista da Creche", new Endereco("Paraná", "São José do Pinhais", " R. Des. Ernâni Almeida de Abreu", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaCreche));
        skateParks.add(new SkatePark("Pista do Gaúcho", new Endereco("Paraná", "Curitiba", "Praça do Redentor", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaDoGaucho));
        skateParks.add(new SkatePark("Pista do Atlético", new Endereco("Paraná", "Curitiba", "Praça Afonso Botelho", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaDoAtletico));
        skateParks.add(new SkatePark("Jardim Ambiental", new Endereco("Paraná", "Curitiba", "Rua Schiller", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, jardimAmbiental));
        skateParks.add(new SkatePark("Pista Central", new Endereco("Paraná", "São José do Pinhais", "Praça dos Missionários", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaCentral));
        skateParks.add(new SkatePark("Pista da Creche", new Endereco("Paraná", "São José do Pinhais", " R. Des. Ernâni Almeida de Abreu", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaCreche));
        skateParks.add(new SkatePark("Pista do Gaúcho", new Endereco("Paraná", "Curitiba", "Praça do Redentor", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaDoGaucho));
        skateParks.add(new SkatePark("Pista do Atlético", new Endereco("Paraná", "Curitiba", "Praça Afonso Botelho", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaDoAtletico));
        skateParks.add(new SkatePark("Jardim Ambiental", new Endereco("Paraná", "Curitiba", "Rua Schiller", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, jardimAmbiental));
        skateParks.add(new SkatePark("Pista Central", new Endereco("Paraná", "São José do Pinhais", "Praça dos Missionários", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaCentral));
        skateParks.add(new SkatePark("Pista da Creche", new Endereco("Paraná", "São José do Pinhais", " R. Des. Ernâni Almeida de Abreu", "S/N"), "Uma das pistas mais antigas de Curitiba", 5, 5, pistaCreche));


    }
}
