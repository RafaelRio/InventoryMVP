package com.example.inventory2.ui.dependency;

import com.example.inventory2.model.Dependency;
import com.example.inventory2.ui.base.BasePresenter;
import com.example.inventory2.ui.base.OnRepositoryCallback;
import com.example.inventory2.ui.base.OnRepositoryListCallback;

public interface DependencyListContract {

    interface View {

    }

    /**
     * Interfaz que debe implementar el presenter
     */
    interface Presenter extends BasePresenter {
        //1. Cargar los datos
        void load();

        //2. Cuando se realiza una pulsacion larga se elimina
        void delete(Dependency dependency);

        //3. Cuando el usuario pulsa la opcion undo del SnackBar
        void undo(Dependency dependency);
    }

    /**
     * Interfaz que debe implementar toda clase que quiera ser un repositorio lista
     */
    interface Repository {
        //1. Cargar los datos
        void getList();

        //2. Cuando se realiza una pulsacion larga se elimina
        void delete(Dependency dependency);

        //3. Cuando el usuario pulsa la opcion undo del SnackBar
        void undo(Dependency dependency);
    }

    /**
     * Interfaz que debe implementar el listener del interactor
     * Esta interfaz muestra las posibles alternativas de los casos de uso
     * - Listar elementos (getList)
     * - Eliminar (delete)
     * - Deshacer (undo)
     */
    interface OnInteractorListener extends OnRepositoryListCallback {
    }
}
