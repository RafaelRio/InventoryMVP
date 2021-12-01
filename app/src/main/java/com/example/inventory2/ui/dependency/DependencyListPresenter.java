package com.example.inventory2.ui.dependency;

import com.example.inventory2.model.Dependency;

import java.util.List;

public class DependencyListPresenter implements DependencyListContract.Presenter, DependencyListContract.OnInteractorListener{
    @Override
    public void onDestroy() {

    }

    @Override
    public void load() {

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

    }

    @Override
    public void onDeleteSuccess(String mensaje) {

    }

    @Override
    public void onUndoSuccess(String mensaje) {

    }
}
