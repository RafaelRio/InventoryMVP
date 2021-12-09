package com.example.inventory2.data.repository;

import com.example.inventory2.model.Dependency;
import com.example.inventory2.ui.base.OnRepositoryListCallback;
import com.example.inventory2.ui.dependency.DependencyListContract;

import java.util.ArrayList;
import java.util.Collections;

public class DependencyRepository implements DependencyListContract.Repository {

    private static DependencyRepository instance;
    private OnRepositoryListCallback callback;
    private ArrayList<Dependency> list;

    private DependencyRepository() {
        list = new ArrayList<>();
        initialice();
    }

    public static DependencyRepository getInstance(OnRepositoryListCallback callback) {
        if (instance == null) {
            instance = new DependencyRepository();
        }
        instance.callback = callback;
        return instance;
    }

    private void initialice() {
        list.add(new Dependency("Aula de 1CFGS", "1CFGS", "3", null));
        list.add(new Dependency("Bula de 1CFGM", "1CFGM", "5", null));
        list.add(new Dependency("Fula de 2CFGS", "2CFGS", "1", null));
        list.add(new Dependency("Cula de 2CFGM", "2CFGM", "2", null));
        list.add(new Dependency("Big Data", "BIG", "4", null));
    }

    @Override
    public void getList() {
        callback.onSuccess(list);
        Collections.sort(list);
    }

    @Override
    public void delete(Dependency dependency) {
        list.remove(dependency);
        callback.onDeleteSuccess("Se ha eliminado la dependencia " + dependency);
    }

    @Override
    public void undo(Dependency dependency) {

    }
}
