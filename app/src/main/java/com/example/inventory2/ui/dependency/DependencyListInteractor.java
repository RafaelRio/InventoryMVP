package com.example.inventory2.ui.dependency;

import com.example.inventory2.ui.base.OnRepositoryListCallback;

import java.util.List;

public class DependencyListInteractor implements OnRepositoryListCallback {
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
