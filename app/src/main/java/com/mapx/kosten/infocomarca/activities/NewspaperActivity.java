package com.mapx.kosten.infocomarca.activities;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;;
import android.view.MenuItem;
import android.view.View;

import com.mapx.kosten.infocomarca.CustomItemClickListener;
import com.mapx.kosten.infocomarca.R;
import com.mapx.kosten.infocomarca.adapters.listItemAdapter;
import com.mapx.kosten.infocomarca.objects.itemList;

import java.util.ArrayList;
import java.util.List;

public class NewspaperActivity extends AppCompatActivity
        implements CustomItemClickListener {

    private final String TAG = "NewspaperActivity";

    // declaration of variables
    private Toolbar mToolbar;
    private LinearLayoutManager mLinearLayoutMgr;
    private RecyclerView mRecyView;
    private List<itemList> mItemList;
    private String[] mItemsName;
    private String[] mItemsSubName;
    private TypedArray mItemsImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newspaper);

        // instantiation of variables
        mToolbar = (Toolbar) findViewById(R.id.toolBarLayout_main);
        mRecyView = (RecyclerView) findViewById(R.id.recyView_item);

        // toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Diarios");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // linear layout manager & recyclerView
        mLinearLayoutMgr = new LinearLayoutManager(this);
        mRecyView.setLayoutManager(mLinearLayoutMgr);
        mRecyView.setHasFixedSize(true);
        mRecyView.setItemAnimator(new DefaultItemAnimator());

        // cargo lista de newspapers
        loadItemsList();

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
        // TODO
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
        mItemsName = getResources().getStringArray(R.array.news_name);
        mItemsSubName = getResources().getStringArray(R.array.news_url);
        mItemsImage = getResources().obtainTypedArray(R.array.news_image);
        for (int i=0; i < mItemsName.length; i++)  {
            mItemList.add(new itemList(mItemsName[i], mItemsSubName[i], mItemsImage.getResourceId(i,-1)));
        }

        mAdapter = new listItemAdapter(mItemList);
        mRecyView.setAdapter(mAdapter);
        mAdapter.setClickListener(this);
    }

}
