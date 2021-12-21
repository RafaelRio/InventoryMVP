package com.example.inventory2.ui.dependency;

import com.example.inventory2.model.Dependency;

public class DependencyManagePresenter implements DependencyManageContract.Presenter, DependencyManageContract.OnInteractorManagerListener {

    private DependencyManageContract.View view;
    private DependencyManageInteractor interactor;

    public DependencyManagePresenter(DependencyManageContract.View view) {
        this.view = view;
        this.interactor = new DependencyManageInteractor(this);
    }

    @Override
    public void onDestroy() {
        this.interactor = null;
        this.view = null;
    }

    @Override
    public void add(Dependency dependency) {
        interactor.add(dependency);
    }

    @Override
    public void edit(Dependency dependency) {
        interactor.edit(dependency);
    }

    @Override
    public void onSuccess(String message) {
        interactor.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        interactor.onFailure(message);
    }

    @Override
    public void onAddSuccess(String message) {
        interactor.onAddSuccess(message);
    }

    @Override
    public void onEditSuccess() {
        interactor.onEditSuccess();
    }

    @Override
    public void onShortNameEmpty() {
        view.setShortNameEmpty();
    }

    @Override
    public void onNameEmpty() {
        view.setNameEmpty();
    }

    @Override
    public void onDescriptionEmpty() {
        view.setDescriptionEmpty();
    }

    @Override
    public void onImageEmpty() {
        view.setImageEmpty();
    }
}
