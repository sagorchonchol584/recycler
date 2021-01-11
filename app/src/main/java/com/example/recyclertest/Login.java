package com.example.recyclertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    public TextView new_account;
    public EditText user_name,loign_possword;
    public Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new_account=(TextView)findViewById(R.id.account_cre);
        user_name=(EditText)findViewById(R.id.user_login);
        loign_possword=(EditText)findViewById(R.id.user_poss);
        btn=(Button)findViewById(R.id.login_btm);





        new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), Registration.class);
                startActivityForResult(myIntent, 0);
            }
        });


     //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
//    public boolean onOptionsItemSelected(MenuItem item){
//        Intent myIntent = new Intent(getApplicationContext(), Registration.class);
//        startActivityForResult(myIntent, 0);
//        return true;
//    }
}