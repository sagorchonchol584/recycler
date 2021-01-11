package com.example.recyclertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Getcontract extends AppCompatActivity {
    public RecyclerView mRecyclerView;
    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    public GetcontractRecycler dataAdapter;
    public Getcontractcontr getcontractcontr;
    public ArrayList<Getcontractcontr> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getcontract);
        requestContactPermission();



        SearchView searchView = (SearchView)findViewById(R.id.menu_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                final ArrayList<Getcontractcontr> filteredModelList = filter(list, query);
                dataAdapter.setFilter(filteredModelList);

                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                final ArrayList<Getcontractcontr> filteredModelList = filter(list, newText);
                dataAdapter.setFilter(filteredModelList);
                return true;
            }

            private ArrayList<Getcontractcontr> filter(ArrayList<Getcontractcontr> models, String search_txt) {

                search_txt = search_txt.toLowerCase();
                final ArrayList<Getcontractcontr> filteredModelList = new ArrayList<>();
                GetcontractRecycler  dataAdapters = new GetcontractRecycler(Getcontract.this, filteredModelList);
                for (Getcontractcontr model : models) {

                    final String text = model.getDisplayName().toLowerCase();



                    if (text.contains(search_txt)) {
                        filteredModelList.add(model);
                    }
                }

                mRecyclerView.setAdapter(dataAdapters);
                return filteredModelList;
            }

        });



    }








    private static final String[] PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
    };

    private void getContacts(){
        list=new ArrayList<Getcontractcontr>();
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PROJECTION, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        if (cursor != null) {
            HashSet<String> mobileNoSet = new HashSet<String>();
            try {
                final int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                final int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                String name, number;
                while (cursor.moveToNext()) {
                    name = cursor.getString(nameIndex);
                    number = cursor.getString(numberIndex);
                    number = number.replace(" ", "");

                    getcontractcontr = new Getcontractcontr();
                    getcontractcontr.setPhoneNumber(number);
                    getcontractcontr.setDisplayName(name);
                    list.add(getcontractcontr);

                }
            } finally {
                cursor.close();


                mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                dataAdapter = new GetcontractRecycler(Getcontract.this, list);
                mRecyclerView.setAdapter(dataAdapter);
            }



        }


    }

    public void requestContactPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_CONTACTS)) {
                    requestPermissions(
                            new String[]
                                    {android.Manifest.permission.READ_CONTACTS}
                            , PERMISSIONS_REQUEST_READ_CONTACTS);

                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{android.Manifest.permission.READ_CONTACTS},
                            PERMISSIONS_REQUEST_READ_CONTACTS);
                }
            } else {
                getContacts();
            }
        } else {
            getContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContacts();
                } else {
                    Toast.makeText(this, "You have disabled a contacts permission", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

}
