package com.example.inventory2.ui.dependency;

import com.example.inventory2.data.repository.DependencyRepository;
import com.example.inventory2.model.Dependency;
import com.example.inventory2.ui.base.OnRepositoryListCallback;

import java.util.ArrayList;
import java.util.List;

public class DependencyListInteractor implements OnRepositoryListCallback {

    private DependencyListContract.OnInteractorListener listener;

    public DependencyListInteractor(DependencyListContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    @Override
    public void onFailure(String mensaje) {

    }

    @Override
    public <T> void onSuccess(List<T> list) {
        listener.onSuccess((ArrayList<Dependency>) list);
    }

    @Override
    public void onDeleteSuccess(String mensaje) {
        listener.onDeleteSuccess(mensaje);
    }

    @Override
    public void onUndoSuccess(String mensaje) {

    }

    /**
     * Metodo que pide los datos al repositorio
     */
    public void load() {
        //SIEMPRE SE ACCEDE DE FORMA ESTATICA AL REPOSITORIO
        DependencyRepository.getInstance(this).getList();
    }

    /**
     * Elimina la dependencia del repositorio
     *
     * @param dependency
     */
    public void delete(Dependency dependency) {
        DependencyRepository.getInstance(this).delete(dependency);
    }
}
