package com.apps.cyberfox_ethicalhackingguide;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    AlertDialog quit;
    AlertDialog.Builder quitbuild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(this);
        loadFragment( new HomeFragment() );

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        quitbuild = new AlertDialog.Builder(this);
        quitbuild.setCancelable(false);
        quitbuild.setMessage("Do you want to quit CyberFox?");
        quitbuild.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                System.exit(0);
            }
        });
        quitbuild.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"Accidental touches, we all face ;)", Toast.LENGTH_SHORT).show();
            }
        });
        quit = quitbuild.create();
    }

    private boolean loadFragment(Fragment fragment){
        if( fragment != null ){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch ( menuItem.getItemId() ){
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_disclaimer:
                fragment = new DisclaimerFragment();
                break;
            case R.id.navigation_aboutus:
                fragment = new AboutusFragment();
                break;
        }
        return loadFragment(fragment);
    }

    @Override
    public void onBackPressed(){
        quit.show();
    }
}
