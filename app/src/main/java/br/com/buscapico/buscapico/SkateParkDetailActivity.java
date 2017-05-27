package br.com.buscapico.buscapico;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.buscapico.buscapico.models.SkatePark;

public class SkateParkDetailActivity extends AppCompatActivity {

    private ImageView iviFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skate_park_detail);

        SkatePark skatePark = (SkatePark) getIntent().getBundleExtra("extra").getSerializable("skate_park");

        findViews();
        setDetail(skatePark);
        validateTransitions(iviFoto);


    }

    private void findViews() {
        iviFoto = (ImageView) findViewById(R.id.ivi_foto);
    }

    private void setDetail(SkatePark skatePark) {
        String url = skatePark.getUrl() == null ?
                "" : skatePark.getUrl();
        Picasso.with(SkateParkDetailActivity.this)
                .load(url)
                .placeholder(R.drawable.default_park)
                .error(R.drawable.default_park)
                .into(iviFoto);
    }

    private void validateTransitions(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setTransitionName("view");
        }
    }


}
