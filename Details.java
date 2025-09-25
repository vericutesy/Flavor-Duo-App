package com.example.flavorduoapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flavorduoapp.Domain.Cart;
import com.example.flavorduoapp.Domain.CartItem;
import com.example.flavorduoapp.R;

public class Details extends AppCompatActivity {

    private ImageView picmain, star;
    private TextView titlebutton, qtyText, minusbutton, plusbutton, quantity, rate, descriptiontext, addtocartbutton, pricing;

    private int itemQuantity = 1;
    private double itemPrice = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Initialize views
        picmain = findViewById(R.id.picmain);
        star = findViewById(R.id.star);
        titlebutton = findViewById(R.id.titlebutton);
        qtyText = findViewById(R.id.qty_text);
        minusbutton = findViewById(R.id.minusbutton);
        plusbutton = findViewById(R.id.plusbutton);
        quantity = findViewById(R.id.quantity);
        rate = findViewById(R.id.rate);
        descriptiontext = findViewById(R.id.descriptiontext);
        addtocartbutton = findViewById(R.id.addtocartbutton);
        pricing = findViewById(R.id.pricing);

        // 🔹 Get data from intent
        String title = getIntent().getStringExtra("title");
        String price = getIntent().getStringExtra("price");
        String description = getIntent().getStringExtra("description");
        String rateValue = getIntent().getStringExtra("rate");
        int imageRes = getIntent().getIntExtra("image", 0);

        // 🔹 Set data to views
        titlebutton.setText(title);
        descriptiontext.setText(description != null ? description : "No description available.");
        rate.setText(rateValue != null ? rateValue : "0");
        picmain.setImageResource(imageRes);

        // 🔹 Convert price to double
        try {
            if (price != null) {
                String cleanPrice = price.replace("₱", "").replace(".00", "").trim();
                itemPrice = Double.parseDouble(cleanPrice);
            } else {
                itemPrice = 25.00; // fallback
            }
        } catch (NumberFormatException e) {
            itemPrice = 25.00;
        }

        updatePrice();

        // 🔹 Plus button click
        plusbutton.setOnClickListener(v -> {
            itemQuantity++;
            quantity.setText(String.valueOf(itemQuantity));
            updatePrice();
        });

        // 🔹 Minus button click
        minusbutton.setOnClickListener(v -> {
            if (itemQuantity > 1) {
                itemQuantity--;
                quantity.setText(String.valueOf(itemQuantity));
                updatePrice();
            }
        });

        // 🔹 Add to cart click
        addtocartbutton.setOnClickListener(v -> {
            Cart.getInstance().addItem(new CartItem(title, itemPrice, imageRes, itemQuantity));
            Toast.makeText(Details.this,
                    "Added " + itemQuantity + " × " + title + " to cart",
                    Toast.LENGTH_SHORT).show();
        });
    }

    private void updatePrice() {
        double total = itemQuantity * itemPrice;
        pricing.setText("₱" + total);
    }
}
