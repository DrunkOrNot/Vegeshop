package com.example.vegeshop;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class IngredientViewAdapter extends RecyclerView.Adapter<IngredientViewAdapter.IngredientViewHolder> {
    ArrayList<Ingredient> ingredients;
    static private final HashMap<String, String> ingredientColorHTML = new HashMap<String, String>() {{
        put("Meat", "#fd2437");
        put("Diary", "#c4cb5c");
        put("Gluten", "#faa148");
        put("Honey", "#c4cb5c");
        put("Fish", "#fd2437");
        put("Seafood", "#c4cb5c");
        put("Lactose", "#faa148");
    }};

    public IngredientViewAdapter(Product product) {
        this.ingredients = product.Ingredients;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient currentIngredient = ingredients.get(position);

        holder.ingredientName.setText(currentIngredient.Name);
        holder.ingredientTrait.setText("");
        if(currentIngredient.Traits.size() > 0) {
            for(String trait : currentIngredient.Traits) {
                if(ingredientColorHTML.containsKey(trait)) {
                    String traitHTML = "<font color='" + ingredientColorHTML.get(trait) + "'>" + trait + "\n</font><br>";
                    holder.ingredientTrait.append(Html.fromHtml(traitHTML, 1));
                }

            }
        }
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
