package com.example.anoren.fastmarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Info extends AppCompatActivity {

    private ImageView imBotaoEmpresa, imBotaoProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ativarComponentes(); acaoBotoes();
    }
    private void ativarComponentes(){
        imBotaoEmpresa = (ImageView)findViewById(R.id.imvBotaoEmpresaInfoID);
        imBotaoProduto = (ImageView)findViewById(R.id.imvBotaoProdutoInfoID);
    }//---------------------------------------------------------------------------------------------
    private void acaoBotoes(){
        /*********************BOTÃO EMPRESA**********************/
        imBotaoEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenteLogin = new Intent(getApplicationContext(), CadastroEmpresa.class);
                startActivity(intenteLogin);
            }
        });
        /**********************BOTÃO PRODUTO*********************/
        imBotaoProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenteLogin = new Intent(getApplicationContext(), CadastroProduto.class);
                startActivity(intenteLogin);
            }
        });
    }//---------------------------------------------------------------------------------------------
}
