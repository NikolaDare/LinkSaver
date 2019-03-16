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

    public LinkViewModel(Application application) {
        super(application);
//        Log.d(TAG, "LinkViewModel: "+rep.getAllLinks());
        rep = new linkRepository(application);
        allNotes = rep.getAllLinks();
    }

    public LiveData<List<Links>> getAllLinks(){
        return allNotes;
    }

    public void insert(Links link){
        rep.insert(link);
    }

    public void update(Links link){
//        rep.update(link);
    }

    public void delete(Links link){
        Log.d("Fragment", "delete: ");
        rep.delete(link);
    }

    public void deleteAll(){
        rep.deleteAll();
    }

}
