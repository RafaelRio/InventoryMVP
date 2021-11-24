package com.example.inventory2.ui.signup;

import com.example.inventory2.model.User;
import com.example.inventory2.ui.login.LoginContract;

public interface SignUpContract {
    interface View extends onSignUpListener{
        void setUserEmptyError();
        void setEmailEmptyError();
        void setPasswordEmptyError();
        void setConfirmPasswordEmptyError();

        void setPasswordError();
        void setEmailError();

        void setPasswordDontMatch();

    }

    interface Presenter extends onSignUpListener{
        void validateSignUp(String user, String email, String password, String confirmPassword);
    }

    interface onSignUpListener{
        void onSuccess(String message);
        void onFailure(String message);

    }

    interface onInteractorListener extends onSignUpListener{
        void onUserEmptyError();
        void onEmailEmptyError();
        void onPasswordEmptyError();
        void onConfirmPasswordEmptyError();

        void onPasswordError();
        void onEmailError();

        void onPasswordDontMatch();
    }

    interface Repository {
        void validateSignUp(String user, String email, String password, String confirmPassword);
    }
}
