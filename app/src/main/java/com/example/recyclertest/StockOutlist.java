package com.example.recyclertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StockOutlist extends AppCompatActivity {
    private SQLiteHelper mDatabase;
    private ProductListAdapter adapter;
    public RecyclerView  recyclerViews;
    public TextView select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_outlist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" Stock Out List ");
        mDatabase = new SQLiteHelper(this);
        recyclerViews=(RecyclerView) findViewById(R.id.recye_stock_out);
        select=(TextView)findViewById(R.id.select);

        ArrayList<Product> words = mDatabase.get_all_stoct_out_list();

        if(words==null){
            Log.e("ddddddddddddddddddddddddddddddd", String.valueOf(words));
        }
        recyclerViews.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductListAdapter(getApplicationContext(),this, words);
        recyclerViews.setAdapter(adapter);
        select_all();
    }


    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = new Intent(getApplicationContext(), home_page.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        return true;
    }

    public void select_all(){

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"All select",Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void select(){

    }
}