package com.example;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;

import com.example.inventory2.R;

import java.util.Collections;

public class InventoryApplication extends Application {
    public static final String IDCHANNEL = "5498465431";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    /**
     * Este metodo crea el canal que se utilizara en las notificaciones
     * de la aplicacion
     */
    private void createNotificationChannel() {
        //Se crea una clase NotificationChannel solo en el caso de que la API sea > 26
        //porque no se ha incluido en la libreria de soporte
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //1. Definir la importancia
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            //2. Definir el nombre del canal
            String nameChannel = getString(R.string.name_channel);

            //3. Se crea el canal
            NotificationChannel notificationChannel =
                    new NotificationChannel(IDCHANNEL, nameChannel, importance);

            //3.1. OPCIONAL: Configurar modo de vibracion, luces...
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);

            //4. Añadir este canal al NotificacionManager
            getSystemService(NotificationManager.class).createNotificationChannel(notificationChannel);
        }

    }
}
