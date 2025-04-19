package com.example.pemata;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.content.Intent;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NotifikasiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notifikasi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Navigasi ke NotifikasiActivity ke Soil diklik
        ImageView navsoil = findViewById(R.id.card_soil_monitor);
        navsoil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotifikasiActivity.this, Penyiraman.class);
                startActivity(intent);
            }
        });

        ImageView navprofil = findViewById(R.id.nav_profile);
        navprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotifikasiActivity.this, ProfilActivity.class);
                startActivity(intent);
            }
        });

        ImageView navcalender = findViewById(R.id.nav_calendar);
        navcalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotifikasiActivity.this, JadwalActivity.class);
                startActivity(intent);
            }
        });


    }
}
