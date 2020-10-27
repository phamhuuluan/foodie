package com.t3h.foodie.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.t3h.foodie.R;
import com.t3h.foodie.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder>{
    private LayoutInflater inflater;
    private List<Category> data;
    private CategoryItemListener listener;

    public void setListener(CategoryItemListener listener) {
        this.listener = listener;
    }

    public CategoryAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setData(List<Category> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public List<Category> getData() {
        return data;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_category, parent, false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        final Category category = data.get(position);
        holder.bindView(category);

        if (listener !=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCategoryItemClicked(category);
                }
            });
        }
       }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder{
        private TextView tvCategoryName;
        private ImageView imIconCategory;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            tvCategoryName= itemView.findViewById(R.id.tv_category_name);
            imIconCategory = itemView.findViewById(R.id.im_icon_category);
        }


        public void bindView(Category category) {
            tvCategoryName.setText(category.getName());
            Picasso.get().load(category.getImage()).placeholder(R.drawable.file).error(R.drawable.picture).into(imIconCategory);

            if (category.isSelected()){
                tvCategoryName.setTextColor(Color.BLUE);
            }else {
                tvCategoryName.setTextColor(Color.BLACK);
            }
        }
    }

    public interface CategoryItemListener {
        void onCategoryItemClicked(Category category);
    }
}

