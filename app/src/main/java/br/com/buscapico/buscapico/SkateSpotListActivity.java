package br.com.buscapico.buscapico;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import br.com.buscapico.buscapico.adapters.SkateSpotAdapter;
import br.com.buscapico.buscapico.dao.MockDao;
import br.com.buscapico.buscapico.models.SkateSpot;

public class SkateSpotListActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SkateParksAct";
    private List<SkateSpot> skateSpots;
    private Toolbar toolbar;
    private FloatingActionButton fabAddSpot;
    private RecyclerView rviSpots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skate_spot_list);
        skateSpots = MockDao.getSkateSpots();
        findViews();
        setActions();
        setRecyclerView();
    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rviSpots = (RecyclerView) findViewById(R.id.rvi_skate_spot_list);
        fabAddSpot = (FloatingActionButton) findViewById(R.id.fac_add_skate_spot);
    }

    private void setActions() {
        fabAddSpot.setOnClickListener(this);
    }

    private void setRecyclerView() {
        rviSpots.setHasFixedSize(true);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rviSpots.setLayoutManager(mLayoutManager);

        SkateSpotAdapter skateSpotAdapter = new SkateSpotAdapter(skateSpots, SkateSpotListActivity.this);
        skateSpotAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = rviSpots.getChildLayoutPosition(view);
                ImageView img = (ImageView) view.findViewById(R.id.ivi_foto);
                Bundle bundle = new Bundle();
                bundle.putSerializable("skate_park", skateSpots.get(position));
                goToSkateSpotDetail(img, bundle);
            }
        });

        rviSpots.setAdapter(skateSpotAdapter);
        rviSpots.setItemAnimator(new DefaultItemAnimator());
    }


    private void goToSkateSpotDetail(View view, Bundle bundle) {
        Intent intent = new Intent(SkateSpotListActivity.this, SkateSpotDetailActivity.class);
        if (bundle != null) {
            intent.putExtra("extra", bundle);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Slide());
            ActivityOptions options = view != null ?
                    ActivityOptions.makeSceneTransitionAnimation(this, view, "view") :
                    ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(intent, options.toBundle());

        } else {
            startActivity(intent);
        }
    }

    private void goToAddSkateSpot(View view) {
        Intent intent = new Intent(SkateSpotListActivity.this, AddSkateSpotActivity.class);
        startActivity(intent);
    }


    private void setToolbar() {
        toolbar.setTitle("Pistas Mais Próximas");
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.fac_add_skate_spot) {
            goToAddSkateSpot(v);
        }
    }
}
