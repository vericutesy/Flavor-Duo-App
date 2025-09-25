package com.example.flavorduoapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flavorduoapp.Domain.CartItem;
import com.example.flavorduoapp.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<CartItem> cartItems;
    private OnCartChangeListener listener;

    public CartAdapter(Context context, List<CartItem> cartItems, OnCartChangeListener listener) {
        this.context = context;
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);

        holder.name.setText(item.getTitle());
        holder.price.setText("₱" + item.getPrice());
        holder.qty.setText("x" + item.getQuantity());
        holder.image.setImageResource(item.getImageResId());

        // Delete button click
        holder.delete.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                cartItems.remove(pos);
                notifyItemRemoved(pos);
                notifyItemRangeChanged(pos, cartItems.size());

                // Notify listener to update total
                if (listener != null) {
                    listener.onCartChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price, qty;
        ImageView delete;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.cartItemImage);
            name = itemView.findViewById(R.id.cartItemName);
            price = itemView.findViewById(R.id.cartItemPrice);
            qty = itemView.findViewById(R.id.cartItemQty);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    // Interface to notify activity/fragment
    public interface OnCartChangeListener {
        void onCartChanged();
    }
}
