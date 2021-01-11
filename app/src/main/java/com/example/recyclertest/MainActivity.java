package com.example.recyclertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public Button button;
    public static TextView textView;
    MyRecyclerViewAdapter adapter;
    public AutoCompleteTextView autoCompleteTextView;
    ArrayList<EditModel> animalNames = new ArrayList<>();
    List<SqliteContracter> categories = new ArrayList<SqliteContracter>();
    public SqliteContracter sqliteContracter;
    private SQLiteHelper mDatabase;
    public String product_name_with_genaric, product_name,product_strength;
    public int product_id;
    public int o;
    public static int u = 0;
    public int unites,less_stock;
    public Double rate;
    public String product_id_for_sqlite,total_stocks,rat;
    public EditModel editModel;
    public ProductListAdapter productListAdapter;
    private Product selectedPerson;
    public ArrayList<Product> products;
    public ArrayList<String> search;
    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqliteContracter = new SqliteContracter();
        mDatabase = new SQLiteHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycel);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        search=mDatabase.listContacts();
        final ArrayList<Product> words = mDatabase.getAllCampsites();
        ArrayList<SqliteContracter> word = mDatabase.getAll();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, search);

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autotext);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowId) {
              //  Product product = (Product) parent.getItemAtPosition(position);
             //    selectedPerson =(Product) adapterView.getItemAtPosition(position);
                product_name_with_genaric = (String) adapterView.getItemAtPosition(position);
                autoCompleteTextView.setText("");



                Cursor res=mDatabase.arrayListstring(product_name_with_genaric);
                StringBuffer stringBuffer=new StringBuffer();
                if(res!=null && res.getCount()>0){
                    while ((res.moveToNext())){
                        stringBuffer.append(res.getString(2));
                    }
                    product_id_for_sqlite=stringBuffer.toString();
                    Log.e("product_id_for_sqlite", product_id_for_sqlite);

                    Cursor stock =mDatabase.stock_list(product_id_for_sqlite);
                    if(stock!=null && stock.getCount()>0){
                        while ((stock.moveToNext())){
                            total_stocks=stock.getString(2);
                                     rat=stock.getString(3);
                        }

                        less_stock=Integer.parseInt(total_stocks);
                        Log.e("stock", less_stock+" rate: "+rat);

                    }

                    Cursor all_data_show =mDatabase.all_data(product_id_for_sqlite);
                    if(all_data_show!=null && all_data_show.getCount()>0){
                        while ((all_data_show.moveToNext())){
                            product_name=all_data_show.getString(2);
                            product_strength=all_data_show.getString(4);
                        }

                        Log.e("product_name", product_name+" genaric: "+product_strength);
                    }
                }
                dailog_box();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (animalNames.isEmpty()) {
                    autoCompleteTextView.setError("Enter Type");
                    dailog_box_test();
                } else {
                    Intent intent = new Intent(MainActivity.this, finalRecycer.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void add_info(String product_id, String product_name, Double rates, int units, int previous, String strength) {
        editModel = new EditModel();
        editModel.setProduct_id(product_id);
        editModel.setNamedx(product_name);
        editModel.setNet(rates);
        editModel.setUnits(units);
        editModel.setPrevious_stock(previous);
        editModel.setStrength(strength);
        editModel.setTotal(rates * units);
        animalNames.add(editModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(getApplicationContext(),this, animalNames);
        recyclerView.setAdapter(adapter);
        Collections.reverse(animalNames);
          Log.e("after", String.valueOf(product_name));

    }


    public void dailog_box() {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_log, null);
        final EditText editText = (EditText) dialogView.findViewById(R.id.edt_comment);
        final EditText rateeditText = (EditText) dialogView.findViewById(R.id.rate);
        final TextView product_name_text = (TextView) dialogView.findViewById(R.id.names);
        final TextView stockid_text = (TextView) dialogView.findViewById(R.id.stockid);
        Button button2 = (Button) dialogView.findViewById(R.id.buttonSubmit);
        Button button3 = (Button) dialogView.findViewById(R.id.expire);
        Button button1 = (Button) dialogView.findViewById(R.id.buttonCancel);
        product_name_text.setText(product_name);
        rateeditText.setText(""+rat);
        stockid_text.setText(total_stocks +" Unit ");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().trim().equalsIgnoreCase("")) {
                    editText.setError("Enter Type");
                } else if (rateeditText.getText().toString().trim().equalsIgnoreCase("")) {
                    rateeditText.setError("Enter Rate");
                } else {
                    unites = Integer.parseInt(editText.getText().toString());
                     rate = Double.valueOf(rateeditText.getText().toString());
                    if(less_stock<=0){
                        Toast.makeText(getApplicationContext(), " You haven't enough stock ", Toast.LENGTH_LONG).show();
                    }else {
                        int less= less_stock-unites;
                        if(unites<=less_stock){
                            add_info(product_id_for_sqlite, product_name, rate, unites, less, product_strength);



//                            boolean update_stoct = mDatabase.insert_pruchess("6","6","6","7","5","5","8");
//                            if (update_stoct == true) {
//                                Toast.makeText(getApplicationContext(), " Your Update data not save ", Toast.LENGTH_LONG).show();
//                            } else {
//
//                                Toast.makeText(getApplicationContext(), " Product add Successful ", Toast.LENGTH_LONG).show();
//                            }




                        }else {
                            Toast.makeText(getApplicationContext(), "Stock out over", Toast.LENGTH_LONG).show();
                        }
                    }

//
                    dialogBuilder.dismiss();
                }
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO SOMETHINGS
                Toast.makeText(getApplicationContext()," Cancel ",Toast.LENGTH_LONG).show();
                dialogBuilder.dismiss();
            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Toast.makeText(getApplicationContext(),"Date Expre Funtion coming soon",Toast.LENGTH_LONG).show();
                // DO SOMETHINGS
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    public void dailog_box_test() {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.errordailog, null);
        final TextView textViews = (TextView) dialogView.findViewById(R.id.worrr);
        textViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }



    private void autoCompleteTextViewProduct_onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Product product = (Product) adapterView.getItemAtPosition(position);
        product_name_with_genaric = product.getProduct_name();
        product_id = position;
        Toast.makeText(getApplicationContext(), "Product id : " + product.getProduct_id(), Toast.LENGTH_SHORT).show();
      //  dailog_box();

        autoCompleteTextView.setText("");

    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = new Intent(getApplicationContext(), home_page.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        return true;
    }
}