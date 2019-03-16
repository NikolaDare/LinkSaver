package com.nikola.linkbar.data.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.nikola.linkbar.data.db.dao.LinkDao;
import com.nikola.linkbar.data.db.model.Links;

import static android.support.constraint.Constraints.TAG;

@Database(entities = {Links.class},version = 1,exportSchema = false)
public abstract class LinkDatabase extends RoomDatabase {
    private static LinkDatabase instance;

    public abstract LinkDao linkDao();

    public static synchronized LinkDatabase getInstance(Context context){
        if (instance==null){

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LinkDatabase.class,"link_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private LinkDao noteDao;

        private PopulateDbAsyncTask(LinkDatabase db) {
            noteDao = db.linkDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Links("Title 1", "Description 1"));
            noteDao.insert(new Links("Title 2", "Description 2"));
            noteDao.insert(new Links("Title 3", "Description 3"));

            return null;
        }
    }
}
