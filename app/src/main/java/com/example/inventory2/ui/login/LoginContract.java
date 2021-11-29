package com.example.inventory2.ui.login;

import com.example.inventory2.model.User;
import com.example.inventory2.ui.base.BasePresenter;
import com.example.inventory2.ui.base.OnRepositoryCallback;
import com.example.inventory2.ui.base.iProgressView;

/**
 * Esta interfaz es el contrato entre la vista y el presentador del Login
 */
public interface LoginContract {

    /**
     * Interfaz que debe implementar la vista
     */
    interface View extends OnRepositoryCallback, iProgressView {
        //Alternativas del caso de uso
        //Elementos de la vista
        void setEmailError();
        void setEmailEmptyError();
        void setPasswordEmptyError();
        void setPasswordError();

    }

    /**
     * Interfaz que debe inicializar el presenter
     */
    interface Presenter extends BasePresenter {
        void validateCredentials(User user);
    }

    /**
     * Interfaz que debe implementar toda clase que quiera ser un repositorio
     * para login
     */
    interface Repository{
        void login(User user);
    }

    /**
     * Interfaz que debe implementar el listener que se le pasa del Interactor
     * Esta interfaz son las posibles alternativas del caso de uso del LOGIN
     */
    interface OnInteractorListener extends OnLoginListener{
        void onEmailEmptyError();
        void onPasswordEmptyError();
        void onPasswordError();
    }

    /**
     * Interfaz que debe implementar toda clase que este relacionada con la
     * respuesta del repositorio (accion login)
     * Esta interfaz es la secuancia normal de LOGIN
     */
    interface OnLoginListener{
        void onSuccess(String message);
        void onFailure(String message);
    }

}
