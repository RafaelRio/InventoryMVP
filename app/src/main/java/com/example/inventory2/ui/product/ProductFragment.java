package com.example.inventory2.ui.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.inventory2.R;
import com.example.inventory2.databinding.FragmentProductBinding;

public class ProductFragment extends Fragment {

    FragmentProductBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavigation();
    }

    /**
     * Este metodo configura el listener del componenente BottomNavigationView
     */
    private void initNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_product_info:
                    loadFragment(ProductInfoFragment.newInstance(null));
                    break;

                case R.id.action_product_map:
                    loadFragment(ProductMapFragment.newInstance(null));
                    break;

                case R.id.action_product_description:
                    loadFragment(ProductDescriptionFragment.newInstance(null));
                    break;
            }
            return true;
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        loadFragment(ProductInfoFragment.newInstance(null));
    }

    /**
     * Este metodo carga un fragment child dentro de otro fragment. Para ello se utiliza
     * el metodo getChildFragmentManager para gestionar fragments anidados
     *
     * @param newInstance
     */
    private void loadFragment(Fragment newInstance) {
        if (newInstance != null) {
            getChildFragmentManager().beginTransaction().replace(R.id.productContent, newInstance).commit();
        }
    }
}