package com.example.inventory2.ui.signup;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;

import com.example.inventory2.data.repository.LoginRepositoryStatic;
import com.example.inventory2.utils.CommonUtils;

public class SignUpInteractor implements SignUpContract.onSignUpListener {

    private SignUpContract.Repository repository;
    private SignUpContract.onInteractorListener listener;

    public SignUpInteractor(SignUpContract.onInteractorListener listener) {
        this.listener = listener;
        repository = LoginRepositoryStatic.getInstance(this);

    }

    public void validateSignUp(String user, String email, String password, String confirmPassword) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Gestionar las alternativas de los casos de uso

                if (TextUtils.isEmpty(user)) {
                    listener.onUserEmptyError();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    listener.onEmailEmptyError();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    listener.onPasswordEmptyError();
                    return;
                }

                if (TextUtils.isEmpty(confirmPassword)) {
                    listener.onConfirmPasswordEmptyError();
                    return;
                }

                if (!CommonUtils.isPasswordValid(password)) {
                    listener.onPasswordError();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    listener.onEmailError();
                    return;
                }

                if (!password.equals(confirmPassword)){
                    listener.onPasswordDontMatch();
                    return;
                }

                repository.SignUp(user, email, password, confirmPassword);
            }
        }, 2000);
    }

    @Override
    public void onSuccess(String message) {
        listener.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        listener.onFailure(message);
    }
}
