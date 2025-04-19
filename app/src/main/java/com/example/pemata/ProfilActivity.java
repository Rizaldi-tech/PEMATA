package com.example.pemata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfilActivity extends AppCompatActivity {

    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        // Mengatur padding agar tidak tertutup status bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Tombol Logout
        logoutButton = findViewById(R.id.btn_logout);
        logoutButton.setOnClickListener(v -> {
            // Kembali ke halaman login dan hapus backstack
            Intent intent = new Intent(ProfilActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        // Navigasi bottom nav
        ImageView navPenyiraman = findViewById(R.id.nav_soil);
        ImageView navJadwal = findViewById(R.id.nav_calendar);
        ImageView navNotifikasi = findViewById(R.id.nav_notifikasi);

        navPenyiraman.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilActivity.this, Penyiraman.class);
            startActivity(intent);
            finish();
        });

        navJadwal.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilActivity.this, JadwalActivity.class);
            startActivity(intent);
            finish();
        });

        navNotifikasi.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilActivity.this, NotifikasiActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
