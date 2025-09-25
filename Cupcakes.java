package com.example.flavorduoapp.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flavorduoapp.Adapter.CupcakeProductAdapter;
import com.example.flavorduoapp.Domain.CupcakeProduct;
import com.example.flavorduoapp.R;

import java.util.ArrayList;
import java.util.List;

public class Cupcakes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupcakes); // create this layout containing RecyclerView

        RecyclerView cupcakeRecyclerView = findViewById(R.id.cupcakeRecyclerView);
        cupcakeRecyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns

        List<CupcakeProduct> cupcakeList = new ArrayList<>();
        cupcakeList.add(new CupcakeProduct("Chocolate Cupcake", "Rich chocolate flavor", "₱25.00", R.drawable.chocolate));
        cupcakeList.add(new CupcakeProduct("Raisin Cupcake", "Sweet raisins inside", "₱50.00", R.drawable.raisin));
        cupcakeList.add(new CupcakeProduct("Pineapple Cupcake", "Sweet pineapple", "₱40.00", R.drawable.pineapple));
        cupcakeList.add(new CupcakeProduct("Apple Cupcake", "Apple heart topping", "₱30.00", R.drawable.apple));
        cupcakeList.add(new CupcakeProduct("Mango Cupcake", "Fresh mango topping", "₱35.00", R.drawable.mango));
        cupcakeList.add(new CupcakeProduct("Banana Cupcake", "Soft banana flavor", "₱45.00", R.drawable.banana));


        CupcakeProductAdapter adapter = new CupcakeProductAdapter(this, cupcakeList);
        cupcakeRecyclerView.setAdapter(adapter);
    }
}
