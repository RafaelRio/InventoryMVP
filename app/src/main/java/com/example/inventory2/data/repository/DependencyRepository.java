package com.example.inventory2.data.repository;

import com.example.inventory2.model.Dependency;
import com.example.inventory2.model.User;
import com.example.inventory2.ui.base.OnRepositoryListCallback;
import com.example.inventory2.ui.dependency.DependencyListContract;

import java.util.ArrayList;

public class DependencyRepository implements DependencyListContract.Repository{

    private OnRepositoryListCallback callback;
    private static DependencyRepository instance;
    private ArrayList<Dependency> list;

    public static DependencyRepository getInstance(OnRepositoryListCallback callback){
        if (instance == null){
            instance = new DependencyRepository();
        }
        instance.callback = callback;
        return instance;
    }

    private DependencyRepository(){
        list = new ArrayList<>();
        initialice();
    }

    private void initialice(){
        list.add(new Dependency("Aula de 1CFGS", "1CFGS", null, null));
        list.add(new Dependency("Aula de 1CFGM", "1CFGM", null, null));
        list.add(new Dependency("Aula de 2CFGS", "2CFGS", null, null));
        list.add(new Dependency("Aula de 2CFGM", "2CFGM", null, null));
        list.add(new Dependency("Big Data", "BIG", null, null));
    }

    @Override
    public void getList() {
        callback.onSuccess(list);
    }

    @Override
    public void delete(Dependency dependency) {

    }

    @Override
    public void undo(Dependency dependency) {

    }
}
