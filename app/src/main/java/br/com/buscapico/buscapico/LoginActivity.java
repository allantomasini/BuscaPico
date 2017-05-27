package br.com.buscapico.buscapico;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText etLogin;
    EditText etSenha;
    Button btEntrar;
    View mainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        setActions();
    }


    private void findViews() {
        etLogin = (EditText) findViewById(R.id.et_login);
        etSenha = (EditText) findViewById(R.id.et_senha);
        btEntrar = (Button) findViewById(R.id.bt_entrar);
        mainContent = (View) findViewById(R.id.main_content);
    }

    private void setActions() {
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = etLogin.getText().toString();
                String senha = etSenha.getText().toString();

                if (validateLogin(login, senha)) {
                    doLogin(v);
                } else {
                    showSnack();
                }

            }
        });
    }

    //Valida se o login e a senha estão corretos
    private boolean validateLogin(String login, String senha) {
        if("teste".equalsIgnoreCase(login)&&"teste".equalsIgnoreCase(senha)){
            return true;
        }
        return false;
    }

    //Realiza o login
    private void doLogin(View v) {
        Intent intent = new Intent(LoginActivity.this, SkateParkListActivity.class);
        startActivity(intent);
        finish();
    }

    //Mostra o snackbar no rodapé da tela
    private void showSnack(){
        Snackbar.make(mainContent, "Login ou senha inválidos", Snackbar.LENGTH_LONG)
                .setAction("", null)
                .show();
    }

}
