package com.example.inventory2.ui.dependency;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory2.R;
import com.example.inventory2.databinding.FragmentDependencyListBinding;

public class DependencyListFragment extends Fragment {

    private FragmentDependencyListBinding binding;
    private DependencyAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1. Se debe indicar a la activity que se quiere modificar el menu
        setHasOptionsMenu(true);
    }

    //2. Sobreescribir el metodo onCreateOptionsMenu para añadir el menu del fragment
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fragmentlist_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //3. Implementar las acciones especificas (iten) del menu del fragment
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_orderdependency:
                Toast.makeText(getActivity(), "Se ha pulsado el ordenar dependencia", Toast.LENGTH_SHORT).show();
                return true;
            default:
                //Si lsos fragments modifican el menu de la Activity se devuelve false
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDependencyListBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRvDependency();
    }

    /**
     * Este metodo inicializa el componente recyclerView
     */
    private void initRvDependency() {
        //1. Inicializar adapter
        adapter = new DependencyAdapter();

        //2. OBLIGATORIO -> Se debe indicar que el diseño (layout) tendrá el recyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        //3. Asignar el Layout al recyclerView
        binding.rvDependency.setLayoutManager(linearLayoutManager);

        //4. Asigna a la vista sus datos (modelo)
        binding.rvDependency.setAdapter(adapter);
    }
}