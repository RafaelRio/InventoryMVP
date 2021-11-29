package com.example.inventory2.ui.login;

import android.os.Handler;
import android.text.TextUtils;

import com.example.inventory2.data.repository.LoginRepositoryStatic;
import com.example.inventory2.model.User;
import com.example.inventory2.utils.CommonUtils;

public class LoginInteractor implements LoginContract.OnLoginListener {

    /**
     * El presentador que quiera utilizar esta clase debe
     * implementar esta interfaz
     */

    private LoginContract.Repository repository;
    private LoginContract.OnInteractorListener listener;

    public LoginInteractor(LoginContract.OnInteractorListener listener) {
        this.listener = listener;
        this.repository = LoginRepositoryStatic.getInstance(this);
    }


    public void validateCredentials(User user) {
        //En base a lo que suceda avisare a su Listener/Presentador
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Gestionar las alternativas de los casos de uso
                if (TextUtils.isEmpty(user.getEmail())) {
                    listener.onEmailEmptyError();
                    return;
                }
                if (TextUtils.isEmpty(user.getPassword())) {
                    listener.onPasswordEmptyError();
                    return;
                }

                if (!CommonUtils.isPasswordValid(user.getPassword())) {
                    listener.onPasswordError();
                    return;
                }

                repository.login(user);
            }
        }, 2000);
    }

    //Estos metodos vienen de la respuesta que nos da el repositorio


    @Override
    public void onSuccess(String message) {
        listener.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        listener.onFailure(message);
    }
}
