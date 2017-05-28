package br.com.buscapico.buscapico;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";

    //Atributos das Views
    private EditText etEmail;
    private EditText etSenha;
    private Button btEntrar;
    private View mainContent;

    //Instância do FirebaseAuth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeAuth();
        mAuth.signOut();
        findViews();
        setActions();
    }

    //Verifica se
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            goToSkateParkList();
        }
    }

    //Inicializa a instância do FirebaseAuth
    private void initializeAuth() {
        mAuth = FirebaseAuth.getInstance();
    }

    // Encontra as Views
    private void findViews() {
        etEmail = (EditText) findViewById(R.id.et_email);
        etSenha = (EditText) findViewById(R.id.et_senha);
        btEntrar = (Button) findViewById(R.id.bt_entrar);
        mainContent = (View) findViewById(R.id.main_content);
    }

    // Inicializa listeners dos botões
    private void setActions() {
        btEntrar.setOnClickListener(this);
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validarPreenchimentoForm()) {
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Se o login estiver ok, vai para a tela principal
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            goToSkateParkList();
                        } else {
                            // Se o login falhar, mostra mensagem ao usuário
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Login ou senha inválidos",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //
    private boolean validarPreenchimentoForm() {
        boolean valido = true;

        String email = etEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Campo Obrigatório!");
            valido = false;
        } else {
            etEmail.setError(null);
        }

        String senha = etSenha.getText().toString();
        if (TextUtils.isEmpty(senha)) {
            etSenha.setError("Required.");
            valido = false;
        } else {
            etSenha.setError(null);
        }

        return valido;
    }

    //Vai para a tela principal SkateParkList
    private void goToSkateParkList() {
        Intent intent = new Intent(LoginActivity.this, SkateParkListActivity.class);
        startActivity(intent);
        finish();
    }

    // Realiza evento onClick do botão clicado
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_entrar) {
            // Ao clicar no botão Entrar, realiza o signIn
            signIn(etEmail.getText().toString(), etSenha.getText().toString());
        }
    }
}
