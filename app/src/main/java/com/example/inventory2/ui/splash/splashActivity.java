package com.example.inventory2.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.inventory2.R;
import com.example.inventory2.ui.login.LoginActivity;

public class splashActivity extends AppCompatActivity {

    //Declarar una constante privada
    private static final long WAIT_TIME=2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }


    /**
     * Vamos a simular un tiempo de espera con un hilo que duerme 2 segundos y
     * cuando despierta se ejecutara un metodo startLogin() que inicia la activity login
     */
    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(() -> startLogin(), WAIT_TIME);
    }

    private void startLogin() {
        startActivity(new Intent( splashActivity.this, LoginActivity.class));
        //Voy a llamar de forma explicita al metodo finish de una activity para
        //eliminar esta activity de la pila de actividades porque si el usuario
        //pulsa back, no queremos que se visualice
        finish();
    }
}