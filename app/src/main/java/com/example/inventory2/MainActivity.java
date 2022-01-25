package com.example.inventory2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.inventory2.databinding.ActivityMainBinding;

import java.util.HashSet;
import java.util.Set;


public class MainActivity extends AppCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    ActivityMainBinding binding;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.include.toolbar);
        setContentView(binding.getRoot());

        //Personalizar navigation drawer
        //Con la opcion 2 hay que comentar estas dos lineas para que la flecha funcione
        /*
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
*/
        //Inicializar el controlador de navegacion en la aplicacion
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_main);

        //Metodo que configura el componente NavigationView
        //Opcion 1: Mostrar siempre el icono hamburguesa -> setUpNavigationView();

        //Opcion 2: Mostrar los niveles de fragment mediante la flecha
        Set<Integer> topLevelDestination = new HashSet<>();
        topLevelDestination.add(R.id.addInventoryFragment);
        topLevelDestination.add(R.id.dependencyListFragment);
        topLevelDestination.add(R.id.settingsFragment);
        topLevelDestination.add(R.id.aboutUsFragment);

        //Configurar la barra de action para que funcione con NavigationUI

        //Este metodo gestiona el evento click del navigationView y se mostrara el id
        //del fragment del navController solo si el id del menu es igual que el id del fragment
        NavigationUI.setupWithNavController(binding.navigationview, navController);

        appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestination).setOpenableLayout(binding.drawerLayout).build();
        //Con este metodo gestionamos la barra de accion cuando hay varios niveles de navegacion
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    /*private void setUpNavigationView() {
        binding.navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.addInventoryFragment:
                        showAddInventory();
                        break;
                    case R.id.action_depedency:
                        showAddDependency();
                        break;
                    case R.id.action_aboutUs:
                        showAboutUs();
                        //binding.navigationview.getCheckedItem().setChecked(false);
                        break;
                    case R.id.action_actionSettings:
                        showSettings();
                        binding.navigationview.getCheckedItem().setChecked(false);
                        break;
                }
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }*/

    private void showAddDependency() {
        navController.navigate(R.id.dependencyListFragment);
    }

    private void showSettings() {
        navController.navigate(R.id.settingsFragment);
    }

    /**
     * Muestra el fragment para añadir un inventory
     */
    private void showAddInventory() {
        navController.navigate(R.id.addInventoryFragment);
    }

    /**
     * Mostrar el fragment AboutUs con sus correspondientes inicializaciones
     */
    private void showAboutUs() {
        navController.navigate(R.id.aboutUsFragment);
    }

    /**
     * Cuando se pulsa sobre el icono de la flecha debe ser el componente NAVIGATIONUI
     * quien gestione la navegacion
     *
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.content_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            /*case R.id.action_search:
                Toast.makeText(this, "Se ha pulsado el search", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                binding.drawerLayout.openDrawer(GravityCompat.START);
                return true;*/
            default:
                //Si lsos fragments modifican el menu de la Activity se devuelve false
                return false;
        }
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
        NavController navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_main);
        if (pref.getKey().equals(getString(R.string.key_account))) {
            navController.navigate(R.id.action_settingsFragment_to_accountFragment);
        }
        return true;
    }
}