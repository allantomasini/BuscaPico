package br.com.buscapico.buscapico;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import br.com.buscapico.buscapico.models.SkateSpot;

public class SkateSpotDetailActivity extends AppCompatActivity {

    private ImageView iviFoto;
    private EditText etePico;
    private EditText eteDescricao;
    private EditText eteTipo;
    private EditText eteRua;
    private EditText eteCidade;
    private EditText eteEstado;
    private RatingBar rbSeguranca;
    private RatingBar rbConservacao;
    private FloatingActionButton fabGoToPico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skate_spot_detail);

        SkateSpot skateSpot = (SkateSpot) getIntent().getBundleExtra("extra").getSerializable("skate_park");

        findViews();
        setActions(skateSpot);
        setDetail(skateSpot);
        validateTransitions(iviFoto);



    }


    private void findViews() {
        iviFoto = (ImageView) findViewById(R.id.ivi_foto);
        etePico = (EditText) findViewById(R.id.ete_pico);
        eteDescricao = (EditText) findViewById(R.id.ete_descricao);
        eteTipo = (EditText) findViewById(R.id.ete_tipo);
        eteRua = (EditText) findViewById(R.id.ete_rua);
        eteCidade = (EditText) findViewById(R.id.ete_cidade);
        eteEstado = (EditText) findViewById(R.id.ete_estado);
        rbSeguranca = (RatingBar) findViewById(R.id.rb_seguranca);
        rbConservacao = (RatingBar) findViewById(R.id.rb_conservacao);
        fabGoToPico = (FloatingActionButton) findViewById(R.id.fab_go_to_pico);
    }
    private void setActions(SkateSpot skateSpot) {
        final double latitude = skateSpot.getEndereco().getLatitude();
        final double longitude = skateSpot.getEndereco().getLongitude();
        fabGoToPico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f",latitude , longitude);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });
    }


    private void setDetail(SkateSpot skateSpot) {
        String url = skateSpot.getUrlFoto() == null ?
                "" : skateSpot.getUrlFoto();
        Picasso.with(SkateSpotDetailActivity.this)
                .load(url)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.default_park)
                .into(iviFoto);

        etePico.setText(skateSpot.getNome());
        eteDescricao.setText(skateSpot.getDescricao());
        eteTipo.setText(skateSpot.getTipo());
        eteRua.setText(skateSpot.getEndereco().getRua());
        eteCidade.setText(skateSpot.getEndereco().getCidade());
        eteEstado.setText(skateSpot.getEndereco().getEstado());
        rbSeguranca.setRating(skateSpot.getSeguranca());
        rbConservacao.setRating(skateSpot.getConservacao());
    }

    private void validateTransitions(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setTransitionName("view");
        }
    }


}
