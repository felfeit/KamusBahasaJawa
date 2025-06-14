package com.felfeit.kamusindojawa.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "KamusJawaIndonesia")
public class DictionaryModel {
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private int id;
    @ColumnInfo(name = "jawa")
    private String jawa;
    @ColumnInfo(name = "indonesia")
    private String indonesia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJawa() {
        return jawa;
    }

    public void setJawa(String jawa) {
        this.jawa = jawa;
    }

    public String getIndonesia() {
        return indonesia;
    }

    public void setIndonesia(String indonesia) {
        this.indonesia = indonesia;
    }
}
