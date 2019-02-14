package com.bignerdranch.android.fitnesspal.db.dml;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

import static com.bignerdranch.android.fitnesspal.db.ddl.CreateTableConstants.CREATE_EXERCISES_TABLE;
import static com.bignerdranch.android.fitnesspal.db.ddl.CreateTableConstants.CREATE_MEASUREMENTS_TABLE;
import static com.bignerdranch.android.fitnesspal.db.ddl.CreateTableConstants.CREATE_REPS_TABLE;
import static com.bignerdranch.android.fitnesspal.db.ddl.CreateTableConstants.CREATE_SETS_TABLE;
import static com.bignerdranch.android.fitnesspal.db.ddl.CreateTableConstants.CREATE_TRAINING_SESSIONS_TABLE;
import static com.bignerdranch.android.fitnesspal.db.ddl.CreateTableConstants.CREATE_TRAINING_SESSION_TYPES_TABLE;

@Data
@AllArgsConstructor
public abstract class AbstractDataLoader implements DataLoader {

    private SQLiteDatabase database;

    @Override
    public void ddl() {
        database.execSQL(CREATE_MEASUREMENTS_TABLE);
        database.execSQL(CREATE_EXERCISES_TABLE);
        database.execSQL(CREATE_TRAINING_SESSION_TYPES_TABLE);
        database.execSQL(CREATE_TRAINING_SESSIONS_TABLE);
        database.execSQL(CREATE_SETS_TABLE);
        database.execSQL(CREATE_REPS_TABLE);
    }

    @Override
    public void dml() {
        for (final ContentValues contentValue : getMeasurements()) {
            getDatabase().insert("measurements", null, contentValue);
        }

        for (final ContentValues contentValue : getExercises()) {
            getDatabase().insert("exercises", null, contentValue);
        }

        for (final ContentValues contentValue : getTrainingSessionTypes()) {
            getDatabase().insert("training_session_types", null, contentValue);
        }

        for (final ContentValues contentValue : getSets()) {
            getDatabase().insert("sets", null, contentValue);
        }

        for (final ContentValues contentValue : getTrainingSessions()) {
            getDatabase().insert("training_sessions", null, contentValue);
        }

        for (final ContentValues contentValue : getReps()) {
            getDatabase().insert("reps", null, contentValue);
        }
    }

    protected abstract List<ContentValues> getMeasurements();
    protected abstract List<ContentValues> getExercises();
    protected abstract List<ContentValues> getTrainingSessionTypes();
    protected abstract List<ContentValues> getSets();
    protected abstract List<ContentValues> getTrainingSessions();
    protected abstract List<ContentValues> getReps();
}
