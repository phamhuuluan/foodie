package com.t3h.foodie.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.t3h.foodie.Fragment.HomeFragment;
import com.t3h.foodie.R;
import com.t3h.foodie.model.Product;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder>{
    private LayoutInflater inflater;
    private List<Product> data;
    private OnClickViewListener listener;

    public void setListener(OnClickViewListener listener) {
        this.listener = listener;
    }

    public ProductAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setData(List<Product> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public List<Product> getData() {
        return data;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
         Product product = data.get(position);
        holder.bindView(data.get(position));
        if (listener == null){
            return;
        }
        holder.imAddShoppingCart.setOnClickListener(view -> listener.clickAddCart(product));
        holder.imFavorite.setOnClickListener(view -> listener.clickFavorite(product));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder{
        private ImageView imProduct;
        private TextView tvProductName, tvPrice, tvAddress;
        private ImageView imAddShoppingCart, imFavorite;
        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            imProduct= itemView.findViewById(R.id.im_product);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvAddress = itemView.findViewById(R.id.tv_address);
            imAddShoppingCart = itemView.findViewById(R.id.im_add_shopping_cart);
            imFavorite= itemView.findViewById(R.id.im_favorite);
        }

        private void bindView(Product product){
            Picasso.get().load(product.getImage()).placeholder(R.drawable.file).error(R.drawable.bg_do_an).into(imProduct);
            tvProductName.setText(product.getName());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            tvPrice.setText(decimalFormat.format(product.getPrice()) + " Đ");
            tvAddress.setText(product.getAddress());

            if (product.isSelected()){
                imFavorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                imFavorite.setEnabled(false);
            }

        }
    }

    public interface OnClickViewListener{
        void clickAddCart(Product product);
        void clickFavorite(Product product);
    }
}
