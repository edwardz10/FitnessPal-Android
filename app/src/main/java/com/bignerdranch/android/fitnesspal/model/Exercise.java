package com.bignerdranch.android.fitnesspal.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Exercise {
    private Long id;
    private String name;
    private Long measurementId;

    public Exercise(final String name, final Measurement measurement) {
        this.name = name;
        this.measurementId = measurement.getId();
    }

    public static ContentValues getContentValues(final Exercise exercise) {
        ContentValues values = new ContentValues();
        values.put("name", exercise.name);
        values.put("measurement_id", exercise.measurementId);
        return values;
    }

    public static List<Exercise> getExercises(final SQLiteDatabase database) {
        final List<Exercise> exercises = new LinkedList<>();
        final Cursor cursor = database.rawQuery("select * from exercises", null);

        try {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                exercises.add(new Exercise(cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getLong(2)));
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return exercises;
    }

    public static Exercise getExerciseByName(final SQLiteDatabase database, final String name) {
        Exercise exercise = null;
        final String query = "select * from exercises where name = '" + name + "';";
        final Cursor cursor = database.rawQuery(query, null);

        try {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                exercise = new Exercise(cursor.getLong(0),
                                        cursor.getString(1),
                                        cursor.getLong(2));
            }
        } finally {
            cursor.close();
        }

        return exercise;
    }

    public static Exercise getExerciseById(final SQLiteDatabase database, final Long id) {
        Exercise exercise = null;
        final String query = "select * from exercises where _id = '" + id + "';";
        final Cursor cursor = database.rawQuery(query, null);

        try {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                exercise = new Exercise(cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getLong(2));
            }
        } finally {
            cursor.close();
        }

        return exercise;
    }

}
