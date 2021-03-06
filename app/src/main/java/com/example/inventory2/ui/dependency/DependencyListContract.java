package com.example.inventory2.ui.dependency;

import com.example.inventory2.model.Dependency;
import com.example.inventory2.ui.base.BasePresenter;
import com.example.inventory2.ui.base.OnRepositoryCallback;
import com.example.inventory2.ui.base.OnRepositoryListCallback;
import com.example.inventory2.ui.base.iProgressView;

import java.util.ArrayList;
import java.util.List;

public interface DependencyListContract {

    /**
     * Esta interfaz tiene los siguientes metodos
     *  -La respuesta del repositorio
     *  -Los metodos necesarios para mortar un progreso
     *  -Los metodos necesarios para gestionar los datos de un RecyclerView
     */
    interface View extends OnRepositoryListCallback, iProgressView {
        void showData(ArrayList<Dependency> list);
        void showNoData();

        //Ordena de la A-Z
        void showDataOrder();

        //Ordena de la Z-A
        void showDataInverseOrder();
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

        //4. La lista se ordena por nombre
        void order();
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
