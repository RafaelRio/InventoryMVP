package com.example.inventory2.ui.dependency;

import com.example.inventory2.model.Dependency;

import java.util.ArrayList;
import java.util.List;

public class DependencyListPresenter implements DependencyListContract.Presenter, DependencyListContract.OnInteractorListener {

    private DependencyListContract.View view;
    private DependencyListInteractor interactor;
    private Boolean order = false;

    public DependencyListPresenter(DependencyListContract.View view) {
        this.view = view;
        this.interactor = new DependencyListInteractor(this);
    }

    @Override
    public void load() {
        view.showProgressBar();
        interactor.load();
    }

    /**
     * Este metodo elimina una dependencia de toda la aplicacion (BD, repositorio...)
     *
     * @param dependency
     */
    @Override
    public void delete(Dependency dependency) {
        interactor.delete(dependency);
    }

    @Override
    public void undo(Dependency dependency) {
        interactor.undo(dependency);
    }

    @Override
    public void order() {
        if (order == true) {
            order = false;
            view.showDataInverseOrder();
        } else {
            order = true;
            view.showDataOrder();
        }
    }

    @Override
    public void onFailure(String mensaje) {

    }

    @Override
    public <T> void onSuccess(List<T> list) {
        if (list.size() == 0) {
            view.showNoData();
        } else {
            view.showData((ArrayList<Dependency>) list);
        }
        view.hideProgressBar();
    }

    @Override
    public void onDeleteSuccess(String mensaje) {
        view.onDeleteSuccess(mensaje);
    }

    @Override
    public void onUndoSuccess(String mensaje) {
        view.onUndoSuccess(mensaje);
    }

    @Override
    public void onDestroy() {
        this.interactor = null;
        this.view = null;
    }
}
