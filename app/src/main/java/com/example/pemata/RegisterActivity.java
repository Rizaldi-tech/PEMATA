package com.example.pemata;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;


public class RegisterActivity extends AppCompatActivity {

    private EditText inputUsername, inputPassword;
    private Button buttonDaftar;
    private TextView textMasuk;
    private FirebaseAuth mAuth; // Tambahkan ini
    private FirebaseFirestore db;
    private EditText inputNama;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();

        // Inisialisasi komponen UI
        inputNama = findViewById(R.id.inputNama);
        inputUsername = findViewById(R.id.inputUsername); // Gunakan ini sebagai EMAIL
        inputPassword = findViewById(R.id.inputPassword);
        buttonDaftar = findViewById(R.id.buttonDaftar);
        textMasuk = findViewById(R.id.textMasuk);

        // Tombol Daftar
        buttonDaftar.setOnClickListener(v -> {
            String nama = inputNama.getText().toString().trim();
            String email = inputUsername.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();

            if (nama.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Isi semua data terlebih dahulu!", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                String userId = mAuth.getCurrentUser().getUid();

                                // Simpan ke Firestore
                                Map<String, Object> userMap = new HashMap<>();
                                userMap.put("nama", nama);
                                userMap.put("email", email);

                                db.collection("Users").document(userId)
                                        .set(userMap)
                                        .addOnSuccessListener(aVoid -> {
                                            Toast.makeText(RegisterActivity.this, "Berhasil daftar!", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                            finish();
                                        })
                                        .addOnFailureListener(e -> {
                                            Toast.makeText(RegisterActivity.this, "Gagal simpan data user", Toast.LENGTH_SHORT).show();
                                        });

                            } else {
                                String message = "Gagal daftar!";
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    message = "Email sudah terdaftar.";
                                } else if (task.getException() != null) {
                                    message = task.getException().getMessage();
                                }
                                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // Teks "Masuk"
        textMasuk.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }
}

