package com.example.inventory2.ui.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.inventory2.R;
import com.example.inventory2.databinding.ActivityLoginBinding;
import com.example.inventory2.databinding.ActivitySignUpBinding;
import com.example.inventory2.ui.base.Event;
import com.example.inventory2.ui.login.LoginActivity;
import com.example.inventory2.ui.login.LoginContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View {
    ActivitySignUpBinding binding;
    private SignUpContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /**
         * Se utiliza el metodo OnBackPressed para eliminar la Activity SignUpActivity
         * y restaurar la actividad anterior LoginActivity
         */
        binding.btnSignUp.setOnClickListener((view -> presenter.validateSignUp(
                binding.tieUser.getText().toString(),
                binding.tieEmail.getText().toString(),
                binding.tiePassword.getText().toString(),
                binding.tieConfirmPassword.getText().toString()
        )));
        presenter = new SignUpPresenter(this);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //CON ESTO SE EVITARIA UN FUTURO MEMORY LEAKS (PERDIDA DE MEMORIA)
        presenter.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void setUserEmptyError() {
        binding.tilUser.setError(getString(R.string.err_emptyUser));
    }

    @Override
    public void setEmailEmptyError() {
        binding.tilEmail.setError(getString(R.string.err_emptyEmail));
    }

    @Override
    public void setPasswordEmptyError() {
        binding.tilEmail.setError(getString(R.string.err_emptyPassword));
    }

    @Override
    public void setConfirmPasswordEmptyError() {
        binding.tilEmail.setError(getString(R.string.err_emptyPassword));
    }

    @Override
    public void setPasswordError() {
        binding.tilPassword.setError(getString(R.string.err_Password));
    }

    @Override
    public void setEmailError() {
        binding.tilPassword.setError(getString(R.string.err_email));
    }

    @Override
    public void setPasswordDontMatch() {
        Toast.makeText(this, R.string.err_passwordDontMatch, Toast.LENGTH_SHORT).show();
    }

    /**
     * Esto ocurre cuando el usuario no existe en la base de datos y puede registrarse en la app
     * @param message
     */
    @Override
    public void onSuccess(String message) {
        onBackPressed();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddSuccess(String message) {

    }

    @Override
    public void onEditSuccess() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showProgressBar() {

    }

    @Subscribe
    public void onEvent(Event event){
        hideProgressBar();
        Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
    }
}