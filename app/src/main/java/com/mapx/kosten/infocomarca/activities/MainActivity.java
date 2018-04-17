package com.mapx.kosten.infocomarca.activities;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mapx.kosten.infocomarca.CustomItemClickListener;
import com.mapx.kosten.infocomarca.R;
import com.mapx.kosten.infocomarca.adapters.listItemAdapter;
import com.mapx.kosten.infocomarca.objects.itemList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CustomItemClickListener {

    private final String TAG = "MainActivity";

    // declaration of variables
    private Toolbar mToolbar;
    private DrawerLayout mDrawLayout;
    private NavigationView mNavView;
    private RecyclerView mRecyView;
    private LinearLayoutManager mLinearLayoutMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiation of variables
        mToolbar = (Toolbar) findViewById(R.id.toolBarLayout_main);
        mDrawLayout = (DrawerLayout)findViewById(R.id.drawerLayout_main);
        mNavView = (NavigationView)findViewById(R.id.navviewLayout_main);
        mRecyView = (RecyclerView)findViewById(R.id.recyView_item);

        // toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("infoPatagones");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_reorder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // navigation view
        mNavView.setNavigationItemSelectedListener(this);

        mLinearLayoutMgr = new LinearLayoutManager(this);
        mRecyView.setLayoutManager(mLinearLayoutMgr);
        mRecyView.setHasFixedSize(true);
        mRecyView.setItemAnimator(new DefaultItemAnimator());

        // cargo lista de items
        loadItemsList();

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent_item = new Intent(view.getContext(), ItemsActivity.class);
        switch (position) {
            case 0:
                intent_item.putExtra("ItemsTitle", "Diarios");
                view.getContext().startActivity(intent_item);
                break;
            case 1:
                intent_item.putExtra("ItemsTitle", "Radios");
                view.getContext().startActivity(intent_item);
                break;
        }
    }

    // funcion que carga la lista de items desde arrays.xml
    public void loadItemsList() {
        listItemAdapter mAdapter;
        List<itemList> mItemList;
        String[] mItemsName;
        String[] mItemsSubName;
        TypedArray mItemsImage;

        mItemList = new ArrayList<>();

        // cargo los campos de los Item almacenados en Values/array.xml
        mItemsName = getResources().getStringArray(R.array.items_name);
        mItemsSubName = getResources().getStringArray(R.array.items_sub_name);
        mItemsImage = getResources().obtainTypedArray(R.array.items_image);
        for (int i=0; i < mItemsName.length; i++)  {
            mItemList.add(new itemList(mItemsName[i], mItemsSubName[i], mItemsImage.getResourceId(i,-1)));
        }

        mAdapter = new listItemAdapter(mItemList);
        mRecyView.setAdapter(mAdapter);
        mAdapter.setClickListener(this);
    }

    @Override
    // listener de los iconos del drawer
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_principal:
                menuItem.setChecked(true);
                break;
            case R.id.menu_diarios:
                Intent intent_news = new Intent(MainActivity.this, ItemsActivity.class);
                intent_news.putExtra("ItemsTitle", "Diarios");
                startActivity(intent_news);
                break;
            case R.id.menu_radios:
                Intent intent_radio = new Intent(MainActivity.this, ItemsActivity.class);
                intent_radio.putExtra("ItemsTitle", "Radios");
                startActivity(intent_radio);
                break;
            case R.id.menu_info:
                Intent intent_about= new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent_about);
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
                Intent intent_about= new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent_about);
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

}
