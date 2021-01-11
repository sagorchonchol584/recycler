package com.example.recyclertest;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;


public class GetcontractRecycler  extends RecyclerView.Adapter<GetcontractRecycler.ViewHolder>{
    public static List<Getcontractcontr> mDataset;
    private LayoutInflater mInflater;
    public ArrayList<Getcontractcontr> os_version;
    private  Context context;
    public GetcontractRecycler(Getcontract getcontract, ArrayList<Getcontractcontr> myDataset) {
        this.mInflater=LayoutInflater.from(getcontract);
        this.mDataset=myDataset;

    }

    @Override
    public GetcontractRecycler.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.getcontract,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder( GetcontractRecycler.ViewHolder holder, int position) {

        int randomAndroidColor = holder.androidColors[new Random().nextInt(holder.androidColors.length)];
        Drawable background = holder.circles.getBackground();
        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable)background).getPaint().setColor(randomAndroidColor);
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable)background).setColor(randomAndroidColor);
        } else if (background instanceof ColorDrawable) {
            ((ColorDrawable)background).setColor(randomAndroidColor);
        }

        String pp=mDataset.get(position).getDisplayName();
        holder.circles.setText(""+pp.substring(0, 1).toUpperCase());
        holder.txtHeader.setText(""+pp);
        holder.txtFooter.setText(""+mDataset.get(position).getPhoneNumber());

        holder.constraintLayouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, finalRecycer.class);
                intent.putExtra(urlist.display,mDataset.get(position).getDisplayName());
                intent.putExtra(urlist.displayNumber,mDataset.get(position).getPhoneNumber());
                context.startActivity(intent);
                Log.e("ddd", String.valueOf(mDataset.get(position).getPhoneNumber()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public TextView txtFooter;
        public TextView circles;
        public ConstraintLayout constraintLayouts;


        int[] androidColors;
        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            androidColors = itemView.getResources().getIntArray(R.array.androidcolors);
            txtHeader = (TextView) itemView.findViewById(R.id.firstLine);
            txtFooter = (TextView) itemView.findViewById(R.id.secondLine);
            circles = (TextView) itemView.findViewById(R.id.circle);
            constraintLayouts = (ConstraintLayout) itemView.findViewById(R.id.contrack);

        }
    }


    public void setFilter(ArrayList<Getcontractcontr> arrayList) {
        os_version = new ArrayList<>();
        os_version.addAll(arrayList);
        notifyDataSetChanged();
    }
}
