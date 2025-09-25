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

public class SignUp extends AppCompatActivity {

    TextInputEditText name, username, password1, confirmpassword;
    TextInputLayout nameInput, usernameInput, password1Input, confirmpasswordInput;
    Button signup;
    TextView login1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        password1 = findViewById(R.id.password1);
        confirmpassword = findViewById(R.id.confirmpassword);

        nameInput = findViewById(R.id.nameInput);
        usernameInput = findViewById(R.id.usernameInput);
        password1Input = findViewById(R.id.password1Input);
        confirmpasswordInput = findViewById(R.id.confirmpasswordInput);

        signup = findViewById(R.id.signup);
        login1 = findViewById(R.id.login1);

        signup.setOnClickListener(v -> {
            String strName = name.getText().toString().trim();
            String strUser = username.getText().toString().trim();
            String strPass = password1.getText().toString().trim();
            String strConfirm = confirmpassword.getText().toString().trim();

            if (strName.isEmpty() || strUser.isEmpty() || strPass.isEmpty() || strConfirm.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (!strPass.equals(strConfirm)) {
                confirmpasswordInput.setError("Passwords do not match");
            } else {
                DatabaseHelper db = new DatabaseHelper(this);

                if (db.checkUsername(strUser)) {
                    Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
                } else {
                    boolean inserted = db.insertUser(strName, strUser, strPass);
                    if (inserted) {
                        // Save name to SharedPreferences
                        SharedPreferences prefs = getSharedPreferences("FlavorDuoPrefs", MODE_PRIVATE);
                        prefs.edit().putString("username", strName).apply();

                        Toast.makeText(this, "Signup successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUp.this, SignupLoginPage.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Signup failed. Try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login1.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, SignupLoginPage.class);
            startActivity(intent);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
