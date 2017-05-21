package br.com.buscapico.buscapico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etLogin;
    EditText etSenha;
    Button btEntrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


            }
        });
    }
    private void doLogin(){
        Intent intent = new Intent(
                this,
                null);
    }
}
