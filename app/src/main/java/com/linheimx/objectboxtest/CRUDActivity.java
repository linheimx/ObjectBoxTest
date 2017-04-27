package com.linheimx.objectboxtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CRUDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        getSupportActionBar().setTitle("增删改查");
    }
}
