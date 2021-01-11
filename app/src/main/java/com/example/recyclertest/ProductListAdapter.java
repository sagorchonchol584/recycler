package com.example.recyclertest;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.recyclertest.MainActivity.textView;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private Context context;
    public static List<Product> mData;
    private SQLiteHelper mDatabase;

    public ProductListAdapter(Context context,StockOutlist stockOutlist, ArrayList<Product> words) {
        this.mInflater=LayoutInflater.from(stockOutlist);
        this.context = context;
        this.mData=words;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.arr,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        holder.textView.setText(""+mData.get(position).getProduct_name());
        holder.streges_textview.setText(""+mData.get(position).getStreges());
        holder.textView_ganaricname.setText(""+mData.get(position).getGenaric_name());
        holder.editText_tabletse.setText(""+mData.get(position).getTablet_option());
        holder.editText_company.setText(""+mData.get(position).getBrand_name());

    }

    @Override
    public int getItemCount() {
        return mData.size();}


    public class ViewHolder extends RecyclerView.ViewHolder{
            public TextView textView, streges_textview, textView_ganaricname, editText_tabletse,editText_company;
            public ImageView imageView;
            public ConstraintLayout constraintLayout;
            public CheckBox select;
            public ViewHolder(View itemView) {
            super(itemView);

            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.constranlay);
            mDatabase = new SQLiteHelper(context);
            textView = (TextView) itemView.findViewById(R.id.productname);
            streges_textview = (TextView) itemView.findViewById(R.id.streges);
            textView_ganaricname = (TextView) itemView.findViewById(R.id.ganaricname);
            editText_tabletse = (TextView) itemView.findViewById(R.id.tablets);
            editText_company = (TextView) itemView.findViewById(R.id.companyname);
            imageView = (ImageView) itemView.findViewById(R.id.imageView4);

            constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    Toast.makeText(context, "Long ", Toast.LENGTH_LONG).show();

                    return false;
                }
            });

              imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

                  Toast.makeText(context, "Delete not Successful ", Toast.LENGTH_LONG).show();
                   mData.remove(getAdapterPosition());
                   notifyDataSetChanged();
    }
});
            }
    }
}