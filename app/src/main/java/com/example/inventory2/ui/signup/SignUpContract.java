package com.example.inventory2.ui.signup;

import com.example.inventory2.model.User;
import com.example.inventory2.ui.base.iProgressView;
import com.example.inventory2.ui.login.LoginContract;

public interface SignUpContract {
    interface View extends LoginContract.View, iProgressView {
        void setUserEmptyError();
        void setConfirmPasswordEmptyError();
        void setConfirmPasswordError();
        void setPasswordDontMatch();

    }

    interface Presenter extends onSignUpListener{
        void validateSignUp(String user, String email, String password, String confirmPassword);
    }

    interface onSignUpListener{
        void onSuccess(String message);
        void onFailure(String message);

    }

    interface onInteractorListener extends LoginContract.OnInteractorListener{
        void onUserEmptyError();
        void onConfirmPasswordEmptyError();

        void onEmailError();

        void onPasswordDontMatch();
    }

    interface Repository {
        void validateSignUp(String user, String email, String password, String confirmPassword);
    }
}
