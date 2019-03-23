package com.nikola.linkbar.data.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "list")
public class Links {

    @ColumnInfo(name = "Id")
    @PrimaryKey(autoGenerate = true)
    private int mId;

    @ColumnInfo(name = "Title")
    private String mTitle;
    @ColumnInfo(name = "Desc")
    private String mDesc;

    private int viewed;

    private boolean favorite;
    public Links(String mTitle, String mDesc,boolean favorite,int viewed) {
        this.mTitle = mTitle;
        this.mDesc = mDesc;
        this.favorite= favorite;
        this.viewed = viewed;
    }

    public void setId(int mId) {
        this.mId=mId;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public int getViewed() {
        return viewed;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    @Ignore
    @Override
    public String toString() {
        return "Links{" +
                "Title=" + mTitle + "\n" +
                ", Desc=" + mDesc + "\n" +
                ", Id=" + mId + "\n" +
                ", Favorite=" + favorite + "\n" +
                ", Viewed=" + viewed + "\n" +
                '}';
    }
}
