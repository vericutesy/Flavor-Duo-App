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
import com.example.flavorduoapp.Domain.CupcakeProduct;
import com.example.flavorduoapp.R;

import java.util.List;

public class CupcakeProductAdapter extends RecyclerView.Adapter<CupcakeProductAdapter.ViewHolder> {

    private final Context context;
    private final List<CupcakeProduct> cupcakeList;

    public CupcakeProductAdapter(Context context, List<CupcakeProduct> cupcakeList) {
        this.context = context;
        this.cupcakeList = cupcakeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cupcakeproducts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CupcakeProduct cupcake = cupcakeList.get(position);

        holder.title.setText(cupcake.getTitle());
        holder.subtitle.setText(cupcake.getSubtitle());
        holder.price.setText(cupcake.getPrice());
        holder.image.setImageResource(cupcake.getImageResId());

        // Add to Cart
        if (holder.addButton != null) {
            holder.addButton.setOnClickListener(v -> {
                double priceValue = Double.parseDouble(
                        cupcake.getPrice().replace("₱", "").replace(".00", "")
                );
                Cart.getInstance().addItem(new CartItem(
                        cupcake.getTitle(), priceValue, cupcake.getImageResId(), 1
                ));
                Toast.makeText(context, cupcake.getTitle() + " added to cart", Toast.LENGTH_SHORT).show();
            });
        }

        // Open Details on item click
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Details.class);
            intent.putExtra("title", cupcake.getTitle());
            intent.putExtra("price", cupcake.getPrice());
            intent.putExtra("description", cupcake.getSubtitle());
            intent.putExtra("image", cupcake.getImageResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return cupcakeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, subtitle, price;
        ImageView image;
        ImageView addButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title2);
            subtitle = itemView.findViewById(R.id.subtitle2);
            price = itemView.findViewById(R.id.price2);
            image = itemView.findViewById(R.id.pic2);

            int addId = itemView.getResources().getIdentifier("imageView7", "id", itemView.getContext().getPackageName());
            if (addId == 0) addId = itemView.getResources().getIdentifier("addtocart2", "id", itemView.getContext().getPackageName());
            if (addId == 0) addId = itemView.getResources().getIdentifier("addtocart", "id", itemView.getContext().getPackageName());
            if (addId == 0) addId = itemView.getResources().getIdentifier("image_add", "id", itemView.getContext().getPackageName());

            if (addId != 0) {
                View maybe = itemView.findViewById(addId);
                if (maybe instanceof ImageView) addButton = (ImageView) maybe;
                else addButton = null;
            } else addButton = null;
        }
    }
}
