package com.nikola.linkbar.rep;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.nikola.linkbar.data.db.model.Links;
import com.nikola.linkbar.data.db.dao.LinkDao;
import com.nikola.linkbar.data.db.LinkDatabase;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class linkRepository {

    private LinkDao linkDao;
    private LiveData<List<Links>> allLinks;
    private LiveData<List<Links>> allFav;
    private LiveData<List<Links>> allViewed;

    public linkRepository(Application application){
        LinkDatabase database = LinkDatabase.getInstance(application);
        linkDao = database.linkDao();
        allLinks = linkDao.getAllLinks();
        allFav = linkDao.getAllFav();
        allViewed = linkDao.getAllViewed();
    }

//    public void insert(final Links link){
//        final Observable operationObservable = Observable.create(new ObservableOnSubscribe() {
//            @Override
//            public void subscribe(ObservableEmitter emitter) throws Exception {
//                linkDao.insert(link);
//            }
//        }).publish();
//    }
    public  void insert(Links link){ new InsertLinkAsyncTask(linkDao).execute(link);}
    public void update(Links link){ new UpdateLinkAsyncTask(linkDao).execute(link);}

    public void delete(Links link){ new DeleteLinkAsyncTask(linkDao).execute(link);}

    public void deleteAll(){new DeleteAllLinkAsyncTask(linkDao).execute();}

    public LiveData<List<Links>> getAllLinks(){
        return allLinks;
    }

    public LiveData<List<Links>> getAllFav(){
        return allFav;
    }

    public LiveData<List<Links>> getViewed(){
        return allViewed;
    }
    //********************************ASYNC TASKS*****************************************//

    private static class InsertLinkAsyncTask extends AsyncTask<Links,Void,Void>{

        private LinkDao linkDao;

        private InsertLinkAsyncTask(LinkDao linkDao){
            this.linkDao=linkDao;
        }

        @Override
        protected Void doInBackground(Links... links) {
            linkDao.insert(links[0]);
            return null;
        }
    }

    private static class UpdateLinkAsyncTask extends  AsyncTask<Links,Void,Void>{
        private LinkDao linkDao;

        private UpdateLinkAsyncTask(LinkDao linkDao){this.linkDao=linkDao;}
        @Override
        protected Void doInBackground(Links... links) {
            linkDao.update(links[0]);
            return null;
        }
    }

    private static class DeleteLinkAsyncTask extends AsyncTask<Links,Void,Void>{

        private LinkDao linkDao;

        private DeleteLinkAsyncTask(LinkDao linkDao){
            this.linkDao=linkDao;
        }


        @Override
        protected Void doInBackground(Links... link) {
            linkDao.delete(link[0]);
            return null;
        }
    }
    private static class DeleteAllLinkAsyncTask extends AsyncTask<Void,Void,Void>{

        private LinkDao linkDao;

        private DeleteAllLinkAsyncTask(LinkDao linkDao){
            this.linkDao=linkDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            linkDao.deleteAllLinks();
            return null;
        }
    }

}
