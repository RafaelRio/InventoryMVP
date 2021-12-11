package com.example.inventory2.ui.dependency;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventory2.R;
import com.example.inventory2.databinding.FragmentDependencyManagerBinding;


public class DependencyManagerFragment extends Fragment {

    private FragmentDependencyManagerBinding binding;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        if(DependencyManagerFragmentArgs.fromBundle(getArguments()).getDependency()!= null){
            //Se trata de editar
            //1.Cambiar titulo
            getActivity().setTitle(getString(R.string.titleEditDependency));
            //2. Rellenar los campos con datos
            initView(DependencyManagerFragmentArgs.fromBundle(getArguments()));
            //3. Modifico el icono del fab
            initFabEdit();
        }
        else {
            //Estamos en añadir
        }
    }

    private void initFabEdit() {
    }

    private void initView(DependencyManagerFragmentArgs fromBundle) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dependency_manager, container, false);
    }
}