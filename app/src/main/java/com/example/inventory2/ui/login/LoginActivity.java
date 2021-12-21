package com.example.inventory2.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inventory2.MainActivity;
import com.example.inventory2.R;
import com.example.inventory2.databinding.ActivityLoginBinding;
import com.example.inventory2.model.User;
import com.example.inventory2.ui.base.Event;
import com.example.inventory2.ui.signup.SignUpActivity;
import com.example.inventory2.utils.CommonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private ActivityLoginBinding binding;
    private LoginContract.Presenter presenter;
    private LoginTextWatcher loginTextWatcher;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //CON ESTO SE EVITARIA UN FUTURO MEMORY LEAKS (PERDIDA DE MEMORIA)
        presenter.onDestroy();

    }

    @Override
    protected void onStop() {
        super.onStop();
        //Se quita como suscriptor del EventBus
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //La vista se registra como suscriptor del EventBus
        EventBus.getDefault().register(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSignUp.setOnClickListener(view -> toSignUpActivity());
        binding.btnSignIn.setOnClickListener(view -> presenter.validateCredentials(new User(binding.tieEmail.getText().toString(), binding.tiePassword.getText().toString())));

        //Se inicializa el listener que escucha los eventos de los campos Editable
        binding.tieEmail.addTextChangedListener(new LoginTextWatcher(binding.tieEmail));
        binding.tiePassword.addTextChangedListener(new LoginTextWatcher(binding.tiePassword));

        presenter = new LoginPresenter(this);


    }

    public void toSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void toMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //region Metodos del contrato View Presenter

    @Override
    public void setEmailError() {

    }

    /**
     * Este metodo activa el error en el TextInputLayout. Mostrar el texto oportuno
     */
    @Override
    public void setEmailEmptyError() {
        binding.tilEmail.setError(getString(R.string.err_emptyEmail));
    }

    @Override
    public void setPasswordEmptyError() {
        binding.tilPassword.setError(getString(R.string.err_emptyPassword));
    }

    @Override
    public void setPasswordError() {
        binding.tilPassword.setError(getString(R.string.err_Password));
    }

    /**
     * Secuencia normal. Usuario y contraseña correctos y el usuario existe en la base de datos
     */
    @Override
    public void onSuccess(String message) {
        SharedPreferences.Editor editor =
                PreferenceManager.getDefaultSharedPreferences(this).edit();
        if (binding.ckRemember.isChecked()) {
            editor.putString(User.TAG, binding.tieEmail.getText().toString());
            editor.apply();
        }
        toMainActivity();
    }

    @Override
    public void showProgressBar() {
        binding.progressHorizontal.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.progressHorizontal.setVisibility(View.INVISIBLE);
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

    //endregion

    //region Clase interna que controla cada vez que el usuario introduce un caracter
    //en un editable si cumple o no las reglas de validacion

    /**
     * Metodo que valida la contraseña mediante el metodo creado de la clase CommonUtils
     *
     * @param password
     */
    private void validatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            binding.tilPassword.setError(getString(R.string.err_emptyPassword));
        } else if (!CommonUtils.isPasswordValid(password)) {
            binding.tilPassword.setError(getString(R.string.err_Password));
        } else {
            binding.tilPassword.setError(null);
        }
    }

    /**
     * Metodo que valida el email
     * 1. No puede ser vacio
     * 2. Vamos a utilizar el patron por defecto de Email para comprobar que es correcto
     *
     * @param email email
     */
    private void validateEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            binding.tilEmail.setError(getString(R.string.err_emptyEmail));
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.setError(getString(R.string.err_email));
        } else {
            //desaparece el error
            binding.tilEmail.setError(null);
        }
    }

    @Subscribe
    public void onEvent(Event event) {
        hideProgressBar();
        Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
    }

    //endregion

    class LoginTextWatcher implements TextWatcher {

        private final View view;

        LoginTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.tieEmail:
                    validateEmail(((EditText) view).getText().toString());
                    break;
                case R.id.tiePassword:
                    validatePassword(((EditText) view).getText().toString());
                    break;
            }
        }
    }
}