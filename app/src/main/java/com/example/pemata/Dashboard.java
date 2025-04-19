package com.example.pemata;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Navigasi ke halaman Penyiraman
        LinearLayout soilCard = findViewById(R.id.card_soil_monitor);
        soilCard.setOnClickListener(v -> {
            startActivity(new Intent(Dashboard.this, Penyiraman.class));
        });

        // Navigasi ke halaman Jadwal
        LinearLayout scheduleCard = findViewById(R.id.card_schedule);
        scheduleCard.setOnClickListener(v -> {
            startActivity(new Intent(Dashboard.this, JadwalActivity.class));
        });

        // Navigasi ke halaman Notifikasi
        LinearLayout notifCard = findViewById(R.id.card_notification);
        notifCard.setOnClickListener(v -> {
            startActivity(new Intent(Dashboard.this, NotifikasiActivity.class));
        });

        // Navigasi ke halaman Profil
        LinearLayout profilCard = findViewById(R.id.card_profil);
        profilCard.setOnClickListener(v -> {
            startActivity(new Intent(Dashboard.this, ProfilActivity.class));
        });
    }
}
