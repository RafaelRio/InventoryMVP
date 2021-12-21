package com.example.inventory2.ui.dependency;

import com.example.inventory2.data.repository.DependencyRepository;
import com.example.inventory2.model.Dependency;
import com.example.inventory2.ui.base.OnRepositoryCallback;

public class DependencyManageInteractor implements OnRepositoryCallback {

    DependencyManageContract.OnInteractorManagerListener listener;
    OnRepositoryCallback callback;

    public DependencyManageInteractor(DependencyManageContract.OnInteractorManagerListener listener) {
        this.listener = listener;
        callback = this;
    }

    public void add(Dependency dependency){
        DependencyRepository.getInstance().add(dependency, callback);
    }

    public void edit(Dependency dependency){
        DependencyRepository.getInstance().edit(dependency, callback);
    }

    @Override
    public void onSuccess(String message) {

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onAddSuccess(String message) {

    }

    @Override
    public void onEditSuccess() {

    }
}
