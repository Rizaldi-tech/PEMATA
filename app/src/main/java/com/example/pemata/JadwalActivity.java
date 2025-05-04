package com.example.pemata;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class JadwalActivity extends AppCompatActivity {

    EditText editTime, editDate;
    Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jadwal);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTime = findViewById(R.id.editTime);
        editDate = findViewById(R.id.editDate);
        btnSave = findViewById(R.id.btnSaveSchedule);


        // Navigasi ke JadwalActivity ke Soil diklik
        ImageView navsoil = findViewById(R.id.card_soil_monitor);
        navsoil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JadwalActivity.this, Penyiraman.class);
                startActivity(intent);
            }
        });

        ImageView navprofil = findViewById(R.id.nav_profile);
        navprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JadwalActivity.this, ProfilActivity.class);
                startActivity(intent);
            }
        });


        // Time Picker
        editTime.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    JadwalActivity.this,
                    (view1, hourOfDay, minute1) -> editTime.setText(String.format("%02d:%02d", hourOfDay, minute1)),
                    hour, minute, true);
            timePickerDialog.show();
        });

        // Date Picker
        editDate.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    JadwalActivity.this,
                    (view12, year1, month1, dayOfMonth) -> {
                        month1 += 1;
                        editDate.setText(String.format("%02d/%02d/%d", dayOfMonth, month1, year1));
                    }, year, month, day);
            datePickerDialog.show();
        });

        // Tombol Simpan
        btnSave.setOnClickListener(v -> {
            String waktu = editTime.getText().toString();
            String tanggal = editDate.getText().toString();
            if (!waktu.isEmpty() && !tanggal.isEmpty()) {
                Toast.makeText(this, "Jadwal disimpan!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Lengkapi waktu dan tanggal", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
