package com.example.recyclertest;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class home_page extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private SQLiteHelper mDatabase;
    NavigationView navigationView;
    DrawerLayout drawer;
    public Intent intent;
    MenuItem menuItem;
    TextView badgeCounter;
    int pendingNotifications =5;

    public TextView notification;
    public TextView textView;
    public int counter;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDatabase = new SQLiteHelper(this);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.sells,R.id.daily, R.id.out_of_stock, R.id.stock_add, R.id.sub_user_create, R.id.logout, R.id.Share, R.id.RateUs).setDrawerLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        setUpNavigationView();




        counter=18;
        LayoutInflater inflater=LayoutInflater.from(home_page.this);
        notification=(TextView)inflater.inflate(R.layout.counter_stock, null);
        navigationView.getMenu().findItem(R.id.out_of_stock).setActionView(notification);
        navication(counter);





    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                switch (menuItem.getItemId()) {

                    case R.id.sells:
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        drawer.closeDrawers();

                        break;
                        case R.id.daily:
                          intent = new Intent(getApplicationContext(), DailyReport.class);
                          intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                          startActivity(intent);
                          drawer.closeDrawers();

                        break;
                        case R.id.out_of_stock:


                            intent = new Intent(getApplicationContext(), StockOutlist.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);
                            drawer.closeDrawers();

                        break;
                        case R.id.stock_add:
                            intent = new Intent(getApplicationContext(), StockAdd.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);
                            drawer.closeDrawers();

                        break;
                        case R.id.sub_user_create:
                            intent = new Intent(getApplicationContext(), subusercreate.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);
                            drawer.closeDrawers();

                        break;
                        case R.id.logout:

                        Toast.makeText(getApplicationContext(),"Logout",Toast.LENGTH_LONG).show();

                            break;
                        case R.id.Share:

                            Toast.makeText(getApplicationContext(),"Share",Toast.LENGTH_LONG).show();
                            break;

                        case R.id.RateUs:
                            Toast.makeText(getApplicationContext(),"RateUs",Toast.LENGTH_LONG).show();

                            return true;
                    default:
                }
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        //getMenuInflater().inflate(R.menu.stock_upload, menu);



        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.synchronizeid:
                get_url_data();
                Toast.makeText(getApplicationContext(), "Product Synchronize done.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    public void get_url_data() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.GET, urlist.Data_url_upload, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray Jarray = new JSONArray(response);
                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject Jasonobject = Jarray.getJSONObject(i);

                        String product_id = Jasonobject.getString("id");
                        String brand_name=Jasonobject.getString("brand_name");
                        String product_name=Jasonobject.getString("product_name");
                        String generic=Jasonobject.getString("generic");
                        String strength=Jasonobject.getString("strength");
                        String form_eg_table=Jasonobject.getString("form_eg_table");
                        String catalogs =Jasonobject.getString("catalogs");

                        Log.i("Test internet ------->", brand_name);

                        boolean result= mDatabase.checkid(product_id);
                        if(result==true){
                            progressDialog.hide();
                        }
                        else {

                            String user_id="10";
                            String date="10.12.20";

                            boolean results=mDatabase.insertRecord(brand_name,product_name,generic,strength,form_eg_table,product_id,catalogs);

                            String search= product_name+" "+strength;

                            boolean test=mDatabase.search_sqlite(search,product_id);
                            boolean stock=  mDatabase.stock_table(product_id,"0","0",user_id,date);
                            if(results==true || test==true||stock==true){
                                progressDialog.hide();
                            }
                            else {
                                progressDialog.show();
                            }
                            progressDialog.hide();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        }


        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(home_page.this);
        requestQueue.add(request);
    }

    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you wanted to Exit?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent =new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        System.exit(0);
                        Toast.makeText(getApplicationContext(),"You clicked yes button",Toast.LENGTH_LONG).show();

                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
      //  super.onBackPressed();

    }

    public void navication(int valur){

        if(counter>0){
           notification.setText(counter+"");
        }else {
            notification.setText("");

        }

    }
}