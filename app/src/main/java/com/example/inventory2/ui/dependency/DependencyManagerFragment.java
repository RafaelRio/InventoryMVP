package com.example.inventory2.ui.dependency;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.InventoryApplication;
import com.example.inventory2.MainActivity;
import com.example.inventory2.R;
import com.example.inventory2.databinding.FragmentDependencyManagerBinding;
import com.example.inventory2.model.Dependency;
import com.example.inventory2.ui.splash.splashActivity;

import java.util.List;
import java.util.Random;


public class DependencyManagerFragment extends Fragment implements DependencyManageContract.View{

    private static final String TAG = "DependencyManagerFragment";
    private FragmentDependencyManagerBinding binding;
    private DependencyManageContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DependencyManagePresenter(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        if(DependencyManagerFragmentArgs.fromBundle(getArguments()).getDependency()!= null){
            //Se trata de editar
            //1.Cambiar titulo
            getActivity().setTitle(getString(R.string.titleEditDependency));
            //2. Rellenar los campos con datos
            initView(DependencyManagerFragmentArgs.fromBundle(getArguments()).getDependency());
            //3. Modifico el icono del fab
            initFabEdit();
        }
        else {
            //Estamos en añadir
            initFabAdd();
        }
    }

    private void initFabAdd() {
    }

    private void initFabEdit() {
        binding.floatingActionButton.setImageResource(R.drawable.ic_action_mail);
        binding.tieShortName.setEnabled(false);
        //binding.floatingActionButton.setOnClickListener(view -> presenter.edit(getDependency()));
    }

    private void initView(Dependency dependency) {
        binding.tieShortName.setText(dependency.getShortName());
        binding.tieName.setText(dependency.getName());
        binding.tieDescription.setText(dependency.getDescription());
        binding.tieImage.setText(dependency.getImage());
    }

    private Dependency getDependency(){
        Dependency dependency = new Dependency();
        dependency.setShortName(binding.tieShortName.getText().toString());
        dependency.setName(binding.tieName.getText().toString());
        dependency.setDescription(binding.tieDescription.getText().toString());
        dependency.setImage(binding.tieImage.getText().toString());
        return dependency;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentDependencyManagerBinding.inflate(inflater, container, false);
        //binding.floatingActionButton.setOnClickListener(view -> presenter.add(new Dependency(binding.tieShortName.getText().toString(),binding.tieName.getText().toString(),binding.tieDescription.getText().toString(),binding.tieImage.getText().toString())));
        Log.d(TAG, "FragmentDependencyManage -> onCreateView()");
        return binding.getRoot();

    }

    @Override
    public void setShortNameEmpty() {

    }

    @Override
    public void setNameEmpty() {

    }

    @Override
    public void setDescriptionEmpty() {

    }

    @Override
    public void setImageEmpty() {

    }

    @Override
    public void onAddSuccess(String message){
        //1. Crear un Bundle que almacene la dependencia
        Bundle bundle = new Bundle();
        bundle.putSerializable(Dependency.TAG, getDependency());

        //2. Crear un intent
        //Intent intent = new Intent(getActivity(), splashActivity.class);
        //intent.putExtras(bundle);

        //3. Crear el PendingIntent
        PendingIntent pendingIntent = new NavDeepLinkBuilder(getActivity())
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.dependencyManagerFragment)
                .setArguments(bundle)
                .createPendingIntent();

        //5. Crear la notificacion
        /**
         * EL TAG DEL BUNDLE SE DEBE LLAMAR IGUAL QUE EL ARGUMENTO QUE SE HA ESTABLECIDO
         * EN SAFEARGS -> que crea automaticamente el metodo segun el nombre del argumento
         *
         * Dependency.TAG = dependency
         * El metodo de SafeArgs es getDependency()
         */
        Notification.Builder builder =
                new Notification.Builder(getActivity(), InventoryApplication.IDCHANNEL)
                .setSmallIcon(R.mipmap.ic_letrai)
                .setContentTitle(getResources().getString(R.string.notification_title))
                .setContentText(message);

        //6. Añadir la notificacion al Manager
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(), builder.build());


        NavHostFragment.findNavController(this).navigateUp();
    }

    @Override
    public void onEditSuccess() {

    }

    @Override
    public void onSuccess(String message) {

    }

    @Override
    public void onFailure(String mensaje) {

    }
}