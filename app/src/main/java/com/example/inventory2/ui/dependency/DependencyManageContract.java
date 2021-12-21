package com.example.inventory2.ui.dependency;

import com.example.inventory2.model.Dependency;
import com.example.inventory2.ui.base.BasePresenter;
import com.example.inventory2.ui.base.OnRepositoryCallback;
import com.example.inventory2.ui.base.OnRepositoryListCallback;
import com.example.inventory2.ui.base.iProgressView;

public interface DependencyManageContract {

    interface View extends OnRepositoryCallback{
        void setShortNameEmpty();
        void setNameEmpty();
        void setDescriptionEmpty();
        void setImageEmpty();
    }

    interface Presenter extends BasePresenter {
        void add(Dependency dependency);
        void edit(Dependency dependency);
    }

    interface Repository{
        void add(Dependency dependency, OnRepositoryCallback onRepositoryCallback);
        void edit(Dependency dependency, OnRepositoryCallback onRepositoryCallback);
    }

    interface OnInteractorManagerListener extends OnRepositoryCallback{
        void onShortNameEmpty();
        void onNameEmpty();
        void onDescriptionEmpty();
        void onImageEmpty();
    }
}
