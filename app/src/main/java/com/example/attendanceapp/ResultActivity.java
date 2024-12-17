package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private EditText dateInput, courseNameInput;
    private Button searchByDateButton, searchByCourseButton;
    private TextView resultView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        dateInput = findViewById(R.id.dateInput);
        courseNameInput = findViewById(R.id.courseNameInput);
        searchByDateButton = findViewById(R.id.searchByDateButton);
        searchByCourseButton = findViewById(R.id.searchByCourseButton);
        resultView = findViewById(R.id.resultView);
        dbHelper = new DatabaseHelper(this);

        searchByDateButton.setOnClickListener(v -> {
            String date = dateInput.getText().toString();
            Cursor cursor = dbHelper.getAttendanceByDate(date);
            displayResults(cursor);
        });

        searchByCourseButton.setOnClickListener(v -> {
            String courseName = courseNameInput.getText().toString();
            Cursor cursor = dbHelper.getAttendanceByCourse(courseName);
            displayResults(cursor);
        });
    }

    private void displayResults(Cursor cursor) {
        StringBuilder results = new StringBuilder();
        while (cursor.moveToNext()) {
            results.append("Faculty ID: ").append(cursor.getString(1)).append("\n")
                    .append("Course Name: ").append(cursor.getString(2)).append("\n")
                    .append("Students Attended: ").append(cursor.getInt(3)).append("\n")
                    .append("Lecture Date: ").append(cursor.getString(4)).append("\n\n");
        }
        resultView.setText(results.toString());
        cursor.close();
    }
}
