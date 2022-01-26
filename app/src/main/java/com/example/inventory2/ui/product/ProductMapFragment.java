package com.example.inventory2.ui.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.inventory2.databinding.FragmentProductMapBinding;

public class ProductMapFragment extends Fragment {
    public static final String TAG = "ProductMap";
    FragmentProductMapBinding binding;

    public static Fragment newInstance(Bundle bundle) {
        ProductMapFragment fragment = new ProductMapFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductMapBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
