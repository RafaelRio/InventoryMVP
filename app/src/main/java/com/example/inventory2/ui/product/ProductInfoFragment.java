package com.example.inventory2.ui.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.inventory2.databinding.FragmentProductInfoBinding;

public class ProductInfoFragment extends Fragment {
    public static final String TAG = "ProductInfo";
    FragmentProductInfoBinding binding;

    public static Fragment newInstance(Bundle bundle) {
        ProductInfoFragment fragment = new ProductInfoFragment();
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
        binding = FragmentProductInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
