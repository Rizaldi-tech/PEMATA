package com.example.pemata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Penyiraman extends AppCompatActivity {

    private Switch switchPompa;
    private Button btnManual, btnOtomatis;
    private TextView phText;

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

        // Inisialisasi View
        phText = findViewById(R.id.ph_text);

        // Firebase reference
        DatabaseReference kelembabanRef = FirebaseDatabase.getInstance(
                "https://pemata-87b27-default-rtdb.asia-southeast1.firebasedatabase.app/"
        ).getReference("sensor/soil_moisture");

        // Listener Firebase
        kelembabanRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String kelembaban = snapshot.getValue().toString();
                    phText.setText(kelembaban + " %");
                } else {
                    phText.setText("N/A");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                phText.setText("Error");
            }
        });


        // Navigasi Bottom Nav
        findViewById(R.id.nav_calendar).setOnClickListener(v ->
                startActivity(new Intent(this, JadwalActivity.class)));

        findViewById(R.id.nav_notification).setOnClickListener(v ->
                startActivity(new Intent(this, NotifikasiActivity.class)));

        findViewById(R.id.nav_profile).setOnClickListener(v ->
                startActivity(new Intent(this, ProfilActivity.class)));

    }
}
