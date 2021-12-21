package com.example.inventory2.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inventory2.MainActivity;
import com.example.inventory2.R;
import com.example.inventory2.model.User;
import com.example.inventory2.ui.login.LoginActivity;

public class splashActivity extends AppCompatActivity {

    //Declarar una constante privada
    private static final long WAIT_TIME = 2000;


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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (saveSession()) {
                    startApp();
                } else {
                    startLogin();
                }
            }
        }, WAIT_TIME);
    }

    /**
     * Metodo que comprueba si el usuario ha iniciado sesion y se ha guardado
     * su email en el fichero de preferencias DefaultSharePreferences
     * @return
     */
    private boolean saveSession() {
        return (PreferenceManager.getDefaultSharedPreferences(this).contains(User.TAG));
    }

    private void startLogin() {
        startActivity(new Intent(splashActivity.this, LoginActivity.class));
        //Voy a llamar de forma explicita al metodo finish de una activity para
        //eliminar esta activity de la pila de actividades porque si el usuario
        //pulsa back, no queremos que se visualice
        finish();
    }

    private void startApp() {
        startActivity(new Intent(splashActivity.this, MainActivity.class));
        //Voy a llamar de forma explicita al metodo finish de una activity para
        //eliminar esta activity de la pila de actividades porque si el usuario
        //pulsa back, no queremos que se visualice
        finish();
    }
}