package br.com.buscapico.buscapico.dao;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.buscapico.buscapico.models.Endereco;
import br.com.buscapico.buscapico.models.SkateSpot;

/**
 * Created by Allan on 28/05/2017.
 */

public class FirebaseDao {
    private static final String TAG = "FirebaseDao";
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference myRef = database.getReference("skateSpots");
    public FirebaseDao() {}

    public static void writeNewSkateSpot(SkateSpot skateSpot){
        skateSpot.setUsuario(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        myRef.child("skateSpots").push().setValue(skateSpot);
    }

    public static List<SkateSpot> getSkateSpots() {
        final List<SkateSpot> skateSpots = new ArrayList<SkateSpot>();
        return skateSpots;
    }
}
