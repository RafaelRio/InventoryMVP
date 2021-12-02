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
import com.example.inventory2.model.Dependency;

import java.util.ArrayList;
import java.util.List;

public class DependencyListFragment extends Fragment implements DependencyListContract.View, DependencyAdapter.OnManageDependencyListener {

    private FragmentDependencyListBinding binding;
    private DependencyAdapter adapter;
    private DependencyListContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1. Se debe indicar a la activity que se quiere modificar el menu
        setHasOptionsMenu(true);
        //2. Se inicializa el presenter
        presenter = new DependencyListPresenter(this);
    }


    //2. Sobreescribir el metodo onCreateOptionsMenu para añadir el menu del fragment
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fragmentlist_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //3. Implementar las acciones especificas (item) del menu del fragment
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

    @Override
    public void onStart() {
        super.onStart();
        presenter.load();
    }

    /**
     * Este metodo inicializa el componente recyclerView
     */
    private void initRvDependency() {
        //1. Inicializar adapter
        adapter = new DependencyAdapter(new ArrayList<>(), this);

        //2. OBLIGATORIO -> Se debe indicar que el diseño (layout) tendrá el recyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        //3. Asignar el Layout al recyclerView
        binding.rvDependency.setLayoutManager(linearLayoutManager);

        //4. Asigna a la vista sus datos (modelo)
        binding.rvDependency.setAdapter(adapter);
    }

    //region Metodos que vienen de la interfaz del ADAPTER
    @Override
    public void onEditDependency(Dependency dependency) {

    }

    @Override
    public void onDeleteDependency(Dependency dependency) {

    }
    //endregion

    //region Metodos que vienen de la respuesta del REPOSITORIO
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
    //endregion

    //region Metodos que vienen del requisito que se debe mostrar una vista de PROGRESSBAR
    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showProgressBar() {

    }
    //endregion

    //region Metodos que vienen del contrato con el PRESENTER

    @Override
    public void showData(ArrayList<Dependency> list) {
        adapter.update(list);
    }

    @Override
    public void showNoData() {

    }
    //endregion
}