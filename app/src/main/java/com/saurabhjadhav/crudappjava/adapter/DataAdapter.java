package com.saurabhjadhav.crudappjava.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.saurabhjadhav.crudappjava.R;
import com.saurabhjadhav.crudappjava.UpdateTaskActivity;
import com.saurabhjadhav.crudappjava.room.SimpleModel;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private Context mCtx;
    private List<SimpleModel> dataList;

    public DataAdapter(Context mCtx, List<SimpleModel> dataList) {
        this.mCtx = mCtx;
        this.dataList = dataList;
    }

    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.data_item, parent, false);
        Log.e("checkme", "onCreateViewHolder: ");
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        Log.d("checkme", "onBindViewHolder() position: " + position);

        Log.e("checkme", "onBindViewHolder: " + dataList.get(position).getImgUrl());
        holder.name.setText(dataList.get(position).getName());
        holder.email.setText(dataList.get(position).getEmail());
        Glide.with(mCtx).load(dataList.get(position).getImgUrl()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        Log.e("checkme", "getItemCount: " + dataList.size());

        return dataList.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView email;
        ImageView img;

        public DataViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textView);
            email = itemView.findViewById(R.id.tv_email);
            img = itemView.findViewById(R.id.imageView);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            SimpleModel data = dataList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdateTaskActivity.class);
            intent.putExtra("data", data);

            mCtx.startActivity(intent);
        }
    }
}
