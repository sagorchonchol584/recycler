package com.example.recyclertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.recyclertest.R;

public class subusercreate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subusercreate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sub-User Create");
        final EditText sub_et_name = (EditText)findViewById(R.id.subname);
        final EditText sub_mobile = (EditText)findViewById(R.id.sub_mobil_no);
        final EditText sub_possword = (EditText)findViewById(R.id.sub_password);
        final EditText sub_con_possword =(EditText) findViewById(R.id.sub_confrim_password);
        final Button bt = (Button)findViewById(R.id.sub_button6);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = new Intent(getApplicationContext(), home_page.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        return true;
    }


}