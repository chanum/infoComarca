package com.mapx.kosten.infocomarca.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mapx.kosten.infocomarca.CustomItemClickListener;
import com.mapx.kosten.infocomarca.R;
import com.mapx.kosten.infocomarca.activities.NewspaperActivity;
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
    private listItemAdapter mAdapter;
    private List<itemList> mItemList;

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

        mItemList = new ArrayList<>();

        mItemList.add(new itemList("Diarios", "-----", R.drawable.ic_action_news));
        mItemList.add(new itemList("Radios", "-----", R.drawable.ic_action_radio));
        mItemList.add(new itemList("Clima", "-----", R.drawable.ic_action_radio));
        mItemList.add(new itemList("Mareas", "-----", R.drawable.ic_action_radio));
        mItemList.add(new itemList("Clasificados", "-----", R.drawable.ic_action_radio));

        mAdapter = new listItemAdapter(mItemList);
        mRecyView.setAdapter(mAdapter);
        mAdapter.setClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }

    public void onItemClick(View view, int position) {

        Intent intent_newspaper = new Intent(view.getContext(), NewspaperActivity.class);
        intent_newspaper.putExtra("ItemId", position);
        view.getContext().startActivity(intent_newspaper);
    }

}
