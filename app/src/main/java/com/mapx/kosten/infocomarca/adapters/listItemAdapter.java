package com.mapx.kosten.infocomarca.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mapx.kosten.infocomarca.CustomItemClickListener;
import com.mapx.kosten.infocomarca.objects.itemList;
import com.mapx.kosten.infocomarca.R;

import java.util.List;

/**
 * Created by orkeke on 2/12/18.
 */

public class listItemAdapter extends RecyclerView.Adapter<listItemAdapter.ViewHolder> {

    private List<itemList> mItem;
    private CustomItemClickListener mClickListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        int mItemId;
        CardView mCardView;
        TextView mNameItem;
        TextView mSubNameItem;
        ImageView mThumbItem;

        ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView)itemView.findViewById(R.id.cardView_item);
            mNameItem = (TextView)itemView.findViewById(R.id.txtView_itemName);
            mSubNameItem = (TextView)itemView.findViewById(R.id.txtView_itemSubName);
            mThumbItem = (ImageView) itemView.findViewById(R.id.imgView_itemThumb);

            itemView.setOnClickListener(new CardOnClickListener());
        }

        class CardOnClickListener implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) mClickListener.onItemClick(itemView, getAdapterPosition());
            }
        }
    }

    public void setClickListener(CustomItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public listItemAdapter(List<itemList> itemList) {
        mItem = itemList;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public listItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                       int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
        listItemAdapter.ViewHolder pvh = new listItemAdapter.ViewHolder(v);
        return pvh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(listItemAdapter.ViewHolder holder, int i) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mItemId = mItem.get(i).getItemId();
        holder.mNameItem.setText(mItem.get(i).getItemName());
        holder.mSubNameItem.setText(mItem.get(i).getItemSubName());
        holder.mThumbItem.setImageResource(mItem.get(i).getItemThumb());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mItem.size();
    }
}
