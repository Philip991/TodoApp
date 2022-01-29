package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    EditText edTxt;
    ImageView addIcon;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lstVw);
        edTxt = (EditText) findViewById(R.id.inputItem);
        addIcon = (ImageView) findViewById(R.id.addBtnIcon);


        arrayList = new ArrayList<String>();
        itemReader();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
                arrayList);
        listView.setAdapter(adapter);
        onBtnClick();



    }

    public void onBtnClick() {
        addIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = edTxt.getText().toString();
                arrayList.add(result);
                adapter.notifyDataSetChanged();
                itemWriter();
                edTxt.setText("");

            }
        });
    }


    public void itemReader() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            arrayList = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            arrayList = new ArrayList<String>();
        }
    }

    ;

    public void itemWriter() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, arrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        itemWriter();
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_done) {
            String itemSelected = "Items Completed: \n";
            for (int i = 0; i < listView.getCount(); i++) {
                if (listView.isItemChecked(i)) {
                    itemSelected += listView.getItemAtPosition(i);
                    arrayList.remove(i);
                    adapter.notifyDataSetChanged();
                    itemWriter();
                }

            }
            Toast.makeText(this, itemSelected, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);


    }
}

