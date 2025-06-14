package com.felfeit.kamusindojawa.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.felfeit.kamusindojawa.models.DictionaryModel;

import java.util.List;

public class DictionaryRepository {
    private DictionaryDao dao;
    private LiveData<List<DictionaryModel>> allWords;

    public DictionaryRepository(Application application) {
        DictionaryDatabase database = DictionaryDatabase.getInstance(application);
        dao = database.dao();
        allWords = dao.getAllWords();
    }

    public LiveData<List<DictionaryModel>> getAllWords() {
        return allWords;
    }

    public LiveData<List<DictionaryModel>> searchWords(String query) {
        return dao.searchWords("%" + query + "%");
    }
}
