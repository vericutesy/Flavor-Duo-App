package com.example.flavorduoapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flavorduoapp.Domain.ComboProduct;
import com.example.flavorduoapp.Adapter.ComboProductAdapter;
import com.example.flavorduoapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private TextView greetingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        // Handle system bars for EdgeToEdge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main4), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 🔹 Get greeting TextView
        greetingText = findViewById(R.id.greetingText);

        // 🔹 Load user's real name from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("FlavorDuoPrefs", MODE_PRIVATE);
        String userName = prefs.getString("name", "User"); // fallback = "User"
        greetingText.setText("Hello " + userName + ", Choose Your");

        // ✅ Cupcakes and Drinks categories (clickable)
        LinearLayout cupcakeCategory = findViewById(R.id.cupcakeCategory);
        LinearLayout drinksCategory = findViewById(R.id.drinksCategory);

        cupcakeCategory.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, Cupcakes.class);
            startActivity(intent);
        });

        drinksCategory.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, Drinks.class);
            startActivity(intent);
        });

        ImageView cartIcon = findViewById(R.id.cartIcon);
        cartIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, CartActivity.class);
            startActivity(intent);
        });

        // ✅ Flavor Duo Combo products RecyclerView
        RecyclerView comboRecyclerView = findViewById(R.id.comboRecyclerView);
        comboRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        comboRecyclerView.setHasFixedSize(true);

        // Initialize combo products list
        List<ComboProduct> comboList = new ArrayList<>();
        comboList.add(new ComboProduct("His & Hers", "2 cupcakes & 2 drinks (Mango & Pineapple)", "₱140.00", R.drawable.combo1));
        comboList.add(new ComboProduct("Love Triangle Treats", "3 cupcakes (Apple, Mango, Chocolate)", "₱80.00", R.drawable.combo2));
        comboList.add(new ComboProduct("Banana Tralala", "3 cupcakes (Banana, Raisins, Pineapple)", "₱120.00", R.drawable.combo3));
        comboList.add(new ComboProduct("Thirstrap", "3 Juices (Iced Tea, Softdrink, Apple Juice)", "₱95.00", R.drawable.combo4));
        comboList.add(new ComboProduct("Fivesome", "5 cupcakes + 1 free softdrink", "₱160.00", R.drawable.combo5));
        comboList.add(new ComboProduct("Diabetes Overload", "All 6 cupcakes", "₱205.00", R.drawable.combo6));

        // Set adapter
        ComboProductAdapter comboAdapter = new ComboProductAdapter(this, comboList);
        comboRecyclerView.setAdapter(comboAdapter);

        // ✅ Profile icon -> show logout popup
        ImageView profileIcon = findViewById(R.id.profileIcon);
        profileIcon.setOnClickListener(v -> {
            View popupView = LayoutInflater.from(MainActivity2.this).inflate(R.layout.logout, null);

            final PopupWindow popupWindow = new PopupWindow(
                    popupView,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    true
            );

            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);
            popupWindow.showAsDropDown(profileIcon, 0, 10);

            TextView logoutText = popupView.findViewById(R.id.logoutText);
            logoutText.setOnClickListener(view -> {
                popupWindow.dismiss();
                Toast.makeText(MainActivity2.this, "Logged out", Toast.LENGTH_SHORT).show();
                // Clear saved username and name
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.apply();
                // Redirect to login
                Intent intent = new Intent(MainActivity2.this, SignupLoginPage.class);
                startActivity(intent);
                finish();
            });
        });
    }
}
