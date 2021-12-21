package com.example.inventory2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.inventory2.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity  implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_aboutUs:
                Toast.makeText(this, "Se ha pulsado el about us", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_actionSettings:
                Toast.makeText(this, "Se ha pulsado el preferencias", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_search:
                Toast.makeText(this, "Se ha pulsado el search", Toast.LENGTH_SHORT).show();
                return true;

            default:
                //Si lsos fragments modifican el menu de la Activity se devuelve false
                return false;
        }
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
        NavController navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_container);
        if (pref.getKey().equals(getString(R.string.key_account))){
            navController.navigate(R.id.action_settingsFragment_to_accountFragment);
        }
        return true;
    }
}