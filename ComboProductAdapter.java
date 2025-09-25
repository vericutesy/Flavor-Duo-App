package com.example.flavorduoapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flavorduoapp.Activity.Details;
import com.example.flavorduoapp.Domain.Cart;
import com.example.flavorduoapp.Domain.CartItem;
import com.example.flavorduoapp.Domain.ComboProduct;
import com.example.flavorduoapp.R;

import java.util.List;

public class ComboProductAdapter extends RecyclerView.Adapter<ComboProductAdapter.ViewHolder> {

    private final Context context;
    private final List<ComboProduct> comboList;

    public ComboProductAdapter(Context context, List<ComboProduct> comboList) {
        this.context = context;
        this.comboList = comboList;
    }

    @NonNull
    @Override
    public ComboProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comboproducts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComboProductAdapter.ViewHolder holder, int position) {
        ComboProduct combo = comboList.get(position);

        holder.title.setText(combo.getTitle());
        holder.subtitle.setText(combo.getSubtitle());
        holder.price.setText(combo.getPrice());
        holder.image.setImageResource(combo.getImageResId());

        // Item click -> open Details
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Details.class);
            intent.putExtra("title", combo.getTitle());
            intent.putExtra("price", combo.getPrice());
            intent.putExtra("description", combo.getSubtitle());
            intent.putExtra("image", combo.getImageResId());
            context.startActivity(intent);
        });

        // Add to Cart button (inside product layout)
        if (holder.addButton != null) {
            holder.addButton.setOnClickListener(v -> {
                double priceValue = Double.parseDouble(combo.getPrice().replace("₱", "").replace(".00", ""));

                // Add item to cart with quantity = 1
                Cart.getInstance().addItem(new CartItem(combo.getTitle(), priceValue, combo.getImageResId(), 1));

                Toast.makeText(context, combo.getTitle() + " added to cart", Toast.LENGTH_SHORT).show();
            });
        }
    }

    @Override
    public int getItemCount() {
        return comboList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, subtitle, price;
        ImageView image;
        ImageView addButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titlee);
            subtitle = itemView.findViewById(R.id.subtitle);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.pic);

            // Add to Cart icon
            int addId = itemView.getResources().getIdentifier("addtocart1", "id", itemView.getContext().getPackageName());
            if (addId != 0) {
                View maybe = itemView.findViewById(addId);
                if (maybe instanceof ImageView) addButton = (ImageView) maybe;
                else addButton = null;
            } else addButton = null;
        }
    }
}
