package com.nikola.linkbar.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nikola.linkbar.Models.LinkViewModel;
import com.nikola.linkbar.R;
import com.nikola.linkbar.data.adapters.LinkAdapter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ItemController  {

    private LinkViewModel linkViewModel;
    private LinkAdapter adapter;
    private Activity act;

    public ItemController(LinkViewModel linkViewModel, LinkAdapter adapter,Activity act) {
        this.linkViewModel = linkViewModel;
        this.adapter = adapter;
        this.act = act;
    }

    public void OnClick() {
        adapter.setOnItemClicked(new LinkAdapter.OnItemClickListener() {

            private ImageView btnStar;

            @Override
            public void onItemClick(View v, int position) {

                linkViewModel.delete(adapter.getLink(position));
                adapter.remove(position);
            }

            @Override
            public void onStarClick(View v, int pos) {

                boolean temp = adapter.getLink(pos).isFavorite();

                btnStar = (ImageView) v.findViewById(R.id.fav);
                if (adapter.getLink(pos).isFavorite()) {
                    btnStar.setImageResource(android.R.drawable.star_on);
                } else {
                    btnStar.setImageResource(android.R.drawable.star_off);
                }

                adapter.getLink(pos).setFavorite(!temp);
                linkViewModel.update(adapter.getLink(pos));
            }

            @Override
            public void onRowClick(View v, int position) {
                String query = null;
                String url = null;
                Uri uriUrl = null;

                query = adapter.getLink(position).getDesc();

                Long tsLong = System.currentTimeMillis()/1000;
                adapter.getLink(position).setViewed(Integer.valueOf(String.valueOf(tsLong)));

                Log.d("TIME", "onRowClick: "+tsLong);
                linkViewModel.update(adapter.getLink(position));


                if (query.startsWith("http://") || query.startsWith("https://") || query.startsWith("www.")) {
                    if (query.startsWith("www.")) {
                        url = "http://" + query;
                        uriUrl = Uri.parse(url);
                    } else {
                        url = query;
                        uriUrl = Uri.parse(url);
                    }
                } else {
                    try {
                        query = URLEncoder.encode(query, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    url = "http://www.google.com/search?q=" + query;
                    uriUrl = Uri.parse(url);
                }

                Intent intent = new Intent(Intent.ACTION_VIEW,uriUrl);
                act.startActivity(intent);
            }
        });
    }


}
