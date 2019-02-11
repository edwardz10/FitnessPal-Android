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
public class TrainingSession {
    private Long id;
    private Long date;
    private Long trainingSessionTypeId;

    public TrainingSession(final Long date,
                           final TrainingSessionType trainingSessionType) {
        this(date, trainingSessionType.getId());
    }

    public TrainingSession(final Long date,
                           final Long trainingSessionTypeId) {
        this.date = date;
        this.trainingSessionTypeId = trainingSessionTypeId;
    }

    public static ContentValues getContentValues(final TrainingSession trainingSession) {
        ContentValues values = new ContentValues();
        values.put("date", trainingSession.date);
        values.put("training_session_type_id", trainingSession.trainingSessionTypeId);
        return values;
    }

    public static List<TrainingSession> getTrainingSessionsByTrainingSessionType(final SQLiteDatabase database,
                                                         final TrainingSessionType trainingSessionType) {
        final List<TrainingSession> trainingSessions = new LinkedList<>();
        final Cursor cursor = database.rawQuery(
                "select * from training_sessions where training_session_type_id=" + trainingSessionType.getId(),
                null);

        try {
            cursor.moveToFirst();

            final Integer idColumn = cursor.getColumnIndex("_id");
            final Integer dateColumn = cursor.getColumnIndex("date");
            final Integer trainingSessionTypeIdColumn = cursor.getColumnIndex("training_session_type_id");

            while (!cursor.isAfterLast()) {
                trainingSessions.add(new TrainingSession(cursor.getLong(idColumn),
                        cursor.getLong(dateColumn),
                        cursor.getLong(trainingSessionTypeIdColumn)));
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return trainingSessions;
    }

}
