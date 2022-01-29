package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Completed extends AppCompatActivity {

    TextView textView;
    ImageView backIcon;
    TextView toolTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);


        textView=(TextView) findViewById(R.id.completed_data);
        Intent intent = getIntent();

        String str= intent.getStringExtra("message_key");
        textView.setText(str);
        onBackIconClick();

        toolTitle.setText("Completed list");


    }
    public void onBackIconClick(){
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Completed.this, MainActivity.class));

            }
        });
    }
}