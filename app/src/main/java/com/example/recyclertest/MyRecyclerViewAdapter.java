package com.example.recyclertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>  {
    public static List<EditModel> mData;
    private LayoutInflater mInflater;
    public static int a,b,c;
    public int add;
    public int units, previous_stock;
    public boolean rowchange;
    private Context context;
    private SQLiteHelper mDatabase;
    public String product_id_for_sqlite;
    public Double rate;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public MyRecyclerViewAdapter(Context context, MainActivity mainActivity, ArrayList<EditModel> animalNames) {
        this.mInflater=LayoutInflater.from(mainActivity);
        this.context = context;
        this.mData=animalNames;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.re,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

       holder.textView.setText(""+mData.get(position).getStrength());
       holder.textViewxd.setText(""+mData.get(position).getNamedx());
       holder.editText_price.setText(""+mData.get(position).getUnits());
       holder.editText_net.setText(""+mData.get(position).getNet());
       holder.editText_price.getText().toString();

       holder.textView_total.setText(df2.format(mData.get(position).getNet()*mData.get(position).getUnits()));

        rate=+mData.get(position).getNet();
        product_id_for_sqlite=mData.get(position).getProduct_id();
        previous_stock=mData.get(position).getPrevious_stock();
        units=mData.get(position).getUnits();


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView, textViewxd,textView_total,stock_id;
        public EditText editText_price, editText_net;
        public ImageView imageView;
        public ConstraintLayout constraintLayout;


        public ViewHolder( View itemView) {
            super(itemView);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.constra);
            mDatabase = new SQLiteHelper(context);
            textView = (TextView) itemView.findViewById(R.id.remil);
            textViewxd = (TextView) itemView.findViewById(R.id.retdx);
            textView_total = (TextView) itemView.findViewById(R.id.total);
            editText_price = (EditText) itemView.findViewById(R.id.pice);
            editText_net = (EditText) itemView.findViewById(R.id.net);
            imageView = (ImageView) itemView.findViewById(R.id.delete);

            imageView.setOnClickListener(this);
            editText_price.setOnClickListener(this);
            constraintLayout.setOnClickListener(this);
            editText_price.setFocusableInTouchMode(false);
            editText_net.setFocusableInTouchMode(false);


        }


        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.delete:

                    int less= previous_stock+units;
                 //   Toast.makeText(context, " Stock valuee "+previous_stock, Toast.LENGTH_LONG).show();

                    boolean update_stoct = mDatabase.stock_table_update(String.valueOf(product_id_for_sqlite), String.valueOf(less),rate);

                    if (update_stoct == true) {
                     Toast.makeText(context, "Delete not Successful ", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, " Delete Successful", Toast.LENGTH_LONG).show();
                    }
                    mData.remove(getAdapterPosition());
                    notifyDataSetChanged();
                    break;
                case R.id.pice:


                    break;
                case R.id.constra:



                    break;
            }
        }
    }

}
