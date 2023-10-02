package com.example.log3;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    ArrayList<String> contact_id, contact_name, contact_email, contact_dob, contact_image;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        recyclerView=findViewById(R.id.recyclerView);
        myDB= new MyDatabaseHelper(DetailsActivity.this);
        contact_id= new ArrayList<>();
        contact_name= new ArrayList<>();
        contact_dob= new ArrayList<>();
        contact_email= new ArrayList<>();
        contact_image= new ArrayList<>();

        storeDataInArrays();
        customAdapter = new CustomAdapter(DetailsActivity.this,contact_id,contact_name,contact_dob,contact_email,contact_image);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customAdapter);

    }
    void storeDataInArrays(){
        Cursor cursor=myDB.readDatabase();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
        else {
            while(cursor.moveToNext()){
                contact_id.add(cursor.getString(0));
                contact_name.add(cursor.getString(1));
                contact_dob.add(cursor.getString(2));
                contact_email.add(cursor.getString(3));
                contact_image.add(cursor.getString(4));
            }
        }

    }
}