package com.example.flavorduoapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flavorduoapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupLoginPage extends AppCompatActivity {

    TextInputEditText username, password;
    TextInputLayout usernameLayout, passwordLayout;
    Button login;
    TextView signup1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_login_page);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        usernameLayout = findViewById(R.id.usernameLayout);
        passwordLayout = findViewById(R.id.passwordLayout);

        login = findViewById(R.id.login);
        signup1 = findViewById(R.id.signup1);

        login.setOnClickListener(v -> {
            String strUser = username.getText().toString().trim();
            String strPass = password.getText().toString().trim();

            if (strUser.isEmpty() || strPass.isEmpty()) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            } else {
                DatabaseHelper db = new DatabaseHelper(this);

                if (db.checkUser(strUser, strPass)) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();

                    // ✅ Get user's name from database
                    String nameFromDB = db.getNameByUsername(strUser);

                    // ✅ Save username and name in SharedPreferences
                    SharedPreferences prefs = getSharedPreferences("FlavorDuoPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("username", strUser);
                    editor.putString("name", nameFromDB); // store real name
                    editor.apply();

                    // Redirect to MainActivity2
                    Intent intent = new Intent(SignupLoginPage.this, MainActivity2.class);
                    startActivity(intent);
                    finish();
                } else if (db.checkUsername(strUser)) {
                    Toast.makeText(this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No account found — please sign up first.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signup1.setOnClickListener(v -> {
            Intent intent = new Intent(SignupLoginPage.this, SignUp.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main3), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
