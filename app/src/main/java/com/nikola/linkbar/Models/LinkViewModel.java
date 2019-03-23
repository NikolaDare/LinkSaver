package com.nikola.linkbar.Models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.nikola.linkbar.data.db.model.Links;
import com.nikola.linkbar.rep.linkRepository;

import java.util.List;

public class LinkViewModel extends AndroidViewModel {

    private static final String TAG = "Model";
    private linkRepository rep;
    private LiveData<List<Links>> allNotes;
    private LiveData<List<Links>> allFav;
    private LiveData<List<Links>> allViewed;

    public LinkViewModel(Application application) {
        super(application);

        rep = new linkRepository(application);
        allNotes = rep.getAllLinks();
        allFav=rep.getAllFav();
        allViewed= rep.getViewed();
    }

    public LiveData<List<Links>> getAllViewed(){
        return allViewed;
    }

    public LiveData<List<Links>> getAllFav(){
        return allFav;
    }

    public LiveData<List<Links>> getAllLinks(){
        return allNotes;
    }

    public void insert(Links link){
        rep.insert(link);
    }

    public void update(Links link){
       rep.update(link);
    }

    public void delete(Links link){
        rep.delete(link);
    }

    public void deleteAll(){
        rep.deleteAll();
    }

}
