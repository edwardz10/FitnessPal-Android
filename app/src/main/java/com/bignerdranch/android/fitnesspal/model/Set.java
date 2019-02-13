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
public class Set {
    private Long id;
    private Integer count;
    private Long trainingSessionTypeId;
    private Long exerciseId;

    public Set(final Integer count,
               final TrainingSessionType trainingSessionType,
               final Exercise exercise) {
        this(count, trainingSessionType.getId(), exercise.getId());
    }

    public Set(final Integer count,
               final Long trainingSessionTypeId,
               final Long exerciseId) {
        this.count = count;
        this.trainingSessionTypeId = trainingSessionTypeId;
        this.exerciseId = exerciseId;
    }

    public static ContentValues getContentValues(final Set set) {
        ContentValues values = new ContentValues();
        values.put("count", set.count);
        values.put("training_session_type_id", set.trainingSessionTypeId);
        values.put("exercise_id", set.exerciseId);
        return values;
    }

    public static List<Set> getSetsByTrainingSessionType(final SQLiteDatabase database,
                                                         final TrainingSessionType trainingSessionType) {
        final List<Set> sets = new LinkedList<>();
        final Cursor cursor = database.rawQuery(
                "select * from sets where training_session_type_id=" + trainingSessionType.getId(),
                null);

        try {
            cursor.moveToFirst();

            final Integer idColumn = cursor.getColumnIndex("_id");
            final Integer countColumn = cursor.getColumnIndex("count");
            final Integer exerciseIdColumn = cursor.getColumnIndex("exercise_id");
            final Integer trainingSessionTypeIdColumn = cursor.getColumnIndex("training_session_type_id");

            while (!cursor.isAfterLast()) {
                sets.add(new Set(cursor.getLong(idColumn),
                        cursor.getInt(countColumn),
                        cursor.getLong(trainingSessionTypeIdColumn),
                        cursor.getLong(exerciseIdColumn)));
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return sets;
    }

    public static Set pickSetByExerciseName(final SQLiteDatabase database,
                                            final List<Set> sets,
                                            final String exerciseName) {
        Set result = null;
        final Exercise exercise = Exercise.getExerciseByName(database, exerciseName);

        for (final Set set : sets) {
            if (set.exerciseId.equals(exercise.getId())) {
                result = set;
                break;
            }
        }

        return result;
    }
}
