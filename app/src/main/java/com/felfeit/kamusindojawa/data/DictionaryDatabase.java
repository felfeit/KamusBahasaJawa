package com.felfeit.kamusindojawa.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.felfeit.kamusindojawa.models.DictionaryModel;

@Database(entities = {DictionaryModel.class}, version = 1)
public abstract class DictionaryDatabase extends RoomDatabase {
    public abstract DictionaryDao dao();

    private static volatile DictionaryDatabase INSTANCE;

    public static DictionaryDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (DictionaryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    DictionaryDatabase.class,
                                    "kamus.db"
                            )
                            .createFromAsset("kamusjawaindonesia.sqlite")
                            .fallbackToDestructiveMigration(false)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
