package com.example.deshnajain.drsystemapp;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
TextView mTextViewSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mTextViewSearch=findViewById(R.id.textViewSearch);
        if(Intent.ACTION_SEARCH.equals(getIntent().getAction())){
            handleSearch(getIntent().getStringExtra(SearchManager.QUERY));
        }
    }

    private void handleSearch(String searchQuery) {
        mTextViewSearch.setText(searchQuery);
    }
}
