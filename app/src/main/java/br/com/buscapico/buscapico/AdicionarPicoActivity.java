package br.com.buscapico.buscapico;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import br.com.buscapico.buscapico.models.Endereco;
import br.com.buscapico.buscapico.models.Pico;

public class AdicionarPicoActivity extends AppCompatActivity implements View.OnClickListener {
    //CONSTANTS
    private static final int WRITE_PERMISSION = 0x01;
    private static final String TAG = "AdicionarPicoActivity";
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    // FIREBASE REFERENCES
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference imagesRef;
    private DatabaseReference skateSpotsReference = database.getReference("skateSpots");

    // Views
    private ImageView iviFoto;
    private EditText etePico;
    private EditText eteDescricao;
    private EditText eteTipo;
    private EditText eteRua;
    private EditText eteCidade;
    private EditText eteEstado;
    private RatingBar rbSeguranca;
    private RatingBar rbConservacao;
    private Button btSalvar;
    private FloatingActionButton fabAddSpotFoto;
    private ProgressBar pbFotoProgress;
    private Uri retornoStorage;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_pico);
        requestPermissions();
        findViews();
        setAction();
        setLocationManager();
    }


    // Encontra as views do layout
    private void findViews() {
        iviFoto = (ImageView) findViewById(R.id.ivi_foto);
        etePico = (EditText) findViewById(R.id.ete_pico);
        eteTipo = (EditText) findViewById(R.id.ete_tipo);
        eteDescricao = (EditText) findViewById(R.id.ete_descricao);
        eteRua = (EditText) findViewById(R.id.ete_rua);
        eteCidade = (EditText) findViewById(R.id.ete_cidade);
        eteEstado = (EditText) findViewById(R.id.ete_estado);
        rbSeguranca = (RatingBar) findViewById(R.id.rb_seguranca);
        rbConservacao = (RatingBar) findViewById(R.id.rb_conservacao);
        btSalvar = (Button) findViewById(R.id.bt_salvar);
        fabAddSpotFoto = (FloatingActionButton) findViewById(R.id.fab_add_spot_foto);
        pbFotoProgress = (ProgressBar) findViewById(R.id.pb_foto_progress);
    }

    // Define os listeners das ações
    private void setAction() {
        fabAddSpotFoto.setOnClickListener(this);
        btSalvar.setOnClickListener(this);
    }

    private void setLocationManager() {
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(AdicionarPicoActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(AdicionarPicoActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, WRITE_PERMISSION);


        }

        if (ContextCompat.checkSelfPermission(AdicionarPicoActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(AdicionarPicoActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000,
                    50f, mLocationListener);
        }

    }

    // Abre a galeria para o usuário selecionar uma foto
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Escolha uma imagem"), 1);
    }

    // Evento executado após o método startActivityForResult, quando o usuário já selecionou a imagem.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            iviFoto.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    // Solicita permissão para gravar arquivos no dispositivo
    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(AdicionarPicoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_PERMISSION);
        }

    }


    // Exibe mensagem para o usuário caso a permissão para gravar arquivos seja negada
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == WRITE_PERMISSION) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Write Permission Failed");
                Toast.makeText(this, "Permissão necessária para salvar imagens.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    // Inicia o fluxo para salvar o pico
    private void saveSpot() {
        if (validarPreenchimentoForm()) {
            uploadToStorage();
        }
    }

    // Após o upload da foto salva o pico no firebase
    private void saveSpotOnFirebase() {
        Endereco endereco = getEnderecoFromView();
        endereco.setLatitude(latitude);
        endereco.setLongitude(longitude);
        Pico pico = getSkateSpotFromView(endereco);
        if (retornoStorage != null) {
            skateSpot.setUrlFoto(retornoStorage.toString());

            skateSpotsReference.push().setValue(skateSpot);
            Log.d(TAG, "url storage: " + retornoStorage.toString());
            Toast.makeText(AddSkateSpotActivity.this, "Pico inserido com sucesso!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    // Recupera os valores do endereco digitados na view
    private Endereco getEnderecoFromView() {
        Endereco endereco = new Endereco();
        endereco.setEstado(eteEstado.getText().toString());
        endereco.setCidade(eteCidade.getText().toString());
        endereco.setRua(eteRua.getText().toString());
        return endereco;
    }

    // Recupera os valores do pico (skateSpot) digitados na view
    private Pico getSkateSpotFromView(Endereco endereco) {
        Pico pico = new Pico();
        pico.setUsuario(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        pico.setConservacao(rbConservacao.getRating());
        pico.setSeguranca(rbSeguranca.getRating());
        pico.setNota((rbConservacao.getRating() + rbSeguranca.getRating()) / 2);
        pico.setDescricao(eteDescricao.getText().toString());

        pico.setEndereco(endereco);
        pico.setNome(etePico.getText().toString());
        pico.setTipo(eteTipo.getText().toString());
        return pico;
    }

    // Faz o upload da imagem selecionada para o FirebaseStorage
    private void uploadToStorage() {
        iviFoto.setDrawingCacheEnabled(true);
        iviFoto.buildDrawingCache();
        Bitmap bitmap = iviFoto.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        Uri downloadUrl = null;
        imagesRef = storage.getReference(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());
        UploadTask uploadTask = imagesRef.child(String.valueOf(Calendar.getInstance().getTimeInMillis())).putBytes(data);

        pbFotoProgress.setVisibility(View.VISIBLE);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                pbFotoProgress.setVisibility(View.GONE);
                Toast.makeText(AddSkateSpotActivity.this, "Erro no upload. Verifique sua conexão",
                        Toast.LENGTH_SHORT).show();
                retornoStorage = null;
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pbFotoProgress.setVisibility(View.GONE);
                @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                Toast.makeText(AddSkateSpotActivity.this, "Upload Ok!",
                        Toast.LENGTH_SHORT).show();
                retornoStorage = downloadUrl;
                saveSpotOnFirebase();
            }
        });
    }

    // valida se todos os campos obrigatórios foram preenchidos
    private boolean validarPreenchimentoForm() {
        boolean valido = true;
        String pico = etePico.getText().toString();
        if (TextUtils.isEmpty(pico)) {
            etePico.setError("Campo Obrigatório!");
            valido = false;
        } else {
            etePico.setError(null);
        }

        String tipo = eteTipo.getText().toString();
        if (TextUtils.isEmpty(tipo)) {
            eteTipo.setError("Campo Obrigatório!");
            valido = false;
        } else {
            eteTipo.setError(null);
        }
        String descricao = eteDescricao.getText().toString();
        if (TextUtils.isEmpty(descricao)) {
            eteDescricao.setError("Campo Obrigatório!");
            valido = false;
        } else {
            eteDescricao.setError(null);
        }
        String rua = eteRua.getText().toString();
        if (TextUtils.isEmpty(rua)) {
            eteRua.setError("Campo Obrigatório!");
            valido = false;
        } else {
            eteRua.setError(null);
        }
        String cidade = eteCidade.getText().toString();
        if (TextUtils.isEmpty(tipo)) {
            eteCidade.setError("Campo Obrigatório!");
            valido = false;
        } else {
            eteCidade.setError(null);
        }
        String estado = eteEstado.getText().toString();
        if (TextUtils.isEmpty(tipo)) {
            eteEstado.setError("Campo Obrigatório!");
            valido = false;
        } else {
            eteEstado.setError(null);
        }

        return valido;
    }

    // Define as ações para cada evento por id
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_salvar) {
            saveSpot();
        }
        if (i == R.id.fab_add_spot_foto) {
            openGallery();
        }
    }
}
