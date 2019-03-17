package com.nikola.linkbar.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.nikola.linkbar.Models.LinkViewModel;
import com.nikola.linkbar.R;
import com.nikola.linkbar.data.adapters.LinkAdapter;
import com.nikola.linkbar.data.db.model.Links;

import java.util.List;

import static android.view.View.OnClickListener;

public class FavoriteFragment extends Fragment {
    private static final String TAG = "Fragment Favorite";

    private RecyclerView recyclerView;
    private LinkAdapter mAdapter;
    private LinkViewModel linkViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favortite_fragment,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.favRec);

        mAdapter = new LinkAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        linkViewModel = ViewModelProviders.of(getActivity()).get(LinkViewModel.class);
        linkViewModel.getAllFav().observe(getActivity(), new Observer<List<Links>>() {
            @Override
            public void onChanged(@Nullable List<Links> links) {
                Log.d(TAG, "onChanged: "+links);
                mAdapter.setLinks(links);
            }
        });
        mAdapter.setOnItemClicked(new LinkAdapter.OnItemClickListener() {
            private ImageView btnStar;

            @Override
            public void onItemClick(View v,int position) {


            }

            @Override
            public void onStarClick(View v,int pos) {

                btnStar = (ImageView) v.findViewById(R.id.fav);
                boolean temp = mAdapter.getLink(pos).isFavorite();
                if (!mAdapter.getLink(pos).isFavorite()){
                    btnStar.setImageResource(android.R.drawable.star_on);
                }else {
                    btnStar.setImageResource(android.R.drawable.star_off);
                }
                mAdapter.getLink(pos).setFavorite(!temp);
                Log.d(TAG, "onItemClick: "+temp);
                linkViewModel.update(mAdapter.getLink(pos));
            }
        });

//        btn.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: CLicked"+TAG);
//                Toast.makeText(getActivity(),"This is "+TAG,Toast.LENGTH_SHORT).show();
//            }
//        });
        setData();
        return view;


    }

    private void setData(){

        mAdapter.notifyDataSetChanged();
    }
}
