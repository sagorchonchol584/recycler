package com.example.recyclertest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import java.text.SimpleDateFormat;
import java.util.Date;


public class finalRecycer extends AppCompatActivity {
    public static int total_sell, items, previous_stock, piece;
    public String strDate;
    public Double Net_value,total_value;
    public TextView textView,unitstextview,buttonview;
    public EditText nameeditext, numbereditext, amountedittext;
    public String namedisplay,numberstring;
    public CheckBox cb1,cb2;
    public ImageView imageView;
    public ConstraintLayout constraintLayout;
    public Bundle bundle;
    public Intent intent;
    public SQLiteHelper mDatabase;
    public String priduct_id, pharmacy_id, net_value, username, indenty_value;
    public boolean update_stoct,paidoption,purchase_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_recycer);
        textView = (TextView) findViewById(R.id.textView16);
        imageView = (ImageView) findViewById(R.id.contart);
        unitstextview = (TextView) findViewById(R.id.unti);
        constraintLayout = (ConstraintLayout) findViewById(R.id.unpaidlayout);
        cb1=(CheckBox)findViewById(R.id.checkBox);
        cb2=(CheckBox)findViewById(R.id.checkB);
        mDatabase = new SQLiteHelper(this);

        buttonview=(TextView)findViewById(R.id.textView30);
        nameeditext=(EditText)findViewById(R.id.names);
        numbereditext=(EditText)findViewById(R.id.mobile);
        amountedittext=(EditText)findViewById(R.id.amount);

        total_value_and_date();
        textView.setText(String.valueOf(total_sell)+" TK");
        unitstextview.setText(String.valueOf(items)+" Item");

        buttonview.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int Moblies=numbereditext.getText().length();
                if (nameeditext.getText().toString().isEmpty() && numbereditext.getText().toString().isEmpty() && amountedittext.getText().toString().isEmpty()) {
                    nameeditext.setError("Enter Type Name");
                    numbereditext.setError("Enter Type Mobile");
                    amountedittext.setError("Enter Type Amount");

                }else if(11>=Moblies && 11<=Moblies) {
                    String name_get=nameeditext.getText().toString();
                    String mobile_get=numbereditext.getText().toString();
                    int amount_get=Integer.parseInt(amountedittext.getText().toString());
                    int debte=total_sell-amount_get;
               if(total_sell>=amount_get){
                   purchess_funcation();
                   paidoption = mDatabase.paid_and_unpaid( name_get, mobile_get, indenty_value, total_sell, amount_get, debte, strDate);
                   if (update_stoct == true && paidoption==true) {
                       Intent i =  new Intent(getApplicationContext(),MainActivity.class);
                       startActivity(i);
                       Toast.makeText(getApplicationContext(), " Your data not save ", Toast.LENGTH_LONG).show();
                   } else {
                       Toast.makeText(getApplicationContext(), " Data Successful ", Toast.LENGTH_LONG).show();
                   }
                   Intent i =  new Intent(getApplicationContext(),MainActivity.class);
                   startActivity(i);
                 }else {

                   View parentLayout = findViewById(android.R.id.content);
                   Snackbar.make(parentLayout, "Payment amount is over", Snackbar.LENGTH_LONG).setAction("CLOSE", new View.OnClickListener() {
                               @Override
                               public void onClick(View view) {
                                   constraintLayout.setVisibility(View.INVISIBLE);
                                   cb1.setChecked(false);
                                   nameeditext.getText().clear();
                                   numbereditext.getText().clear();
                                   amountedittext.getText().clear();
                               }
                           })
                           .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                           .show();
               }
                }else {
                    numbereditext.setError("Mobile number is invalid");
                }
            }
        });

        intent = getIntent();
        bundle = intent.getExtras();
        if(bundle != null){
            cb1.setChecked(true);
            constraintLayout.setVisibility(View.VISIBLE);
            namedisplay = getIntent().getExtras().getString(urlist.display);
            String numberdataa  = getIntent().getExtras().getString(urlist.displayNumber);
            nameeditext.setText(String.valueOf(namedisplay));
            int s= numberdataa.length();
            if(11>=s){
                numberstring=numberdataa;
                numbereditext.setText(numberstring);
            }else {
                String input = numberdataa;
                String d=input.split("-")[0];
                String b=d.substring(3,8);
                String c=input.split("-")[1];
                numberstring=b+c;
                numbereditext.setText(numberstring);
            }
        }else {
            constraintLayout.setVisibility(View.INVISIBLE);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(getApplicationContext(),Getcontract.class);
                startActivity(i);
            }
        });







        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    constraintLayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    constraintLayout.setVisibility(View.INVISIBLE);
                }
            }
        });





        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    open();
                    cb1.setChecked(false);
                    nameeditext.getText().clear();
                    numbereditext.getText().clear();
                }
                else
                {
                }
            }
        });
    }

    public void total_value_and_date() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        strDate = formatter.format(date);


        int total = 0;
        for (int i = 0; i < MyRecyclerViewAdapter.mData.size(); i++) {
            total += MyRecyclerViewAdapter.mData.get(i).getTotal();
            Net_value=MyRecyclerViewAdapter.mData.get(i).getNet();
            piece=MyRecyclerViewAdapter.mData.get(i).getUnits();
            priduct_id=MyRecyclerViewAdapter.mData.get(i).getProduct_id();

            pharmacy_id="5";
            username="8";
            indenty_value="3";
        }
        total_sell = total;
        items=MyRecyclerViewAdapter.mData.size();
    }





public void purchess_funcation(){
    for (int i = 0; i < MyRecyclerViewAdapter.mData.size(); i++) {
        Net_value=MyRecyclerViewAdapter.mData.get(i).getNet();
        piece=MyRecyclerViewAdapter.mData.get(i).getUnits();
        priduct_id=MyRecyclerViewAdapter.mData.get(i).getProduct_id();
        total_value=MyRecyclerViewAdapter.mData.get(i).getTotal();
        previous_stock=MyRecyclerViewAdapter.mData.get(i).getPrevious_stock();
        pharmacy_id="5";
        username="8";
        indenty_value="3";

        purchase_insert = mDatabase.insert_pruchess(priduct_id, pharmacy_id, Net_value, username, piece, total_value, indenty_value,strDate);
        update_stoct= mDatabase.stock_table_update(String.valueOf(priduct_id), String.valueOf(previous_stock), MyRecyclerViewAdapter.mData.get(i).getNet());

        if(previous_stock==0){
            stock_out_data(priduct_id);
            Toast.makeText(getApplicationContext(), " Notification ", Toast.LENGTH_LONG).show();
        }else {


        }
    }
}
    public void stock_out_data( String id){
        String brand_name, prouct_name, ganaric, strength,table;
        String prarmarcy_id="5";
        Cursor res=mDatabase.stock_out_data(id);
        StringBuffer stringBuffer=new StringBuffer();
        if(res!=null && res.getCount()>0){
            while ((res.moveToNext())){
                ganaric=res.getString(3);
                table=res.getString(5);
                brand_name=res.getString(1);
                prouct_name=res.getString(2);
                strength=res.getString(4);
                Log.e("stock Out",prouct_name);

                //  mDatabase.stoct_out_list(prarmarcy_id, brand_name, prouct_name, ganaric, strength,table, strDate);
                boolean  paidoption = mDatabase.stoct_out_list(prarmarcy_id, brand_name, prouct_name, ganaric, strength,table, strDate);
                if (paidoption==true) {
                    Toast.makeText(getApplicationContext(), " Your Update data not save ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), " Product add Successful ", Toast.LENGTH_LONG).show();
                }
                //  mDatabase.stoct_out_list(prarmarcy_id, brand_name, prouct_name, ganaric, strength,table, strDate);
            }


       }
    }

    public void open(){
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.payment_dailog, null);

        final TextView dialog_text = (TextView) dialogView.findViewById(R.id.dialog_info);
        final TextView dialog_cancel = (TextView) dialogView.findViewById(R.id.dialog_cancel);
        final TextView dialog_ok = (TextView) dialogView.findViewById(R.id.dialog_ok);
        dialog_text.setText("Do you want to Pay " + ""+total_sell+" Tk ?");
        dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                purchess_funcation();
                String name_get="Unknow";
                String mobile_get="N/A";
                int debte=0;
                paidoption = mDatabase.paid_and_unpaid( name_get, mobile_get, indenty_value, total_sell, total_sell, debte, strDate);
                if (update_stoct == true && paidoption==true) {
                    Intent i =  new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), " Your Update data not save ", Toast.LENGTH_LONG).show();
                } else {
                    Intent i =  new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), " Product add Successful ", Toast.LENGTH_LONG).show();
                }
                dialogBuilder.dismiss();
            }
        });


        dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cb2.setChecked(false);
                Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_LONG).show();
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();

    }
}