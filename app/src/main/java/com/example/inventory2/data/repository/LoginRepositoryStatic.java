package com.example.inventory2.data.repository;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.inventory2.model.User;
import com.example.inventory2.ui.base.OnRepositoryCallback;
import com.example.inventory2.ui.login.LoginContract;
import com.example.inventory2.ui.signup.SignUpContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Vamos a simular que la instancia de LoginRepository es unica. Si es así,
 * PATRON SINGLETON
 * --El primer requisito es que el constructor es privado
 * --El segundo requisito es que todas las clases obtienen la instacia a traves de un metodo que
 * se llama getInstance()
 */
public class LoginRepositoryStatic implements LoginContract.Repository, SignUpContract.Repository{

    private static LoginRepositoryStatic instance;
    private ArrayList<User> users;      //ArrayList de usuarios autorizados en mi App
    OnRepositoryCallback callback;

    private LoginRepositoryStatic() {
        users = new ArrayList<>();
        initialice();
    }



    /**
     * Es un metodo privado que inicializa la estructura de datos de una clase (ArrayList)
     */
    private void initialice() {
        users.add(new User("rafa2000inator@gmail.com", "RafaRio&123"));
    }


    public static LoginRepositoryStatic getInstance(OnRepositoryCallback listener){
        if (instance == null){
            instance = new LoginRepositoryStatic();
        }
        instance.callback = listener;
        return instance;
    }

    /**
     * Este es el metodo que comprueba si el usuario existe. Para comprobarlo hay que
     * recorrer el ArrayList
     *
     * @param u
     */
    @Override
    public void login(User u) {
        for (User user : users) {
            if (user.getEmail().equals(u.getEmail()) && user.getPassword().equals(u.getPassword())) {
                callback.onSuccess("Usuario correcto");
                return;
            }
        }
        //En caso contrario, no existe
        callback.onFailure("Error en la autenticacion");
    }


    @Override
    public void SignUp(String user, String email, String password, String confirmPassword) {
        for (User user1 : users) {
            if (user1.getEmail().equals(email) && user1.getPassword().equals(password)) {
                callback.onFailure("El usuario ya existe");
                return;
            }
        }
        //En caso contrario, no existe
        users.add(new User(email, password));
        callback.onSuccess("Usuario creado con éxito");
    }
}
