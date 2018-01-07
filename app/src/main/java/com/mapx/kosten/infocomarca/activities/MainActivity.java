package com.mapx.kosten.infocomarca.activities;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mapx.kosten.infocomarca.R;
import com.mapx.kosten.infocomarca.fragments.AboutFragment;
import com.mapx.kosten.infocomarca.fragments.MainFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = "MainActivity";

    // declaration of variables
    private Toolbar mToolbar;
    private DrawerLayout mDrawLayout;
    private NavigationView mNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiation of variables
        mToolbar = (Toolbar) findViewById(R.id.toolBarLayout_main);
        mDrawLayout = (DrawerLayout)findViewById(R.id.drawerLayout_main);
        mNavView = (NavigationView)findViewById(R.id.navviewLayout_main);

        // toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("infoComarca");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_reorder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // navigation view
        mNavView.setCheckedItem(R.id.menu_principal);
        mNavView.setNavigationItemSelectedListener(this);

    }

    @Override
    // listener de los iconos del drawer
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_principal:
                //Log.i(TAG, "Menu Principal");
                changeFragment(new MainFragment(), menuItem.getTitle().toString());
                //menuItem.setChecked(true);
                break;
            case R.id.menu_diarios:
                Log.i(TAG, "Menu Diarios");
                //changeFragment(new MainFragment(), menuItem.getTitle().toString());
                break;
            case R.id.menu_radios:
                //Log.i(TAG, "Menu Radios");
                //changeFragment(new MainFragment(), menuItem.getTitle().toString());
                break;
            case R.id.menu_info:
                //Log.i(TAG, "Menu Información");
                changeFragment(new AboutFragment(), menuItem.getTitle().toString());
                break;
        }
        mDrawLayout.closeDrawers();
        return true;
    }

    @Override
    // listener de los iconos del menu de la toolbar
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case android.R.id.home:
                mDrawLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.item_info:
                //Log.i(TAG, "Menu información");
                changeFragment(new AboutFragment(), menuItem.getTitle().toString());
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    // infla el menu de la toolbar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Cambia un fragment por otro
    private void changeFragment(Fragment f, String title) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, f)
                .commit();
        getSupportActionBar().setTitle(title);
    }
}
