package com.example.inventory2.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.inventory2.model.User;
import com.example.inventory2.ui.base.Event;
import com.example.inventory2.ui.login.LoginContract;
import com.example.inventory2.ui.signup.SignUpContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserRepositoryImpl implements LoginContract.Repository, SignUpContract.Repository {

    private static final String TAG = UserRepositoryImpl.class.getName();
    private static UserRepositoryImpl instance;
    private LoginContract.OnLoginListener listener;

    public static UserRepositoryImpl getInstance(LoginContract.OnLoginListener listener) {
        if (instance == null) {
            instance = new UserRepositoryImpl();
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
                            listener.onSuccess("Usuario correcto");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());

                            Event loginEvent = new Event();
                            loginEvent.setEventType(Event.onLoginError);
                            loginEvent.setMessage(task.getException().toString());
                        }
                    }
                });
    }

    @Override
    public void validateSignUp(String user, String email, String password, String confirmPassword) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            listener.onSuccess("Usuario correcto");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());

                            Event signUpEvent = new Event();
                            signUpEvent.setEventType(Event.onSignUpError);
                            signUpEvent.setMessage(task.getException().toString());
                        }
                    }
                });
    }
}
