package com.nikola.linkbar.data.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nikola.linkbar.Models.LinkViewModel;
import com.nikola.linkbar.data.db.model.Links;
import com.nikola.linkbar.R;

import java.util.ArrayList;
import java.util.List;

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.mViewHolder> {
    private List<Links> LinkList= new ArrayList<>();
    private static final String TAG = "LinkAdapter";

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(View v,int position);
        void onStarClick(View v,int pos);
    }

    public void setOnItemClicked(OnItemClickListener listener){
        mListener =listener;
    }

    public class mViewHolder extends RecyclerView.ViewHolder{

        public TextView mTitle,mDesc;
        public ImageView mStar;
        public boolean mClicked;
        public RelativeLayout mLay;
        public ImageView mExit;

        public mViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            mExit = (ImageView) itemView.findViewById(R.id.delete);
            mTitle = (TextView) itemView.findViewById(R.id.mTitle);
            mDesc = (TextView) itemView.findViewById(R.id.mDesc);
            mStar = (ImageView) itemView.findViewById(R.id.fav);
            mLay = (RelativeLayout) itemView.findViewById(R.id.mId);
            mExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position = getLayoutPosition();

                        if (position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(v,position);
                        }
                    }
                }
            });
            mStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position = getLayoutPosition();

                        if (position!=RecyclerView.NO_POSITION){
                            listener.onStarClick(v,position);
                        }
                    }
                }
            });
        }


    }


    @Override
    public mViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false);
        return new mViewHolder(item,mListener);
    }

    @Override
    public void onBindViewHolder(final mViewHolder holder, final int position) {
        final Links ln = LinkList.get(position);
        holder.mLay.setTag(ln.getId());
        holder.mClicked=true;
        holder.mTitle.setText(ln.getTitle());
        holder.mDesc.setText(ln.getDesc());
        if (ln.isFavorite()){
            holder.mStar.setImageResource(android.R.drawable.star_on);
        }
    }

    public void setLinks(List<Links> LinksList){

        this.LinkList = LinksList;
        notifyDataSetChanged();
    }

    public Links getLink(int pos){
        return LinkList.get(pos);
    }
    public void remove(int pos){
        if (LinkList.size()!=0){
            LinkList.remove(pos);
        }
        notifyItemRemoved(pos);
    }
    @Override
    public int getItemCount() {
        return LinkList.size();
    }



}
