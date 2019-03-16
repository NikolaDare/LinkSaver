package com.nikola.linkbar.data.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "list")
public class Links {
    @ColumnInfo(name = "Id")
    @PrimaryKey(autoGenerate = true)
    private int mId;

    @ColumnInfo(name = "Title")
    private String mTitle;
    @ColumnInfo(name = "Desc")
    private String mDesc;

    public Links(String mTitle, String mDesc) {
        this.mTitle = mTitle;
        this.mDesc = mDesc;

    }

    @Ignore
    public Links() {
    }

    public void setId(int mId) {
        this.mId=mId;
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

    @Ignore
    @Override
    public String toString() {
        return "Links{" +
                "Title=" + mTitle + "\n" +
                ", Desc=" + mDesc + "\n" +
                ", Id=" + mId + "\n" +
                '}';
    }
}
