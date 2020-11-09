package com.t3h.foodie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.foodie.R;
import com.t3h.foodie.model.Product;

import java.util.List;

public class ShoppingcartAdapter extends RecyclerView.Adapter<ShoppingcartAdapter.ShoppingcarHolder> {
    private List<Product> data;
    private LayoutInflater inflater;

    public ShoppingcartAdapter(List<Product> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setData(List<Product> data) {
        this.data = data;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ShoppingcarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_productshopping,parent,false);
        return new ShoppingcarHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingcarHolder holder, int position) {
        holder.binView(data.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ShoppingcarHolder extends RecyclerView.ViewHolder {
        public ShoppingcarHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void binView(Product product) {
        }
    }
}
