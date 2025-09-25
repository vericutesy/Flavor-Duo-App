package com.example.flavorduoapp.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flavorduoapp.Adapter.DrinkAdapter;
import com.example.flavorduoapp.Domain.DrinkProduct;
import com.example.flavorduoapp.R;

import java.util.ArrayList;
import java.util.List;

public class Drinks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_drinks);

        // Make screen edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainDrinks), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // RecyclerView setup
        RecyclerView recyclerView = findViewById(R.id.drinkRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns
        recyclerView.setHasFixedSize(true);

        // Drinks list
        List<DrinkProduct> drinks = new ArrayList<>();
        drinks.add(new DrinkProduct("Softdrink", "₱25.00", "Refreshing fizzy drink", R.drawable.softdrink));
        drinks.add(new DrinkProduct("Iced Tea", "₱50.00", "Cool and sweet tea", R.drawable.icedtea));
        drinks.add(new DrinkProduct("Coffee", "₱40.00", "Hot brewed coffee", R.drawable.coffee));
        drinks.add(new DrinkProduct("Apple Juice", "₱30.00", "Fresh apple goodness", R.drawable.applejuice));
        drinks.add(new DrinkProduct("Mango Juice", "₱35.00", "Sweet tropical flavor", R.drawable.mangojuice));
        drinks.add(new DrinkProduct("Pineapple Juice", "₱45.00", "Zesty and refreshing", R.drawable.pineapplejuice));

        // Set adapter
        DrinkAdapter adapter = new DrinkAdapter(this, drinks);
        recyclerView.setAdapter(adapter);
    }
}
