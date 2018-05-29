package com.example.anoren.fastmarket;

import android.*;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.anoren.fastmarket.pctproduto.Produto;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class CadastroProduto extends AppCompatActivity {
    //Permissões que foram atribuidas em AndroidManifest.xml----------------------------------------
    String[] permissoes = new String[]{
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private Boolean permissiook = false;
    //----------------------------------------------------------------------------------------------
    private EditText codigo, descricao, preco, unidade;
    private ImageView imagemProduto;

    //OBJETOS DE CONEXÃO COM O FIREBASE-------------------------------------------------------------
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        this.setTitle("Produtos");

        Permissao.validate(CadastroProduto.this, 0, permissoes);

        ativarComponentes(); conectaFirebase(); capturaFoto();
    }//---------------------------------------------------------------------------------------------
    private void ativarComponentes(){
        codigo = (EditText)findViewById(R.id.edtxtInseriCodigoCadProID);
        descricao = (EditText)findViewById(R.id.edtxtInseriDescricaoCadProID);
        preco = (EditText)findViewById(R.id.edtxtInseriPrecoCadProID);
        unidade = (EditText)findViewById(R.id.edtxtInseriUnidadeCadProID);
        imagemProduto = (ImageView)findViewById(R.id.imvTiraFotoCadProID);
    }//---------------------------------------------------------------------------------------------
    private void conectaFirebase(){

        FirebaseApp.initializeApp(CadastroProduto.this);

        firebaseDatabase = FirebaseDatabase.getInstance();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true); //GRAVA DADOS (OFFLINE)

        databaseReference = firebaseDatabase.getReference();
    }//---------------------------------------------------------------------------------------------
    private void camposLimpos(){
        codigo.setText("");
        descricao.setText("");
        preco.setText("");
        unidade.setText("");
    }
    //TIRAR FOTO------------------------------------------------------------------------------------
    private void capturaFoto() {
        imagemProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foto = new  Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(foto, 0);
            }
        });
    }

    @Override //Após tirar a foto, é entregue nesse método
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle bundle = data.getExtras();
        if(data !=null){
            //Recupera o Bitmap pela câmera
            Bitmap bitmap = (Bitmap)bundle.get("data");
            //Atualiza a imagem na tela
            imagemProduto.setImageBitmap(bitmap);
        }
    }
    //ACTIONBAR - Usando ActionBar na tela cadastro de produto--------------------------------------
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbarcadproduto, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menuConsultarProID:
                //COLOCAR AÇÃO
                return (true);

            case R.id.menuSalvarProID:

                    Produto produtos = new Produto();

                    produtos.setId(codigo.getText().toString());
                    produtos.setDescricao(descricao.getText().toString());
                    produtos.setPreco(preco.getText().toString());
                    produtos.setUnidade(unidade.getText().toString());

                    //FAZ O INSERT NO BANCO DE DADOS
                    databaseReference.child("Produto").child(produtos.getId()).setValue(produtos);

                    camposLimpos();

                    exibiMensagem("Produto cadastrado!!!");

                return (true);

            case R.id.menuExcluirProID:
                //COLOCAR AÇÃO
                return (true);
        }
            return (super.onOptionsItemSelected(item));
    }

    //MÉTODO PARA EXIBIR AS MENSAGENS---------------------------------------------------------------
    private void exibiMensagem(String mensagem){
        Toast.makeText(CadastroProduto.this, mensagem, Toast.LENGTH_LONG).show();
    }//---------------------------------------------------------------------------------------------
}
