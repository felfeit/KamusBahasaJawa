package com.felfeit.kamusindojawa.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.felfeit.kamusindojawa.models.DictionaryModel;

import java.util.List;

@Dao
public interface DictionaryDao {

    @Query("SELECT * FROM KamusJawaIndonesia")
    LiveData<List<DictionaryModel>> getAllWords();

    @Query("SELECT * FROM KamusJawaIndonesia WHERE jawa LIKE :query OR indonesia LIKE :query")
    LiveData<List<DictionaryModel>> searchWords(String query);
}