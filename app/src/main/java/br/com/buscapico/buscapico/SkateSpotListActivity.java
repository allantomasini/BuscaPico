package br.com.buscapico.buscapico;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.buscapico.buscapico.adapters.SkateSpotAdapter;
import br.com.buscapico.buscapico.models.SkateSpot;

public class SkateSpotListActivity extends AppCompatActivity implements View.OnClickListener {
    //CONSTANTS
    private static final int WRITE_PERMISSION = 0x01;
    private static final String TAG = "SkateParksAct";


    private List<SkateSpot> skateSpots;
    private Toolbar toolbar;
    private FloatingActionButton fabAddSpot;
    private RecyclerView rviSpots;

    private double latitude;
    private double longitude;
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
    private LocationManager mLocationManager;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference spotsRef = database.getReference("skateSpots");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skate_spot_list);
//        skateSpots = MockDao.getSkateSpots();
        getSkateSpots();
        findViews();
        setActions();
        setRecyclerView();
        setLocationManager();
    }

    private void setLocationManager() {
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(SkateSpotListActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(SkateSpotListActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, WRITE_PERMISSION);


        }

        if (ContextCompat.checkSelfPermission(SkateSpotListActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(SkateSpotListActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000,
                    50f, mLocationListener);
        }
    }

    private void getSkateSpots() {
        spotsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                skateSpots = new ArrayList<SkateSpot>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    SkateSpot newSpot = postSnapshot.getValue(SkateSpot.class);
                    double distancia = calcularDistancia(newSpot.getEndereco().getLatitude()
                            , newSpot.getEndereco().getLongitude());
                    newSpot.getEndereco()
                            .setHaversine(distancia);
                    skateSpots.add(newSpot);
                    setRecyclerView();
                }
                Collections.sort(skateSpots, new Comparator<SkateSpot>(){
                    public int compare(SkateSpot obj1, SkateSpot obj2) {
                        // ## Ascending order
                        return Double.valueOf(obj1.getEndereco().getHaversine()).compareTo(obj2.getEndereco().getHaversine()); // To compare string values
                        // return Integer.valueOf(obj1.empId).compareTo(obj2.empId); // To compare integer values

                        // ## Descending order
                        // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
                        // return Integer.valueOf(obj2.empId).compareTo(obj1.empId); // To compare integer values
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private double calcularDistancia(double lat2, double lon2) {
        double radius = 6378137;   // approximate Earth radius, *in meters*
        double lat1 = latitude;
        double lon1 = longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return radius * c;
    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rviSpots = (RecyclerView) findViewById(R.id.rvi_skate_spot_list);
        fabAddSpot = (FloatingActionButton) findViewById(R.id.fab_add_skate_spot);
    }

    private void setActions() {
        fabAddSpot.setOnClickListener(this);
    }

    private void setRecyclerView() {
        rviSpots.setHasFixedSize(true);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rviSpots.setLayoutManager(mLayoutManager);

        if (skateSpots == null) {
            skateSpots = new ArrayList<SkateSpot>();
        }

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
        if (i == R.id.fab_add_skate_spot) {
            goToAddSkateSpot(v);
        }
    }
}
