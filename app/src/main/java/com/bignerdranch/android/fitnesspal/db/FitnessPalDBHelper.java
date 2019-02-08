package com.bignerdranch.android.fitnesspal.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.fitnesspal.model.Exercise;
import com.bignerdranch.android.fitnesspal.model.Measurement;

import java.util.LinkedList;
import java.util.List;

import static com.bignerdranch.android.fitnesspal.db.DbConstants.DATABASE_NAME;
import static com.bignerdranch.android.fitnesspal.db.DdlConstants.CREATE_EXERCISES_TABLE;
import static com.bignerdranch.android.fitnesspal.db.DdlConstants.CREATE_EXERCISE_SETS_TABLE;
import static com.bignerdranch.android.fitnesspal.db.DdlConstants.CREATE_MEASUREMENTS_TABLE;
import static com.bignerdranch.android.fitnesspal.db.DdlConstants.CREATE_TRAINING_SESSIONS_TABLE;
import static com.bignerdranch.android.fitnesspal.db.DdlConstants.CREATE_TRAINING_SESSION_TYPES_TABLE;
import static com.bignerdranch.android.fitnesspal.db.DdlRollbackConstants.DROP_MEASUREMENTS_TABLE;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.*;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.KILOGRAMS;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.METERS;
import static com.bignerdranch.android.fitnesspal.db.DmlConstants.SECONDS;

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

    public List<Measurement> getMeasurements() {
        final List<Measurement> measurements = new LinkedList<>();

        final Cursor cursor = getDatabase().rawQuery("select * from measurements", null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            measurements.add(new Measurement(cursor.getLong(0), cursor.getString(1)));
            cursor.moveToNext();
        }

        return measurements;
    }

    public Measurement getMeasurementByName(final String name) {
        final String query = "select * from measurements where name = '" + name + "';";
        final Cursor cursor = getDatabase().rawQuery(query, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return new Measurement(cursor.getLong(0), cursor.getString(1));
        }

        return null;
    }

    public List<Exercise> getExercises() {
        final List<Exercise> exercises = new LinkedList<>();

        final Cursor cursor = getDatabase().rawQuery("select * from exercises", null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            exercises.add(new Exercise(cursor.getLong(0),
                                       cursor.getString(1),
                                       cursor.getLong(2)));
            cursor.moveToNext();
        }

        return exercises;
    }

    private void ddl() {
        getDatabase().execSQL(CREATE_MEASUREMENTS_TABLE);
        getDatabase().execSQL(CREATE_EXERCISES_TABLE);
        getDatabase().execSQL(CREATE_TRAINING_SESSION_TYPES_TABLE);
        getDatabase().execSQL(CREATE_EXERCISE_SETS_TABLE);
        getDatabase().execSQL(CREATE_TRAINING_SESSIONS_TABLE);
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
    }

    private void createExercises() {
        final Measurement measurementInKg = getMeasurementByName(KILOGRAMS);
        final Measurement measurementInMeters = getMeasurementByName(METERS);
        final Measurement measurementInSecs = getMeasurementByName(SECONDS);

        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(DEADLIFTS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(BACK_SQUATS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(FRONT_SQUATS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(BENCH_PRESS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(INCLINE_FRENCH_PRESS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(OVERHEAD_PRESS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(PUSH_PRESS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(BENT_OVER_ROWS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(SNATCH, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(CLEAN_AND_JERK, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(BICEP_CURLS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(SKULL_CRUSHERS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(PULL_UPS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(CHIN_UPS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(MUSCLE_UPS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(PUSH_UPS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(DIPS, measurementInKg)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(PLANK, measurementInSecs)));
        getDatabase().insert("exercises", null,
                Exercise.getContentValues(new Exercise(AB_ROLLOUTS, measurementInKg)));
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
                Exercise.getContentValues(new Exercise(TREADMILL, measurementInMeters)));
    }

    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = getWritableDatabase();
        }
        
        return database;
    }
}
