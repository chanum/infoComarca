package com.mapx.kosten.infocomarca.fragments;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mapx.kosten.infocomarca.CustomItemClickListener;
import com.mapx.kosten.infocomarca.R;
import com.mapx.kosten.infocomarca.activities.NewspaperActivity;
import com.mapx.kosten.infocomarca.activities.RadioActivity;
import com.mapx.kosten.infocomarca.adapters.listItemAdapter;
import com.mapx.kosten.infocomarca.objects.itemList;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment
        implements CustomItemClickListener {

    private LinearLayoutManager mLinearLayoutMgr;
    private RecyclerView mRecyView;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mRecyView = (RecyclerView) view.findViewById(R.id.recyView_item);
        mLinearLayoutMgr = new LinearLayoutManager(this.getActivity());
        mRecyView.setLayoutManager(mLinearLayoutMgr);
        mRecyView.setHasFixedSize(true);
        mRecyView.setItemAnimator(new DefaultItemAnimator());

        // cargo lista de items
        loadItemsList();

        // Inflate the layout for this fragment
        return view;
    }


    public void onItemClick(View view, int position) {

        switch (position) {
            case 0:
                Intent intent_newspaper = new Intent(view.getContext(), NewspaperActivity.class);
                //intent_newspaper.putExtra("ItemId", position);
                view.getContext().startActivity(intent_newspaper);
                break;
            case 1:
                Intent intent_radio = new Intent(view.getContext(), RadioActivity.class);
                //intent_radio.putExtra("ItemId", position);
                view.getContext().startActivity(intent_radio);
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

}
