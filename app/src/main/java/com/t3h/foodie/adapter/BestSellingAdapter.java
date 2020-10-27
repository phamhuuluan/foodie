package com.t3h.foodie.adapter;

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

public class BestSellingAdapter extends RecyclerView.Adapter<BestSellingAdapter.BestSellingHolder>{
    private LayoutInflater inflater;
    private List<Product> data;

    public BestSellingAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setData(List<Product> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BestSellingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_best_selling, parent, false);
        return new BestSellingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BestSellingHolder holder, int position) {
        holder.bindView(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class BestSellingHolder extends RecyclerView.ViewHolder{
        private ImageView imBestSell;
        private TextView tvBestSellName, tvBestSellPrice, tvBestSellAddress;
        public BestSellingHolder(@NonNull View itemView) {
            super(itemView);
            imBestSell= itemView.findViewById(R.id.im_best_sell);
            tvBestSellName = itemView.findViewById(R.id.tv_best_sell_name);
            tvBestSellPrice = itemView.findViewById(R.id.tv_best_sell_price);
            tvBestSellAddress = itemView.findViewById(R.id.tv_best_sell_address);
        }
        private void bindView(Product product){
            Picasso.get().load(product.getImage()).placeholder(R.drawable.file).error(R.drawable.bg_do_an).into(imBestSell);
            tvBestSellName.setText(product.getName());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            tvBestSellPrice.setText(decimalFormat.format(product.getPrice()) + " đ");
            tvBestSellAddress.setText(product.getAddress());
        }
    }
}
