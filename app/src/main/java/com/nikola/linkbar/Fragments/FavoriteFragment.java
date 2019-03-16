package com.nikola.linkbar.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.nikola.linkbar.R;

import static android.view.View.OnClickListener;

public class FavoriteFragment extends Fragment {
    private static final String TAG = "Fragment Favorite";
    private Button btn;
    private Button addbtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favortite_fragment,container,false);
        btn = (Button) view.findViewById(R.id.btnTest2);

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: CLicked"+TAG);
                Toast.makeText(getActivity(),"This is "+TAG,Toast.LENGTH_SHORT).show();
            }
        });
        return view;


    }
}