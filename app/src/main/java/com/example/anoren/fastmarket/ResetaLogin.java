package com.example.anoren.fastmarket;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetaLogin extends AppCompatActivity {

    //OBJETO FIREBASE
    private static FirebaseAuth auth;
    //----------------------------------------------------------------------------------------------
    private Button btResetar;
    private EditText informaEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reseta_login);
        this.setTitle("Resetar Senha");

        ativarComponentes(); acaoBotao();

    }//---------------------------------------------------------------------------------------------
    private void ativarComponentes() {
        btResetar = (Button) findViewById(R.id.btnResetarResetaLoginID);
        informaEmail = (EditText) findViewById(R.id.edtxtInfoEmailResetaSenhaID);
    }//---------------------------------------------------------------------------------------------
    private void acaoBotao() {
        btResetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = informaEmail.getText().toString().trim();

                resetarSenha(email);
            }
        });
    }//---------------------------------------------------------------------------------------------
    private void resetarSenha(String email){
        auth.sendPasswordResetEmail(email).addOnCompleteListener
                (ResetaLogin.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    exibiMensagem("E-mail enviado para resetar sua senha!!!");
                    finish();
                }else{
                    exibiMensagem("E-mail Inválido!!!");
                }
            }
        });
    }//---------------------------------------------------------------------------------------------
    //MÉTODO PARA EXIBIR AS MENSAGENS
    private void exibiMensagem(String mensagem) {
        Toast.makeText(ResetaLogin.this, mensagem, Toast.LENGTH_LONG).show();
    }//---------------------------------------------------------------------------------------------
    //MÉTODO ON START
    @Override
    protected void onStart() {
        super.onStart();

        auth = ConectaBD.getFirebaseAuth(); //Adiciona no objeto Auth o que receber de conexão
    }//---------------------------------------------------------------------------------------------
}
