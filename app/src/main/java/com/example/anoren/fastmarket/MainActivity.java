package com.example.anoren.fastmarket;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //CHAMANDO O MÉTODO INICIARSPLASH
        iniciarSplash();
    }
    //MÉTODO PARA INICIAR O SPLASH
    public void iniciarSplash(){
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {

                contador++;

                try{
                    while(contador == 0 || contador <3){
                        Thread.sleep(1000);
                        contador++;

                        //Para ver o valor que está sendo incrementado no contador
                        Log.e("Contador = ", Integer.toString(contador));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //Para abrir o novo layout
                if(contador == 3){
                    Intent intente = new Intent(MainActivity.this, TelaLogin.class);
                    startActivity(intente);
                    contador++; //contador para mais um incremento, assim não entra em loop infinito!
                    Log.e("Contador = ", Integer.toString(contador));
                }
            }
        }).start(); //Método start para da inicio
    }
}
