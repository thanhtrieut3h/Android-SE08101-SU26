package com.example.aistudymentor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aistudymentor.R;
import com.example.aistudymentor.models.CategoryModel;

import java.util.ArrayList;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryItemViewHolder> {
    public ArrayList<CategoryModel> categoryModels;
    public Context context;
    public CategoryListAdapter(ArrayList<CategoryModel> model, Context myContext){
        categoryModels = model;
        context = myContext;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false);
        return new CategoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position) {
        CategoryModel model = categoryModels.get(position);
        holder.tvName.setText(model.getName());
        holder.tvTime.setText(String.format("Created at : %s", model.getCreatedAt()));
    }

    @Override
    public int getItemCount() {
        return categoryModels.size(); // dem du lieu trong database
    }

    public static class CategoryItemViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvTime;
        View view;
        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tvName = view.findViewById(R.id.tvCategoryName);
            tvTime = view.findViewById(R.id.tvCategoryTime);
        }
    }
}
