package com.example.inventory2.ui.signup;

public class SignUpPresenter implements SignUpContract.Presenter, SignUpContract.onSignUpInteractorListener {

    private SignUpContract.View view;
    private SignUpInteractor interactor;

    public SignUpPresenter(SignUpContract.View view) {
        this.view = view;
        interactor = new SignUpInteractor(this);
    }

    @Override
    public void validateSignUp(String user, String email, String password, String confirmPassword) {
        interactor.validateSignUp(user, email, password, confirmPassword);
    }

    @Override
    public void onSuccess(String message) {
        view.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        view.onFailure(message);
    }

    @Override
    public void onAddSuccess(String message) {

    }

    @Override
    public void onEditSuccess() {

    }

    @Override
    public void onUserEmptyError() {
        view.setUserEmptyError();
    }

    @Override
    public void onEmailEmptyError() {
        view.setEmailEmptyError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.setUserEmptyError();
    }

    @Override
    public void onConfirmPasswordEmptyError() {
        view.setConfirmPasswordEmptyError();
    }

    @Override
    public void onPasswordError() {
        view.setPasswordError();
    }

    @Override
    public void onEmailError() {
        view.setEmailError();
    }

    @Override
    public void onPasswordDontMatch() {
        view.setPasswordDontMatch();
    }

    @Override
    public void onDestroy() {

    }
}
