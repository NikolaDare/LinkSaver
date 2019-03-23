package com.nikola.linkbar.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.nikola.linkbar.Models.LinkViewModel;
import com.nikola.linkbar.R;
import com.nikola.linkbar.data.adapters.LinkAdapter;
import com.nikola.linkbar.data.db.model.Links;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import static android.view.View.OnClickListener;

public class ViewedFragment extends Fragment {
    private static final String TAG = "Fragment Viewed";

    private RecyclerView recyclerView;
    private LinkAdapter mAdapter;
    private LinkViewModel linkViewModel;
    private ItemController controller;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewed_fragment,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.viewedRec);

        mAdapter = new LinkAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        linkViewModel= ViewModelProviders.of(getActivity()).get(LinkViewModel.class);

        linkViewModel.getAllViewed().observe(getActivity(), new Observer<List<Links>>() {
            @Override
            public void onChanged(@Nullable List<Links> links) {
                mAdapter.setLinks(links);
                Log.d(TAG, "onChanged: "+links);
            }
        });

        controller = new ItemController(linkViewModel,mAdapter,getActivity());

        controller.OnClick();
        setData();
        return view;
    }

    private void setData(){

        mAdapter.notifyDataSetChanged();
    }
}
