package com.example.vegeshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IngredientViewAdapter extends RecyclerView.Adapter<IngredientViewAdapter.IngredientViewHolder> {
    ArrayList<Ingredient> ingredients;

    public IngredientViewAdapter(Product product) {
        this.ingredients = product.Ingredients;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient currentIngredient = ingredients.get(position);

        holder.ingredientName.setText(currentIngredient.Name);
        if(currentIngredient.Traits.size() > 0) {
            holder.ingredientTrait.setText(currentIngredient.Traits.get(0));
        }
        else
            holder.ingredientTrait.setText("");

        //holder.textNative.setText(currentResult.GetNativeText());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        public TextView ingredientName;
        public TextView ingredientTrait;
        //public RecyclerView ingredientTraits;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.txtIngredientName);
            ingredientTrait = itemView.findViewById(R.id.txtIngredientTrait);
        }
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_listitem, parent, false);
        return new IngredientViewHolder(v);
    }

}
