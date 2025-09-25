package com.example.flavorduoapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flavorduoapp.Activity.Details;
import com.example.flavorduoapp.Domain.Cart;
import com.example.flavorduoapp.Domain.CartItem;
import com.example.flavorduoapp.Domain.DrinkProduct;
import com.example.flavorduoapp.R;

import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> {

    private final Context context;
    private final List<DrinkProduct> drinkList;

    public DrinkAdapter(Context context, List<DrinkProduct> drinkList) {
        this.context = context;
        this.drinkList = drinkList;
    }

    @NonNull
    @Override
    public DrinkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.drinkproducts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkAdapter.ViewHolder holder, int position) {
        DrinkProduct drink = drinkList.get(position);

        holder.title.setText(drink.getTitle());
        holder.subtitle.setText(drink.getDescription());
        holder.price.setText(drink.getPrice());
        holder.image.setImageResource(drink.getImageResId());

        // Add to Cart (with 4-parameter CartItem)
        if (holder.addButton != null) {
            holder.addButton.setOnClickListener(v -> {
                double priceValue = Double.parseDouble(
                        drink.getPrice().replace("₱", "").replace(".00", "")
                );
                Cart.getInstance().addItem(new CartItem(
                        drink.getTitle(), priceValue, drink.getImageResId(), 1
                ));
                Toast.makeText(context, drink.getTitle() + " added to cart", Toast.LENGTH_SHORT).show();
            });
        }

        // Open Details
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Details.class);
            intent.putExtra("title", drink.getTitle());
            intent.putExtra("price", drink.getPrice());
            intent.putExtra("description", drink.getDescription());
            intent.putExtra("image", drink.getImageResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, subtitle, price;
        ImageView addButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.pic3);
            title = itemView.findViewById(R.id.title3);
            subtitle = itemView.findViewById(R.id.subtitle3);
            price = itemView.findViewById(R.id.price3);

            int addId = itemView.getResources().getIdentifier("addtocart3", "id", itemView.getContext().getPackageName());
            if (addId != 0) {
                View maybe = itemView.findViewById(addId);
                if (maybe instanceof ImageView) addButton = (ImageView) maybe;
                else addButton = null;
            } else addButton = null;
        }
    }
}
