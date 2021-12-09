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
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory2.R;
import com.example.inventory2.databinding.FragmentDependencyListBinding;
import com.example.inventory2.model.Dependency;
import com.example.inventory2.ui.base.BaseDialogFragment;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class DependencyListFragment extends Fragment implements DependencyListContract.View, DependencyAdapter.OnManageDependencyListener {

    private FragmentDependencyListBinding binding;
    private DependencyAdapter adapter;
    private DependencyListContract.Presenter presenter;


    private Dependency deleted;

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
                presenter.order();
                return true;
            case R.id.action_order_byDescription:
                adapter.orderByDescription();
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
        Toast.makeText(getActivity(), "Editar dependencia " + dependency.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteDependency(Dependency dependency) {
        Bundle bundle = new Bundle();
        bundle.putString(BaseDialogFragment.TITLE, getString(R.string.title_delete_dependency));
        bundle.putString(BaseDialogFragment.MESSAGE, String.format(getString(R.string.message_delete_dependency), dependency.getShortName()));
        NavHostFragment.findNavController(this).
                navigate(R.id.action_dependencyListFragment_to_baseDialogFragment, bundle);
        //Registrar el listener del BaseDialog. Este codigo sirve para comunicar dos Fragments
        //en el cual el padre necesita un resultado del hijo. SI SE USA LA LIBRERIA DE SOPORTE
        //SE DEBE LLAMAR A getSupportFragmentManager()
        getActivity().getSupportFragmentManager().setFragmentResultListener(BaseDialogFragment.REQUEST, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                //Si la respuesta del usuario = true, se llama al presentador
                if (bundle.getBoolean(BaseDialogFragment.KEY_BUNDLE)) {
                    deleted = dependency;
                    presenter.delete(dependency);
                }
            }
        });

    }
    //endregion

    //region Metodos que vienen de la respuesta del REPOSITORIO
    @Override
    public void onFailure(String mensaje) {

    }

    @Override
    public <T> void onSuccess(List<T> list) {

    }

    /**
     * Metodo que muestra un SnackBar con la opcion UNDO
     * @param mensaje
     */
    @Override
    public void onDeleteSuccess(String mensaje) {
        Snackbar.make(getView(), mensaje, BaseTransientBottomBar.LENGTH_LONG).show();
        adapter.delete(deleted);
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

    @Override
    public void showDataOrder() {
        adapter.order();
    }

    @Override
    public void showDataInverseOrder() {
        adapter.inverseOrder();
    }
    //endregion
}