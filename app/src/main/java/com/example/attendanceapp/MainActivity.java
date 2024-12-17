package com.example.attendanceapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button submitAttendanceButton, viewAttendanceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitAttendanceButton = findViewById(R.id.submitAttendanceButton);
        viewAttendanceButton = findViewById(R.id.viewAttendanceButton);

        // Navigate to AttendanceSubmissionActivity
        submitAttendanceButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AttendanceSubmissionActivity.class);
            startActivity(intent);
        });

        // Navigate to ResultActivity
        viewAttendanceButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            startActivity(intent);
        });
    }
}
