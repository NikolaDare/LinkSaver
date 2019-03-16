package com.nikola.linkbar.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nikola.linkbar.Models.LinkViewModel;
import com.nikola.linkbar.data.adapters.LinkAdapter;
import com.nikola.linkbar.data.db.model.Links;
import com.nikola.linkbar.R;

import java.util.List;

public class HomeFragment extends Fragment{
    private static final String TAG = "Fragment Home";

    private List<Links> LinkList;
    private RecyclerView recyclerView;
    private LinkAdapter mAdapter;
    private View view;
    private LinkViewModel linkViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyV);

        mAdapter = new LinkAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        linkViewModel = ViewModelProviders.of(getActivity()).get(LinkViewModel.class);
        linkViewModel.getAllLinks().observe(getActivity(), new Observer<List<Links>>() {
            @Override
            public void onChanged(@Nullable List<Links> links) {
                Log.d(TAG, "onChanged: "+links);
                mAdapter.setLinks(links);
            }
        });

        mAdapter.setOnItemClicked(new LinkAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG, "onItemClick: "+mAdapter.getLink(position));
                linkViewModel.delete(mAdapter.getLink(position));
                mAdapter.remove(position);
            }
        });

        setData();
        return view;
    }



    private void setData(){

        mAdapter.notifyDataSetChanged();
    }
}
