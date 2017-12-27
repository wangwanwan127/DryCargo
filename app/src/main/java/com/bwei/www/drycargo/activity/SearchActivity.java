package com.bwei.www.drycargo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwei.www.drycargo.R;


public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();
    }
}
