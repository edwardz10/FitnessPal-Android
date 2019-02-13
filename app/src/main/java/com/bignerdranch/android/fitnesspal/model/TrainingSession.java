package com.bignerdranch.android.fitnesspal.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.bignerdranch.android.fitnesspal.util.Utils.millisToDate;

@Getter
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

    public static TrainingSession getTrainingSessionsByDate(final SQLiteDatabase database,
                                                                  final Long date) {
        TrainingSession trainingSession = null;
        final Cursor cursor = database.rawQuery(
                "select * from training_sessions where date=" + date,
                null);

        try {
            cursor.moveToFirst();

            final Integer idColumn = cursor.getColumnIndex("_id");
            final Integer dateColumn = cursor.getColumnIndex("date");
            final Integer trainingSessionTypeIdColumn = cursor.getColumnIndex("training_session_type_id");

            if (!cursor.isAfterLast()) {
                trainingSession = new TrainingSession(
                        cursor.getLong(idColumn),
                        cursor.getLong(dateColumn),
                        cursor.getLong(trainingSessionTypeIdColumn));
            }
        } finally {
            cursor.close();
        }

        return trainingSession;
    }

    public static TrainingSession pickSetByTrainingSessionTypeName(final SQLiteDatabase database,
                                                                   final List<TrainingSession> trainingSessions,
                                                                   final String trainingSessionTypeName) {
        TrainingSession result = null;
        final TrainingSessionType trainingSessionType
                = TrainingSessionType.getTrainingSessionTypeByName(database, trainingSessionTypeName);

        for (final TrainingSession trainingSession : trainingSessions) {
            if (trainingSession.trainingSessionTypeId.equals(trainingSessionType.getId())) {
                result = trainingSession;
                break;
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "id=" + id
                + ", date=" + millisToDate(date)
                + ", trainingSessionTypeId=" + trainingSessionTypeId;
    }
}
