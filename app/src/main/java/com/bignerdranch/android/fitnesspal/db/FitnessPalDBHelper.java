package com.bignerdranch.android.fitnesspal.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.fitnesspal.model.Exercise;
import com.bignerdranch.android.fitnesspal.model.Measurement;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;

import static com.bignerdranch.android.fitnesspal.db.DbConstants.DATABASE_NAME;
import static com.bignerdranch.android.fitnesspal.db.DdlConstants.CREATE_EXERCISES_TABLE;
import static com.bignerdranch.android.fitnesspal.db.DdlConstants.CREATE_MEASUREMENTS_TABLE;
import static com.bignerdranch.android.fitnesspal.db.DdlConstants.CREATE_REPS_TABLE;
import static com.bignerdranch.android.fitnesspal.db.DdlConstants.CREATE_SETS_TABLE;
import static com.bignerdranch.android.fitnesspal.db.DdlConstants.CREATE_TRAINING_SESSIONS_TABLE;
import static com.bignerdranch.android.fitnesspal.db.DdlConstants.CREATE_TRAINING_SESSION_TYPES_TABLE;
import static com.bignerdranch.android.fitnesspal.db.DdlRollbackConstants.DROP_MEASUREMENTS_TABLE;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.AB_ROLLOUTS;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.BARBELL_BENCH_PRESS;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.BARBELL_BENT_OVER_ROWS;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.BARBELL_BICEPS_CURLS;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.BARBELL_DEADLIFT;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.BARBELL_FRONT_RAISE;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.BARBELL_LUNGES;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.BARBELL_LYING_PULLOVER;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.BARBELL_MILITARY_PRESS;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.BARBELL_SHRUGS;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.BARBELL_SQUATS;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.CHIN_UPS;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.DUMPBELL_ARNOLD_PRESS;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.DUMPBELL_BICEPS_CURLS;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.DUMPBELL_CHEST_FLYE;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.DUMPBELL_CHEST_PULLOVER;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.DUMPBELL_FRONT_SQUAT;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.DUMPBELL_LATERAL_RAISES;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.DUMPBELL_LUNGES;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.DUMPBELL_OVERHEAD_PRESS;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.KILOGRAMS;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.METERS;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.PLANK;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.PULL_UPS;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.SECONDS;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.TIMES;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.TREADMILL;

@Data
public class FitnessPalDBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;

    private SQLiteDatabase database;

    public FitnessPalDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.database = db;

        ddl();
        dml();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ddlRollback(db);
    }

    private void ddl() {
        getDatabase().execSQL(CREATE_MEASUREMENTS_TABLE);
        getDatabase().execSQL(CREATE_EXERCISES_TABLE);
        getDatabase().execSQL(CREATE_TRAINING_SESSION_TYPES_TABLE);
        getDatabase().execSQL(CREATE_TRAINING_SESSIONS_TABLE);
        getDatabase().execSQL(CREATE_SETS_TABLE);
        getDatabase().execSQL(CREATE_REPS_TABLE);
    }

    private void ddlRollback(SQLiteDatabase db) {
        db.execSQL(DROP_MEASUREMENTS_TABLE);
    }

    private void dml() {
        createMeasurements();
        createExercises();
    }

    private void createMeasurements() {
        getDatabase().insert("measurements", null, Measurement.getContentValues(new Measurement(KILOGRAMS)));
        getDatabase().insert("measurements", null, Measurement.getContentValues(new Measurement(METERS)));
        getDatabase().insert("measurements", null, Measurement.getContentValues(new Measurement(SECONDS)));
        getDatabase().insert("measurements", null, Measurement.getContentValues(new Measurement(TIMES)));
    }

    private void createExercises() {
        final Measurement measurementInKg = Measurement.getMeasurementByName(getDatabase(), KILOGRAMS);
        final Measurement measurementInMeters = Measurement.getMeasurementByName(getDatabase(), METERS);
        final Measurement measurementInSecs = Measurement.getMeasurementByName(getDatabase(), SECONDS);
        final Measurement measurementInTimes = Measurement.getMeasurementByName(getDatabase(), TIMES);

        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(BARBELL_SQUATS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(BARBELL_DEADLIFT, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(BARBELL_LUNGES, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(BARBELL_BENCH_PRESS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(BARBELL_LYING_PULLOVER, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(BARBELL_BENT_OVER_ROWS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(BARBELL_MILITARY_PRESS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(BARBELL_FRONT_RAISE, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(BARBELL_SHRUGS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(BARBELL_BICEPS_CURLS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(DUMPBELL_LATERAL_RAISES, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(DUMPBELL_LUNGES, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(DUMPBELL_OVERHEAD_PRESS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(DUMPBELL_CHEST_FLYE, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(DUMPBELL_CHEST_PULLOVER, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(DUMPBELL_FRONT_SQUAT, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(DUMPBELL_ARNOLD_PRESS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(DUMPBELL_BICEPS_CURLS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(PULL_UPS, measurementInTimes)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(CHIN_UPS, measurementInTimes)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(PLANK, measurementInSecs)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(AB_ROLLOUTS, measurementInTimes)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(TREADMILL, measurementInMeters)));
    }

    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = getWritableDatabase();
        }
        
        return database;
    }
}
