package com.nikola.linkbar.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nikola.linkbar.Models.LinkViewModel;
import com.nikola.linkbar.R;
import com.nikola.linkbar.data.db.model.Links;

public class InsertFragment extends Fragment {
    private static final String TAG = "InsertFragment";
    private Button btn;
    private String name,url;
    private LinkViewModel linkViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.insert_fragment,container,false);
        TextView tName = (TextView) view.findViewById(R.id.name);
        TextView tUrl = (TextView) view.findViewById(R.id.url);

        name = (String) tName.getText();
        url = (String) tUrl.getText();

        btn = (Button) view.findViewById(R.id.btn);
        linkViewModel = ViewModelProviders.of(this).get(LinkViewModel.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: CLicked"+TAG);
                Links link = new Links(name,url);
                linkViewModel.insert(link);

            }
        });
        return view;


    }
}
