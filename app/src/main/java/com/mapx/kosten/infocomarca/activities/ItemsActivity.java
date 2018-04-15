package com.mapx.kosten.infocomarca.activities;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.mapx.kosten.infocomarca.CustomItemClickListener;
import com.mapx.kosten.infocomarca.R;
import com.mapx.kosten.infocomarca.adapters.listItemAdapter;
import com.mapx.kosten.infocomarca.objects.itemList;

import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AppCompatActivity
        implements CustomItemClickListener {

    private final String TAG = "ItemsActivity";

    // declaration of variables
    private Toolbar mToolbar;
    private LinearLayoutManager mLinearLayoutMgr;
    private RecyclerView mRecyView;
    private String mItemsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        // instantiation of variables
        mToolbar = (Toolbar) findViewById(R.id.toolBarLayout_main);
        mRecyView = (RecyclerView) findViewById(R.id.recyView_item);

        // toolbar
        setSupportActionBar(mToolbar);

        // obtenemos datos pasado por Activity previo
        Bundle bundle = getIntent().getExtras();
        mItemsArray = bundle.getString("ItemsTitle");

        getSupportActionBar().setTitle(mItemsArray);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // linear layout manager & recyclerView
        mLinearLayoutMgr = new LinearLayoutManager(this);
        mRecyView.setLayoutManager(mLinearLayoutMgr);
        mRecyView.setHasFixedSize(true);
        mRecyView.setItemAnimator(new DefaultItemAnimator());

        // cargo lista de newspapers
        loadItemsList(mItemsArray);

    }

    @Override
    // listener de los iconos del menu de la toolbar
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onItemClick(View view, int position) {
        Intent intent_item = new Intent(view.getContext(), WebActivity.class);
        intent_item.putExtra("ItemsTitle", mItemsArray);
        intent_item.putExtra("ItemPosition", position);
        startActivity(intent_item);

    }

    // funcion que carga la lista de items desde arrays.xml
    public void loadItemsList(String itemsArray) {
        boolean status;
        listItemAdapter listAdapter;
        List<itemList> itemsList;
        String[] itemsName;
        String[] itemsSubName;
        TypedArray itemsImage;

        itemsList = new ArrayList<>();
        status=true;
        switch (itemsArray) {
            case "Diarios":
                // cargo los campos de los Item almacenados en Values/array.xml
                itemsName = getResources().getStringArray(R.array.news_name);
                itemsSubName = getResources().getStringArray(R.array.news_url);
                itemsImage = getResources().obtainTypedArray(R.array.news_image);
                for (int i=0; i < itemsName.length; i++)  {
                    itemsList.add(new itemList(itemsName[i], itemsSubName[i], itemsImage.getResourceId(i,-1)));
                }
                break;
            case "Radios":
                // cargo los campos de los Item almacenados en Values/array.xml
                itemsName = getResources().getStringArray(R.array.radio_name);
                itemsSubName = getResources().getStringArray(R.array.radio_sub_name);
                itemsImage = getResources().obtainTypedArray(R.array.radio_image);
                for (int i=0; i < itemsName.length; i++)  {
                    itemsList.add(new itemList(itemsName[i], itemsSubName[i], itemsImage.getResourceId(i,-1)));
                }
                break;
            default:
                status=false;
        }

        listAdapter = new listItemAdapter(itemsList);
        mRecyView.setAdapter(listAdapter);
        listAdapter.setClickListener(this);
    }


}
