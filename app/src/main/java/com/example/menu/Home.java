package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    RecyclerView recyclerView;
    DBSqlite myDB;
    ArrayList<String> _id,nomRest,nomCat,descreption,adresse,telephone,option,image,menu_img,reduction,category,speciality;
    CustomAdapter customAdapter;
    //int images[] = {R.drawable.coffee01,R.drawable.coffee02,R.drawable.coffee03};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.recyclerView);
        myDB = new DBSqlite(Home.this );
        _id = new ArrayList<>();
        nomRest = new ArrayList<>();
        nomCat = new ArrayList<>();
        descreption = new ArrayList<>();
        adresse = new ArrayList<>();
        telephone = new ArrayList<>();
        option = new ArrayList<>();
        image = new ArrayList<>();
        menu_img = new ArrayList<>();
        reduction = new ArrayList<>();
        category = new ArrayList<>();
        speciality = new ArrayList<>();


        StoreData();
        customAdapter = new CustomAdapter(Home.this,_id,nomRest,image, nomCat,reduction);

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Home.this));
    }

//To store the data in array
    void StoreData(){
        Cursor cursor = myDB.readData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                _id.add(cursor.getString(0));
                nomRest.add(cursor.getString(1));
                nomCat.add(cursor.getString(2));
                descreption.add(cursor.getString(3));
                adresse.add(cursor.getString(4));
                telephone.add(cursor.getString(5));
                option.add(cursor.getString(6));
                image.add(cursor.getString(7));
                menu_img.add(cursor.getString(8));
                reduction.add(cursor.getString(9));
                category.add(cursor.getString(10));
                speciality.add(cursor.getString(11));
            }
        }
    }
}