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
public class Rep {

    private Long id;
    private Integer times;
    private Float measurementNumber;
    private Long setId;
    private Long trainingSessionId;

    public Rep(final Integer times,
               final Float measurementNumber,
               final Set set,
               final TrainingSession trainingSession) {
        this(times, measurementNumber, set.getId(), trainingSession.getId());
    }

    public Rep(final Integer times,
               final Float measurementNumber,
               final Long setId,
               final Long trainingSessionId) {
        this.times = times;
        this.measurementNumber = measurementNumber;
        this.setId = setId;
        this.trainingSessionId = trainingSessionId;
    }

    public static ContentValues getContentValues(final Rep rep) {
        ContentValues values = new ContentValues();
        values.put("times", rep.times);
        values.put("measurement_number", rep.measurementNumber);
        values.put("set_id", rep.setId);
        values.put("training_session_id", rep.trainingSessionId);
        return values;
    }


    public static List<Rep> getRepsByTrainingSession(final SQLiteDatabase database,
                                                     final TrainingSession trainingSession) {
        final List<Rep> reps = new LinkedList<>();
        final Cursor cursor = database.rawQuery(
                "select * from reps where training_session_id=" + trainingSession.getId(),
                null);

        try {
            cursor.moveToFirst();

            final Integer idColumn = cursor.getColumnIndex("_id");
            final Integer timesColumn = cursor.getColumnIndex("times");
            final Integer measurementNumber = cursor.getColumnIndex("measurement_number");
            final Integer setIdColumn = cursor.getColumnIndex("set_id");
            final Integer trainingSessionTypeIdColumn = cursor.getColumnIndex("training_session_id");

            while (!cursor.isAfterLast()) {
                reps.add(new Rep(cursor.getLong(idColumn),
                        cursor.getInt(timesColumn),
                        cursor.getFloat(measurementNumber),
                        cursor.getLong(setIdColumn),
                        cursor.getLong(trainingSessionTypeIdColumn)));
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return reps;
    }

    public static List<Rep> getAllReps(final SQLiteDatabase database) {
        final List<Rep> reps = new LinkedList<>();
        final Cursor cursor = database.rawQuery("select * from reps", null);

        try {
            cursor.moveToFirst();

            final Integer idColumn = cursor.getColumnIndex("_id");
            final Integer timesColumn = cursor.getColumnIndex("times");
            final Integer measurementNumber = cursor.getColumnIndex("measurement_number");
            final Integer setIdColumn = cursor.getColumnIndex("set_id");
            final Integer trainingSessionTypeIdColumn = cursor.getColumnIndex("training_session_id");

            while (!cursor.isAfterLast()) {
                reps.add(new Rep(cursor.getLong(idColumn),
                        cursor.getInt(timesColumn),
                        cursor.getFloat(measurementNumber),
                        cursor.getLong(setIdColumn),
                        cursor.getLong(trainingSessionTypeIdColumn)));
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return reps;
    }
}
