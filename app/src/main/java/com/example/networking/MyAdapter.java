package com.example.networking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<Mountains> Mountains;
    public MyAdapter(List<Mountains> mountains) {
        this.Mountains = mountains;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.name.setText(Mountains.get(position).getName());
            holder.size.setText(String.valueOf(Mountains.get(position).getSize()));
    }

    @Override
    public int getItemCount() {
        return Mountains.size();
    }
}