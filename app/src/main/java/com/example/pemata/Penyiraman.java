package com.example.pemata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Penyiraman extends AppCompatActivity {

    private Switch switchPompa;
    private Button btnManual, btnOtomatis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_penyiraman);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Switch Pompa Air
        switchPompa = findViewById(R.id.switch_pompa);
        switchPompa.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(this, "Pompa Air Dinyalakan", Toast.LENGTH_SHORT).show();
                // TODO: Kirim perintah ke hardware
            } else {
                Toast.makeText(this, "Pompa Air Dimatikan", Toast.LENGTH_SHORT).show();
                // TODO: Kirim perintah ke hardware
            }
        });

        // Tombol Mode Manual / Otomatis
        btnManual = findViewById(R.id.btn_manual);
        btnOtomatis = findViewById(R.id.btn_otomatis);

        btnManual.setOnClickListener(v -> {
            setMode(true);
            Toast.makeText(this, "Mode Manual Aktif", Toast.LENGTH_SHORT).show();
        });

        btnOtomatis.setOnClickListener(v -> {
            setMode(false);
            Toast.makeText(this, "Mode Otomatis Aktif", Toast.LENGTH_SHORT).show();
        });

        // Navigasi ke JadwalActivity saat nav_calendar diklik
        ImageView navCalendar = findViewById(R.id.nav_calendar);
        navCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Penyiraman.this, JadwalActivity.class);
                startActivity(intent);
            }
        });

        // Navigasi ke JadwalActivity saat nav_notication diklik
        ImageView navNotification = findViewById(R.id.nav_notification);
        navNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Penyiraman.this, NotifikasiActivity.class);
                startActivity(intent);
            }
        });

        // Navigasi ke ProfilActivity saat nav_profile diklik
        ImageView navProfile = findViewById(R.id.nav_profile);
        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Penyiraman.this, ProfilActivity.class);
                startActivity(intent);
            }
        });

        // Default mode
        setMode(true);
    }

    private void setMode(boolean isManual) {
        if (isManual) {
            btnManual.setBackgroundResource(R.drawable.manual_button_bg);
            btnOtomatis.setBackgroundResource(R.drawable.otomatis_button_inactive);
        } else {
            btnManual.setBackgroundResource(R.drawable.manual_button_inactive);
            btnOtomatis.setBackgroundResource(R.drawable.otomatis_button_bg);
        }
    }
}
