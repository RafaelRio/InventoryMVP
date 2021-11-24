package com.example.inventory2.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.inventory2.model.User;
import com.example.inventory2.ui.login.LoginContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginRepositoryImpl implements LoginContract.Repository {

    private static final String TAG = LoginRepositoryImpl.class.getName();
    private static LoginRepositoryImpl instance;
    private LoginContract.OnLoginListener listener;

    public static LoginRepositoryImpl getInstance(LoginContract.OnLoginListener listener) {
        if (instance == null) {
            instance = new LoginRepositoryImpl();
        }
        instance.listener = listener;
        return instance;
    }

    public void login(User user) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            listener.onSucces("Usuario correcto");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            listener.onFailure("Error de autenticacion: " + task.getException());
                        }
                    }
                });
    }
}
