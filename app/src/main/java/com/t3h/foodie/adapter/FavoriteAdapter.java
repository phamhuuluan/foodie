package com.t3h.foodie.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.t3h.foodie.R;
import com.t3h.foodie.model.Category;
import com.t3h.foodie.model.Product;

import java.text.DecimalFormat;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>{
    private LayoutInflater inflater;
    private List<Product> data;

    public FavoriteAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_favorite, parent , false);
        return new FavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHolder holder, int position) {
        final Product product = data.get(position);
        holder.bindView(product);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class FavoriteHolder extends RecyclerView.ViewHolder{
        private ImageView imFavoriteFood;
        private TextView tvFavoriteName, tvFavoritePrice, tvFavoriteAddress;

        public FavoriteHolder(@NonNull View itemView) {
            super(itemView);
            imFavoriteFood = itemView.findViewById(R.id.im_favorite_food);
            tvFavoriteName = itemView.findViewById(R.id.tv_favorite_name);
            tvFavoritePrice = itemView.findViewById(R.id.tv_favorite_price);
            tvFavoriteAddress = itemView.findViewById(R.id.tv_favorite_address);
        }
        private void bindView(Product product){
            Picasso.get().load(product.getImage()).placeholder(R.drawable.file).error(R.drawable.bg_do_an).into(imFavoriteFood);
            tvFavoriteName.setText(product.getName());
            tvFavoriteName.setMaxLines(1);
            tvFavoriteName.setEllipsize(TextUtils.TruncateAt.END);
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            tvFavoritePrice.setText(decimalFormat.format(product.getPrice()) + " Đ");
            tvFavoriteAddress.setText(product.getAddress());
        }
    }
}
