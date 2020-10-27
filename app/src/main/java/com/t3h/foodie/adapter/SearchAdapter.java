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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {
    private LayoutInflater inflater;
    private List<Product> data;

    public SearchAdapter(LayoutInflater inflater) {
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
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_search, parent, false);
        return new SearchHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, int position) {
        holder.bindView(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class SearchHolder extends RecyclerView.ViewHolder{
        private ImageView imSearchFood;
        private TextView tvSearchName, tvSearchPrice, tvSearchAddress;

        public SearchHolder(@NonNull View itemView) {
            super(itemView);
            imSearchFood = itemView.findViewById(R.id.im_search_food);
            tvSearchName = itemView.findViewById(R.id.tv_search_name);
            tvSearchPrice = itemView.findViewById(R.id.tv_search_price);
            tvSearchAddress = itemView.findViewById(R.id.tv_search_address);
        }
        private void bindView(Product product){
            Picasso.get().load(product.getImage()).placeholder(R.drawable.file).error(R.drawable.bg_do_an).into(imSearchFood);
            tvSearchName.setText(product.getName());
            tvSearchName.setMaxLines(1);
            tvSearchName.setEllipsize(TextUtils.TruncateAt.END);
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            tvSearchPrice.setText(decimalFormat.format(product.getPrice()) + " Đ");
            tvSearchAddress.setText(product.getAddress());
        }
    }
}
