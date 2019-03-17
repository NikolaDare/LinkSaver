package com.nikola.linkbar.Activitys;

import android.app.Dialog;
import android.app.FragmentManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nikola.linkbar.Fragments.InsertFragment;
import com.nikola.linkbar.Models.LinkViewModel;
import com.nikola.linkbar.data.adapters.SectionPageAdapter;
import com.nikola.linkbar.Fragments.FavoriteFragment;
import com.nikola.linkbar.Fragments.HomeFragment;
import com.nikola.linkbar.Fragments.ViewedFragment;
import com.nikola.linkbar.R;
import com.nikola.linkbar.data.db.model.Links;

import java.util.List;

public class Index extends AppCompatActivity {
    private static final String TAG = "Index";
    private Dialog dialog;

    private LinkViewModel linkViewModel;
    private  TabLayout tab;
    private SectionPageAdapter mSPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Log.d(TAG, "onCreate: Starting");

        dialog = new Dialog(this);
        linkViewModel = ViewModelProviders.of(this).get(LinkViewModel.class);
        linkViewModel.getAllLinks().observe(this, new Observer<List<Links>>() {
                    @Override
                    public void onChanged(@Nullable List<Links> movies) {

                        Log.d(TAG, "onChanged: "+movies);
                    }
                }


                    );
        mSPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        //Set up view pager with section adapter
        mViewPager = (ViewPager) findViewById(R.id.container);
        SetupViewPage(mViewPager);

        TabLayout tab= (TabLayout) findViewById(R.id.tabs);
        tab.setupWithViewPager(mViewPager);

    }
    public void ShowPopup(View v){
        ImageView btn;
        Button insert;

        dialog.setContentView(R.layout.insert_fragment);

        btn = (ImageView) dialog.findViewById(R.id.close);
        insert = (Button) dialog.findViewById(R.id.btn);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText tName = (TextInputEditText) dialog.findViewById(R.id.name);
                TextInputEditText tUrl = (TextInputEditText) dialog.findViewById(R.id.url);
                String name,url;
                name = String.valueOf(tName.getText());
                url = String.valueOf(tUrl.getText());
                Links link = new Links(name,url,false);
                linkViewModel.insert(link);
                dialog.dismiss();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    //creats SectionsPageAdapter and add fragments to SPA
    private void SetupViewPage(ViewPager VP){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());

        adapter.addFragment(new HomeFragment(),"Home");
        adapter.addFragment(new FavoriteFragment(),"Favorite");
        adapter.addFragment(new ViewedFragment(),"Viewed");

        VP.setAdapter(adapter);
    }
}
