package com.example.pemata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ProfilActivity extends AppCompatActivity {

    private EditText editNama, editEmail;
    private Button logoutButton, btnSimpan;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editEmail = findViewById(R.id.edit_email);
        logoutButton = findViewById(R.id.btn_logout);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();

        if (user != null) {
            editEmail.setText(user.getEmail());

            // Ambil nama dari Firestore
            DocumentReference docRef = db.collection("users").document(user.getUid());
            docRef.get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    String nama = documentSnapshot.getString("nama");
                    editNama.setText(nama);
                }
            }).addOnFailureListener(e -> {
                Toast.makeText(this, "Gagal memuat profil", Toast.LENGTH_SHORT).show();
            });
        }

        // Simpan nama yang diubah ke Firestore
        btnSimpan.setOnClickListener(v -> {
            String namaBaru = editNama.getText().toString().trim();
            if (!namaBaru.isEmpty() && user != null) {
                Map<String, Object> update = new HashMap<>();
                update.put("nama", namaBaru);

                db.collection("users").document(user.getUid())
                        .update(update)
                        .addOnSuccessListener(aVoid ->
                                Toast.makeText(this, "Nama berhasil diperbarui", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e ->
                                Toast.makeText(this, "Gagal memperbarui nama", Toast.LENGTH_SHORT).show());
            }
        });

        // Logout
        logoutButton.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent(ProfilActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        // Navigasi Bottom
        findViewById(R.id.nav_soil).setOnClickListener(v -> startActivity(new Intent(this, Penyiraman.class)));
        findViewById(R.id.nav_calendar).setOnClickListener(v -> startActivity(new Intent(this, JadwalActivity.class)));
    }
}
