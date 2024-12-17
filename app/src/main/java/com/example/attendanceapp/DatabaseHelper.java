package com.example.attendanceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "attendance.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Attendance";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "faculty_id TEXT, " +
                "course_name TEXT, " +
                "students_attended INTEGER, " +
                "lecture_date TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertAttendance(String facultyId, String courseName, int studentsAttended, String lectureDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("faculty_id", facultyId);
        values.put("course_name", courseName);
        values.put("students_attended", studentsAttended);
        values.put("lecture_date", lectureDate);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Cursor getAttendanceByDate(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE lecture_date = ?", new String[]{date});
    }

    public Cursor getAttendanceByCourse(String courseName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE course_name = ?", new String[]{courseName});
    }
}
