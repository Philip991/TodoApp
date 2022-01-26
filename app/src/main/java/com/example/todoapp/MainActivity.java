package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        itemReader();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                arrayList);
        listView.setAdapter(adapter);
        onBtnClick();
        onListClick();

        listViewListener();



    }

    public void onBtnClick(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = edTxt.getText().toString();
                arrayList.add(result);
                adapter.notifyDataSetChanged();
                itemWriter();

            }
        });
    }

    public void onListClick(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this,ListItem.class));
            }
        });

    }
    private void listViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                adapter.notifyDataSetChanged();
                itemWriter();

                return true;
            }
        });
    }

        public void itemReader() {
            File filesDir = getFilesDir() ;
            File todoFile = new File(filesDir, "todo.txt");
            try {
                arrayList = new ArrayList<String>(FileUtils.readLines(todoFile));
            }catch (IOException e){
                    arrayList=new ArrayList<String>();
                }
            };
    public void itemWriter(){
        File filesDir= getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try{
            FileUtils.writeLines(todoFile, arrayList);
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}
