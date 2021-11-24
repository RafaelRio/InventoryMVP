package com.example.inventory2.ui.inventory;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventory2.R;
import com.example.inventory2.databinding.FragmentAddInventoryBinding;


public class AddInventoryFragment extends Fragment {

    FragmentAddInventoryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddInventoryBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnOk.setOnClickListener(view1 -> {
            //En vez de crear dos acciones bidireccionales entre dos fragments, se usa el
            //navigateUp() que va al fragment padre
            NavHostFragment.findNavController(this).navigateUp();
        });
    }
}