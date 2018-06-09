package br.com.buscapico.buscapico;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import br.com.buscapico.buscapico.models.Pico;

public class DetalhePicoActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_detalhe_pico);

        Pico pico = (Pico) getIntent().getBundleExtra("extra").getSerializable("skate_park");

        findViews();
        setActions(pico);
        setDetail(pico);
        validateTransitions(iviFoto);
    }

    // Define as views
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

    // Define a ação do botão flutuante que aciona o mapa
    private void setActions(Pico pico) {
        final double latitude = pico.getEndereco().getLatitude();
        final double longitude = pico.getEndereco().getLongitude();
        fabGoToPico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });
    }

    // Define os dados do detalhe
    private void setDetail(Pico pico) {
        String url = pico.getUrlFoto() == null ?
                "" : pico.getUrlFoto();
        Picasso.with(DetalhePicoActivity.this)
                .load(url)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.default_park)
                .into(iviFoto);

        etePico.setText(pico.getNome());
        eteDescricao.setText(pico.getDescricao());
        eteTipo.setText(pico.getTipo());
        eteRua.setText(pico.getEndereco().getRua());
        eteCidade.setText(pico.getEndereco().getCidade());
        eteEstado.setText(pico.getEndereco().getEstado());
        rbSeguranca.setRating(pico.getSeguranca());
        rbConservacao.setRating(pico.getConservacao());
    }

    // Define a transição
    private void validateTransitions(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setTransitionName("view");
        }
    }
}
