package com.example.flavorduoapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flavorduoapp.Adapter.CartAdapter;
import com.example.flavorduoapp.Domain.Cart;
import com.example.flavorduoapp.R;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private TextView totalPrice;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        totalPrice = findViewById(R.id.totalprice);

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setHasFixedSize(true);

        // Initialize adapter with OnCartChangeListener
        adapter = new CartAdapter(this, Cart.getInstance().getItems(), new CartAdapter.OnCartChangeListener() {
            @Override
            public void onCartChanged() {
                updateTotalPrice();
            }
        });

        cartRecyclerView.setAdapter(adapter);

        updateTotalPrice();

        // 🛒 Checkout button
        findViewById(R.id.button).setOnClickListener(v -> {
            if (Cart.getInstance().getItems().isEmpty()) {
                Toast.makeText(CartActivity.this, "Your cart is empty", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CartActivity.this, "Purchase successful!", Toast.LENGTH_SHORT).show();
                Cart.getInstance().getItems().clear();  // Clear cart after checkout
                adapter.notifyDataSetChanged();
                updateTotalPrice();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        double total = Cart.getInstance().getTotalPrice();
        totalPrice.setText("₱" + total);
    }
}
