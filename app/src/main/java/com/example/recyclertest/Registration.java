package com.example.recyclertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registration extends AppCompatActivity {
    public EditText institution_names, name_eidt, mobilr_number, password, confrim_password;
    public Spinner spinner_java;
    public Button btm;
    public String spiner_string;
    public int aa;
    public String name, possword, institution_name, urlid;
    public int active_sataes, mobile;
    public String catagoryid;
    List<String> categories = new ArrayList<String>();
    public String IMEINumber;
    private static final int REQUEST_CODE = 101;
    public TextView c;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        spinner_java = (Spinner) findViewById(R.id.spinner);
        institution_names = (EditText) findViewById(R.id.shop_name);

        c = (TextView) findViewById(R.id.cat);

        name_eidt = (EditText) findViewById(R.id.name);
        mobilr_number = (EditText) findViewById(R.id.mobil_no);
        password = (EditText) findViewById(R.id.password);
        confrim_password = (EditText) findViewById(R.id.confrim_password);
        btm = (Button) findViewById(R.id.button6);

        Registration_add();
        loadIMEI();

        btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                c.setText(""+IMEINumber);

                if(IMEINumber==null){
                    Toast.makeText(getApplicationContext(), "Permission denied."+IMEINumber, Toast.LENGTH_SHORT).show();
                }

                if (institution_names.getText().toString().isEmpty() || name_eidt.getText().toString().isEmpty() || mobilr_number.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), " empty", Toast.LENGTH_SHORT).show();
                } else {
                    String passwrd = password.getText().toString();
                    if (confrim_password.getText().toString().equals(passwrd)) {
                        Toast.makeText(getApplicationContext(), " match", Toast.LENGTH_SHORT).show();

                        //`name`,`mobile`,`possword`,`catagory`,`date`,`active_sataes`,`institution_name`

                        name = name_eidt.getText().toString();
                        mobile = Integer.parseInt(mobilr_number.getText().toString());
                        possword = password.getText().toString();
                        active_sataes = 0;
                        institution_name = institution_names.getText().toString();


                        reg_website();
                    } else {
                        confrim_password.setError("Confirm password not match");
                    }
                }
            }
        });

        spinner_java.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spiner_string = (String) parent.getItemAtPosition(position);

                if (position == 0) {
                    Toast.makeText(getApplicationContext(), "Selected Item:", Toast.LENGTH_SHORT).show();
                } else {
                    Login();
                }

                Toast.makeText(getApplicationContext(), "Selected Item: " + spiner_string, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        confrim_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aa++;
                if (aa == 1) {
                    confrim_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    Toast.makeText(getApplicationContext(), "hide: ", Toast.LENGTH_SHORT).show();
                } else {
                    //drawableRight
                    confrim_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    Toast.makeText(getApplicationContext(), "show: ", Toast.LENGTH_SHORT).show();
                    aa = 0;
                }
            }
        });


        confrim_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String passwrd = password.getText().toString();
                if (editable.length() > passwrd.length()) {
                    if (!confrim_password.equals(passwrd)) {
                        confrim_password.setError("Confirm password not match");
                    } else {

                    }
                }
            }
        });

        categories.add("Selecte");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_java.setAdapter(dataAdapter);

    }



    public void Registration_add() {

        StringRequest request = new StringRequest(Request.Method.GET, urlist.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONArray Jarray = new JSONArray(response);
                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject Jasonobject = Jarray.getJSONObject(i);
                        catagoryid = Jasonobject.getString("catagory_name");
                        categories.add(catagoryid);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        }

        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Registration.this);
        requestQueue.add(request);
    }

    public void Login() {
        StringRequest requests = new StringRequest(Request.Method.POST, urlist.urlr, new Response.Listener<String>() {
            @Override
            public void onResponse(String responses) {

                urlid = responses;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", spiner_string);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Registration.this);
        requestQueue.add(requests);

    }

    public void reg_website() {
        StringRequest requests = new StringRequest(Request.Method.POST, urlist.reg_website, new Response.Listener<String>() {
            @Override
            public void onResponse(String responses) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        ) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("mobile", String.valueOf(mobile));
                params.put("possword", possword);
                params.put("catagory", urlid);
                params.put("active_sataes", String.valueOf(active_sataes));
                params.put("institution_name", institution_name);
                params.put("imei", IMEINumber);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Registration.this);
        requestQueue.add(requests);

    }
    private void loadIMEI() {

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(Registration.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Registration.this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
            return;
        }
        IMEINumber = telephonyManager.getDeviceId();
        Log.e("asssa",IMEINumber);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}