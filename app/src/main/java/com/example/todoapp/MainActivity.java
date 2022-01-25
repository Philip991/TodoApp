package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    EditText edTxt;
    Button btn;
    ArrayList<String>arrayList;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView) findViewById(R.id.lstVw);
        edTxt=(EditText) findViewById(R.id.inputItem);
        btn =(Button) findViewById(R.id.addBtnItem);

        arrayList= new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                arrayList);
        listView.setAdapter(adapter);
        onBtnClick();

    }

    public void onBtnClick(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = edTxt.getText().toString();
                arrayList.add(result);
                adapter.notifyDataSetChanged();

            }
        });
    }


}