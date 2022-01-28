package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ListItem extends AppCompatActivity {

    TextView toolBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        getSupportActionBar().hide();

        toolBarTitle=(TextView)findViewById(R.id.toolbar_title);

        toolBarTitle.setText("Completed");
    }
}