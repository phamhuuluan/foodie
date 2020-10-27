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
import com.t3h.foodie.model.Product;

import java.text.DecimalFormat;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodHolder>{
    private LayoutInflater inflater;
    private List<Product> data;

    public FoodAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setData(List<Product> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = inflater.inflate(R.layout.item_food, parent, false);
      return new FoodHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHolder holder, int position) {
          holder.bindView(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class FoodHolder extends RecyclerView.ViewHolder{
        private ImageView imFood;
        private TextView tvFoodName, tvFoodPrice, tvFoodAddress;

        public FoodHolder(@NonNull View itemView) {
            super(itemView);
            imFood= itemView.findViewById(R.id.im_food);
            tvFoodName = itemView.findViewById(R.id.tv_food_name);
            tvFoodPrice = itemView.findViewById(R.id.tv_food_price);
            tvFoodAddress = itemView.findViewById(R.id.tv_food_address);
        }
        private void bindView(Product product){
            Picasso.get().load(product.getImage()).placeholder(R.drawable.file).error(R.drawable.bg_do_an).into(imFood);
            tvFoodName.setText(product.getName());
            tvFoodName.setMaxLines(1);
            tvFoodName.setEllipsize(TextUtils.TruncateAt.END);
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            tvFoodPrice.setText(decimalFormat.format(product.getPrice()) + " Đ");
            tvFoodAddress.setText(product.getAddress());
        }
    }
}
