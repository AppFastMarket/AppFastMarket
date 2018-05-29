package com.example.anoren.fastmarket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anoren.fastmarket.pctempresa.Empresa;
import com.example.anoren.fastmarket.pctproduto.Produto;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroEmpresa extends AppCompatActivity {

    private EditText codigo, nome, cidade, estado, telefone;

    //OBJETOS DE CONEXÃO COM O FIREBASE-------------------------------------------------------------
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_empresa);
        this.setTitle("Cadastro Empresa");

        ativarComponentes(); conectaFirebase();
    }//---------------------------------------------------------------------------------------------
    private void ativarComponentes(){
        codigo = (EditText)findViewById(R.id.edtxtInfoCodigoCadEmpresaID);
        nome = (EditText)findViewById(R.id.edtxtInfoNomeCadEmpresaID);
        cidade = (EditText)findViewById(R.id.edtxtInfoCidadeCadEmpresaID);
        estado = (EditText)findViewById(R.id.edtxtInfoEstadoCadEmpresaID);
        telefone = (EditText)findViewById(R.id.edtxtInfoTelefoneCadEmpresaID);
    }//---------------------------------------------------------------------------------------------

    private void conectaFirebase(){
        FirebaseApp.initializeApp(CadastroEmpresa.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    //ACTIONBAR - Usando ActionBar na tela cadastro de empresa--------------------------------------
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbarcadempresa, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menuConsultarEmpresaID:
                //COLOCAR AÇÃO
                return (true);

            case R.id.menuSalvarEmpresaID:

                Empresa empresas = new Empresa();

                empresas.setId(codigo.getText().toString());
                empresas.setNome(nome.getText().toString());
                empresas.setCidade(cidade.getText().toString());
                empresas.setEstado(estado.getText().toString());
                empresas.setTelefone(telefone.getText().toString());

                //FAZ O INSERT NO BANCO DE DADOS
                databaseReference.child("Empresa").child(empresas.getId()).setValue(empresas);

                camposLimpos();

                exibiMensagem("Empresa cadastrado!!!");

                return (true);

            case R.id.menuExcluirEmpresaID:
                //COLOCAR AÇÃO
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }
    //MÉTODO PARA EXIBIR AS MENSAGENS---------------------------------------------------------------
    private void exibiMensagem(String mensagem){
        Toast.makeText(CadastroEmpresa.this, mensagem, Toast.LENGTH_LONG).show();
    }//---------------------------------------------------------------------------------------------
    private void camposLimpos(){
        codigo.setText("");
        nome.setText("");
        cidade.setText("");
        estado.setText("");
        telefone.setText("");
    }//---------------------------------------------------------------------------------------------
}
