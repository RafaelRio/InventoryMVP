package com.example.inventory2.ui.dependency;

import com.example.inventory2.model.Dependency;

import java.util.ArrayList;
import java.util.List;

public class DependencyListPresenter implements DependencyListContract.Presenter, DependencyListContract.OnInteractorListener{

    private DependencyListContract.View view;
    private DependencyListInteractor interactor;

    public DependencyListPresenter(DependencyListContract.View view) {
        this.view = view;
        this.interactor = new DependencyListInteractor(this);
    }

    @Override
    public void load() {
        view.showProgressBar();
        interactor.load();
    }

    @Override
    public void delete(Dependency dependency) {

    }

    @Override
    public void undo(Dependency dependency) {

    }

    @Override
    public void onFailure(String mensaje) {

    }

    @Override
    public <T> void onSuccess(List<T> list) {
        if (list.size() == 0){
            view.showNoData();
        }else{
            view.showData((ArrayList<Dependency>) list);
        }
        view.hideProgressBar();
    }

    @Override
    public void onDeleteSuccess(String mensaje) {

    }

    @Override
    public void onUndoSuccess(String mensaje) {

    }

    @Override
    public void onDestroy() {
        this.interactor = null;
        this.view = null;
    }
}
