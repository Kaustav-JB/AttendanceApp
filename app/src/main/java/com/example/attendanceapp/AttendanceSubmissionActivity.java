package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class AttendanceSubmissionActivity extends AppCompatActivity {
    private EditText facultyIdInput, studentsAttendedInput;
    private AutoCompleteTextView courseNameInput;
    private Button submitButton;
    private TextView dateInput;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_submission);

        facultyIdInput = findViewById(R.id.facultyIdInput);
        courseNameInput = findViewById(R.id.courseNameInput);
        studentsAttendedInput = findViewById(R.id.studentsAttendedInput);
        dateInput = findViewById(R.id.dateInput);
        submitButton = findViewById(R.id.submitButton);
        dbHelper = new DatabaseHelper(this);

        // AutoCompleteTextView setup
        String[] courses = {"Mathematics", "Physics", "Chemistry", "Computer Science"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, courses);
        courseNameInput.setAdapter(adapter);

        // Date picker dialog
        dateInput.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePicker = new DatePickerDialog(AttendanceSubmissionActivity.this, (view, year, month, day) -> {
                dateInput.setText(year + "-" + (month + 1) + "-" + day);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePicker.show();
        });

        // Submit button listener
        submitButton.setOnClickListener(v -> {
            String facultyId = facultyIdInput.getText().toString();
            String courseName = courseNameInput.getText().toString();
            int studentsAttended = Integer.parseInt(studentsAttendedInput.getText().toString());
            String lectureDate = dateInput.getText().toString();

            dbHelper.insertAttendance(facultyId, courseName, studentsAttended, lectureDate);
            Toast.makeText(this, "Attendance submitted successfully", Toast.LENGTH_SHORT).show();
        });
    }
}
