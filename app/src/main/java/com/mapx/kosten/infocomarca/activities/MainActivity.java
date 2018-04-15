package com.mapx.kosten.infocomarca.activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
        getSupportActionBar().setTitle("infoPatagones");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_reorder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // navigation view
        mNavView.setNavigationItemSelectedListener(this);

        // set main fragment
        changeFragment(new MainFragment(), "infoPatagones");
    }

    @Override
    // listener de los iconos del drawer
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_principal:
                changeFragment(new MainFragment(), menuItem.getTitle().toString());
                menuItem.setChecked(true);
                break;
            case R.id.menu_diarios:
                //menuItem.setChecked(true);
                Intent intent_news = new Intent(MainActivity.this, ItemsActivity.class);
                intent_news.putExtra("ItemsTitle", "Diarios");
                startActivity(intent_news);
                break;
            case R.id.menu_radios:
                //menuItem.setChecked(true);
                Intent intent_radio = new Intent(MainActivity.this, ItemsActivity.class);
                intent_radio.putExtra("ItemsTitle", "Radios");
                startActivity(intent_radio);
                break;
            case R.id.menu_info:
                changeFragment(new AboutFragment(), menuItem.getTitle().toString());
                //menuItem.setChecked(true);
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
