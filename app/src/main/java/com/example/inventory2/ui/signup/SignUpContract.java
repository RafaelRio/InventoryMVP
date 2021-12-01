package com.example.inventory2.ui.signup;

import com.example.inventory2.model.User;
import com.example.inventory2.ui.base.BasePresenter;
import com.example.inventory2.ui.base.OnRepositoryCallback;
import com.example.inventory2.ui.base.iProgressView;
import com.example.inventory2.ui.login.LoginContract;

public interface SignUpContract {
    interface View extends LoginContract.View, iProgressView {
        void setUserEmptyError();
        void setConfirmPasswordEmptyError();
        void setEmailError();
        void setPasswordDontMatch();

    }

    interface Presenter extends BasePresenter {
        void validateSignUp(String user, String email, String password, String confirmPassword);
    }

    interface onSignUpInteractorListener extends LoginContract.OnInteractorListener, OnRepositoryCallback {
        void onUserEmptyError();
        void onConfirmPasswordEmptyError();

        void onEmailError();

        void onPasswordDontMatch();
    }

    interface Repository {
        void SignUp(String user, String email, String password, String confirmPassword);
    }
}
