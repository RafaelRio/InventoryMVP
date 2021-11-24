package com.example.inventory2.ui.login;

import com.example.inventory2.model.User;

public class LoginPresenter implements LoginContract.Presenter, LoginContract.OnInteractorListener {

    private LoginContract.View view;
    private LoginInteractor interactor;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        interactor = new LoginInteractor(this);
    }

    //region Metodos del contrato Presenter/Interactor


    @Override
    public void validateCredentials(User user) {
        interactor.validateCredentials(user);
        view.showProgressBar();
    }

    @Override
    public void onEmailEmptyError() {
        view.hideProgressBar();
        view.setEmailEmptyError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.hideProgressBar();
        view.setPasswordEmptyError();
    }

    @Override
    public void onPasswordError() {
        view.hideProgressBar();
        view.setPasswordError();
    }


    @Override
    public void onSucces(String message) {
        view.hideProgressBar();
        view.onSucces(message);
    }


    @Override
    public void onFailure(String message) {
        view.hideProgressBar();
        view.onFailure(message);
    }

    //endregion
}
