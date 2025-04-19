package com.example.pemata;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoadingScreen extends AppCompatActivity {

    private static final int SPLASH_DELAY = 3000; // 3 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loading_screen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Pindah ke MainActivity setelah delay
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(LoadingScreen.this, MainActivity.class);
            startActivity(intent);
            finish(); // agar tidak kembali ke splash saat tekan tombol back
        }, SPLASH_DELAY);
    }
}
