package com.bignerdranch.android.fitnesspal.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.fitnesspal.model.ExerciseType;
import com.bignerdranch.android.fitnesspal.model.Measurement;

import java.util.LinkedList;
import java.util.List;

import static com.bignerdranch.android.fitnesspal.db.DbConstants.DATABASE_NAME;
import static com.bignerdranch.android.fitnesspal.db.DdlConstants.CREATE_EXERCISES_TABLE;
import static com.bignerdranch.android.fitnesspal.db.DdlConstants.CREATE_EXERCISE_SETS_TABLE;
import static com.bignerdranch.android.fitnesspal.db.DdlConstants.CREATE_EXERCISE_TYPES_TABLE;
import static com.bignerdranch.android.fitnesspal.db.DdlConstants.CREATE_MEASUREMENTS_TABLE;
import static com.bignerdranch.android.fitnesspal.db.DdlConstants.CREATE_TRAINING_SESSIONS_TABLE;
import static com.bignerdranch.android.fitnesspal.db.DdlConstants.CREATE_TRAINING_SESSION_TYPES_TABLE;
import static com.bignerdranch.android.fitnesspal.db.DdlRollbackConstants.DROP_MEASUREMENTS_TABLE;

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

        final Cursor cursor = getReadableDatabase().rawQuery("select * from measurements", null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            measurements.add(new Measurement(cursor.getLong(0), cursor.getString(1)));
            cursor.moveToNext();
        }

        return measurements;
    }

    public List<ExerciseType> getExerciseTypes() {
        final List<ExerciseType> exerciseTypes = new LinkedList<>();

        final Cursor cursor = getReadableDatabase().rawQuery("select * from exercise_types", null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            exerciseTypes.add(new ExerciseType(cursor.getLong(0), cursor.getString(1)));
            cursor.moveToNext();
        }

        return exerciseTypes;
    }

    private void ddl() {
        database.execSQL(CREATE_EXERCISE_TYPES_TABLE);
        database.execSQL(CREATE_MEASUREMENTS_TABLE);
        database.execSQL(CREATE_EXERCISES_TABLE);
        database.execSQL(CREATE_TRAINING_SESSION_TYPES_TABLE);
        database.execSQL(CREATE_EXERCISE_SETS_TABLE);
        database.execSQL(CREATE_TRAINING_SESSIONS_TABLE);
    }

    private void ddlRollback(SQLiteDatabase db) {
        db.execSQL(DROP_MEASUREMENTS_TABLE);
    }

    private void dml() {
        createMeasurements();
        createExerciseTypes();
    }

    private void createMeasurements() {
        database.insert("measurements", null, Measurement.getContentValues("kg1"));
        database.insert("measurements", null, Measurement.getContentValues("meter2"));
        database.insert("measurements", null, Measurement.getContentValues("sec3"));
    }

    private void createExerciseTypes() {
        database.insert("exercise_types", null, ExerciseType.getContentValues("arms"));
        database.insert("exercise_types", null, ExerciseType.getContentValues("legs"));
        database.insert("exercise_types", null, ExerciseType.getContentValues("chest"));
        database.insert("exercise_types", null, ExerciseType.getContentValues("shoulders"));
        database.insert("exercise_types", null, ExerciseType.getContentValues("back"));
        database.insert("exercise_types", null, ExerciseType.getContentValues("endurance"));
    }

}
