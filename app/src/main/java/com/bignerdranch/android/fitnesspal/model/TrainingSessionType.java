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
public class TrainingSessionType {
    private Long id;
    private String name;

    public TrainingSessionType(final String name) {
        this.name = name;
    }

    public static ContentValues getContentValues(final TrainingSessionType TrainingSessionType) {
        ContentValues values = new ContentValues();
        values.put("name", TrainingSessionType.name);
        return values;
    }

    public static List<TrainingSessionType> getTrainingSessionTypes(final SQLiteDatabase database) {
        final List<TrainingSessionType> trainingSessionTypes = new LinkedList<>();

        final Cursor cursor = database.rawQuery("select * from training_session_types", null);

        try {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                trainingSessionTypes.add(new TrainingSessionType(cursor.getLong(0), cursor.getString(1)));
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return trainingSessionTypes;
    }

    public static TrainingSessionType getTrainingSessionTypeByName(final SQLiteDatabase database, final String name) {
        TrainingSessionType trainingSessionType = null;
        final String query = "select * from training_session_types where name = '" + name + "';";
        final Cursor cursor = database.rawQuery(query, null);

        try {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                trainingSessionType = new TrainingSessionType(cursor.getLong(0), cursor.getString(1));
            }
        } finally {
            cursor.close();
        }

        return trainingSessionType;
    }
}
