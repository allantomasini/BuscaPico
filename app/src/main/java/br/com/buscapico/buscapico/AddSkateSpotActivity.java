package br.com.buscapico.buscapico;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

public class AddSkateSpotActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int WRITE_PERMISSION = 0x01;
    private static final String TAG = "AddSkateSpotActivity";
    private ImageView iviFoto;
    private EditText etePico;
    private EditText eteTipo;
    private EditText eteCidade;
    private EditText eteEstado;
    private RatingBar rbSeguranca;
    private RatingBar rbConservacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_skate_spot);
        requestWritePermission();
        findViews();
        setAction();
    }

    private void findViews() {
        iviFoto = (ImageView) findViewById(R.id.ivi_foto);
        etePico = (EditText) findViewById(R.id.ete_pico);
        eteTipo = (EditText) findViewById(R.id.ete_tipo);
        eteCidade = (EditText) findViewById(R.id.ete_cidade);
        eteEstado = (EditText) findViewById(R.id.ete_estado);
        rbSeguranca = (RatingBar) findViewById(R.id.rb_seguranca);
        rbConservacao = (RatingBar) findViewById(R.id.rb_conservacao);
    }

    private void setAction() {
        iviFoto.setOnClickListener(this);
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Escolha uma imagem"), 1);
    }

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

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == WRITE_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Write Permission Failed");
                Toast.makeText(this, "You must allow permission write external storage to your mobile device.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void requestWritePermission() {
        if (ContextCompat.checkSelfPermission(AddSkateSpotActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_PERMISSION);
        }
    }

    private void saveSpot() {

    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_salvar) {
            saveSpot();
        }
        if (i == R.id.ivi_foto) {
            openGallery();
        }
    }
}
