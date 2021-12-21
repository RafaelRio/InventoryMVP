package com.example.inventory2.ui.signup;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;

import com.example.inventory2.data.repository.LoginRepositoryImpl;
import com.example.inventory2.data.repository.LoginRepositoryStatic;
import com.example.inventory2.ui.base.OnRepositoryCallback;
import com.example.inventory2.utils.CommonUtils;

public class SignUpInteractor implements OnRepositoryCallback {

    private SignUpContract.Repository repository;
    private SignUpContract.onSignUpInteractorListener listener;

    public SignUpInteractor(SignUpContract.onSignUpInteractorListener listener) {
        this.listener = listener;
        repository = LoginRepositoryImpl.getInstance(this);

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

                //repository.SignUp(user, email, password, confirmPassword);
                LoginRepositoryImpl.getInstance(SignUpInteractor.this).SignUp(user, email, password, confirmPassword);
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

    @Override
    public void onAddSuccess(String message) {

    }

    @Override
    public void onEditSuccess() {

    }
}
