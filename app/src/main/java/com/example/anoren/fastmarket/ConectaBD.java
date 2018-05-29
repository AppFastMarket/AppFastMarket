package com.example.anoren.fastmarket;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ConectaBD {
    private static FirebaseUser firebaseUser;
    private static FirebaseAuth firebaseAuth;
    private static FirebaseAuth.AuthStateListener authStateListener;

    //CONSTRUTOR
    private void ConectaBD(){

    }//---------------------------------------------------------------------------------------------
    //MÉTODO QUE RETORNA O OBJETO FIREBASE
    public static FirebaseAuth getFirebaseAuth(){
        if(firebaseAuth == null){
            iniciaFirebaseAuth();
        }
        return firebaseAuth;
    }//---------------------------------------------------------------------------------------------
    //MÉTODO PARA INICIAR O FIREBASE
    private static void  iniciaFirebaseAuth(){
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser usuario = firebaseAuth.getCurrentUser();
                if(usuario != null){
                    firebaseUser = usuario;
                }
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);
    }//---------------------------------------------------------------------------------------------
    public static FirebaseUser getFirebaseUser(){
        return firebaseUser;
    }//---------------------------------------------------------------------------------------------
    public static void saidaUsuario(){
        firebaseAuth.signOut();
    }//---------------------------------------------------------------------------------------------
}
