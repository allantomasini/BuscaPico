package br.com.buscapico.buscapico;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import br.com.buscapico.buscapico.adapters.SkateParkAdapter;
import br.com.buscapico.buscapico.dao.MockDao;
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
        skateParks = MockDao.getSkateParks();
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



}
