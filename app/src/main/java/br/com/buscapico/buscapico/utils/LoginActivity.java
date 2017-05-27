package br.com.buscapico.buscapico.utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.buscapico.buscapico.R;

public class LoginActivity extends AppCompatActivity {

    EditText etLogin;
    EditText etSenha;
    Button btEntrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
        setActions();
    }


    private void findViews() {
        etLogin = (EditText)findViewById(R.id.et_login);
        etSenha = (EditText)findViewById(R.id.et_senha);
        btEntrar = (Button) findViewById(R.id.bt_entrar);
    }
    private void setActions() {
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SkateParkListActivity.class);
                startActivity(intent);

            }
        });
    }
    private void doLogin(){
        Intent intent = new Intent(
                this,
                null);
    }
}
