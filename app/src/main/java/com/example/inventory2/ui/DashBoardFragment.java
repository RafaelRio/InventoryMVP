package com.example.inventory2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.inventory2.R;
import com.example.inventory2.databinding.FragmentDashboardBinding;

public class DashBoardFragment extends Fragment implements View.OnClickListener {

    private FragmentDashboardBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        binding.btiInventory.setOnClickListener(this);
        binding.btiDependency.setOnClickListener(this);
        binding.btiProducts.setOnClickListener(this);
        binding.btiSections.setOnClickListener(this);
        binding.btiAboutUs.setOnClickListener(this);
        binding.btiSettings.setOnClickListener(this);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Es el metodo que maneja los eventos click de la interfaz
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btiAboutUs:
                showAboutUs();
                break;

            case R.id.btiInventory:
                showAddInventory();
                break;

            case R.id.btiDependency:
                showAddDependency();
                break;

            case R.id.btiSettings:
                showSettings();
                break;
        }
    }

    private void showAddDependency() {
        NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_dependencyListFragment);
    }

    private void showSettings() {
        NavHostFragment.findNavController(this).navigate(R.id.action_DashBoardFragment_to_settingsFragment);
    }

    /**
     * Muestra el fragment para añadir un inventory
     */
    private void showAddInventory() {
        NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_addInventoryFragment);
    }

    /**
     * Mostrar el fragment AboutUs con sus correspondientes inicializaciones
     */
    private void showAboutUs(){
        NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_aboutUsFragment);
    }
}