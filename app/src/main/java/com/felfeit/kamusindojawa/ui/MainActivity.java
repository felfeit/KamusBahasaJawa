package com.felfeit.kamusindojawa.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.felfeit.kamusindojawa.R;
import com.felfeit.kamusindojawa.data.DictionaryRepository;
import com.felfeit.kamusindojawa.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DictionaryAdapter adapter;
    private DictionaryRepository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupEdgeToEdge();

        repository = new DictionaryRepository(getApplication());
        adapter = new DictionaryAdapter();

        binding.rvDictionary.setAdapter(adapter);
        binding.rvDictionary.setLayoutManager(new LinearLayoutManager(this));

        repository.getAllWords().observe(this, words -> adapter.submitList(words));

        binding.searchEditText.setOnEditorActionListener((v, actionId, evennt) -> {
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch();
                return true;
            }
            return false;
        });

        binding.nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY > 100) {
                binding.fabScrollToTop.show();
            } else {
                binding.fabScrollToTop.hide();
            }
        });

        binding.fabScrollToTop.setOnClickListener(v -> binding.nestedScrollView.smoothScrollTo(0, 0));
    }

    private void setupEdgeToEdge() {
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void performSearch() {
        String query = Objects.requireNonNull(binding.searchEditText.getText()).toString().trim();
        if (query.isEmpty()) {
            repository.getAllWords().observe(this, words -> adapter.submitList(words));
        } else {
            repository.searchWords(query).observe(this, words -> adapter.submitList(words));
        }

        // Hide keyboard after search
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.searchEditText.getWindowToken(), 0);
    }
}