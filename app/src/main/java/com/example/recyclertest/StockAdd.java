package com.example.recyclertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StockAdd extends AppCompatActivity {

    public RecyclerView recyclerView;
    public Button button;
    public static TextView textView;
    StockAddAdapter adapter;
    public AutoCompleteTextView autoCompleteTextView;
    ArrayList<EditModel> animalNames = new ArrayList<>();
    List<SqliteContracter> categories = new ArrayList<SqliteContracter>();
    public SqliteContracter sqliteContracter;
    private SQLiteHelper mDatabase;
    public String product_name_with_genaric, product_name,product_strength;
    public int product_id;
    public int o;
    public static int u = 0;
    public int unites,less_stock,  less;
    public Double rate;
    public String product_id_for_sqlite,total_stocks,rat;
    public EditModel editModel;
    public ProductListAdapter productListAdapter;
    private Product selectedPerson;
    public ArrayList<Product> products;
    public ArrayList<String> search;
    boolean update_stoct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_add);

        sqliteContracter = new SqliteContracter();
        mDatabase = new SQLiteHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.stoclrecler);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Stock Add");

        search=mDatabase.listContacts();
        final ArrayList<Product> words = mDatabase.getAllCampsites();
        ArrayList<SqliteContracter> word = mDatabase.getAll();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, search);
        //   ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, restaurants);
        // autoCompleteTextView.setAdapter(adapter);
        //  autoCompleteTextView.setAdapter(productListAdapter);
        //  ProductListAdapter productListAdapter = new ProductListAdapter(getApplicationContext(),R.layout.activity_main, R.id.productname, products);


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


//
//        animalNames= populateList();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new MyRecyclerViewAdapter(this, animalNames);
//        recyclerView.setAdapter(adapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Log.i("SELECTED TEXT WAS------->", String.valueOf(MyRecyclerViewAdapter.mData.size()));
                if (animalNames.isEmpty()) {
                    autoCompleteTextView.setError("Enter Type");
                    dailog_box_test();
                } else {

                    total_value();

//                    Intent intent = new Intent(getApplicationContext(), finalRecycer.class);
//                    startActivity(intent);
                }
            }
        });

    }
    public void total_value() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you wanted to Stock Upload ?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        for (int i = 0; i < StockAddAdapter.mData.size(); i++) {
                            update_stoct = mDatabase.stock_table_update(String.valueOf(StockAddAdapter.mData.get(i).getProduct_id()), String.valueOf(StockAddAdapter.mData.get(i).getPrevious_stock()),Double.valueOf(StockAddAdapter.mData.get(i).getNet()));
                        }
                        if (update_stoct == true) {
                            Toast.makeText(getApplicationContext(), " Your Update data not save ", Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent(getApplicationContext(), home_page.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), " Product add Successful ", Toast.LENGTH_LONG).show();
                        }

                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();





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
        adapter = new StockAddAdapter(getApplicationContext(),this, animalNames);
        recyclerView.setAdapter(adapter);
        Collections.reverse(animalNames);
        //  Log.e("after", String.valueOf(u));

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
                    less= less_stock+unites;

                    add_info(product_id_for_sqlite, product_name, rate, unites, less, product_strength);
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