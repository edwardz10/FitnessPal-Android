package com.bignerdranch.android.fitnesspal.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bignerdranch.android.fitnesspal.db.dml.BasicDataLoader;
import com.bignerdranch.android.fitnesspal.db.dml.DataLoader;
import com.bignerdranch.android.fitnesspal.model.Exercise;
import com.bignerdranch.android.fitnesspal.model.Measurement;
import com.bignerdranch.android.fitnesspal.model.Rep;
import com.bignerdranch.android.fitnesspal.model.Set;
import com.bignerdranch.android.fitnesspal.model.TrainingSession;
import com.bignerdranch.android.fitnesspal.model.TrainingSessionType;
import com.bignerdranch.android.fitnesspal.util.Utils;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.bignerdranch.android.fitnesspal.db.DbConstants.DATABASE_NAME;
import static com.bignerdranch.android.fitnesspal.db.ddl.CreateTableConstants.*;
import static com.bignerdranch.android.fitnesspal.db.ddl.DropTableConstants.DROP_MEASUREMENTS_TABLE;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.AB_ROLLOUTS;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.BACK_BICEPS;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.BACK_EXTENSION;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.BARBELL_BENCH_PRESS;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.BARBELL_BENT_OVER_ROW;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.BARBELL_BICEPS_CURL;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.BARBELL_DEADLIFT;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.BARBELL_FRONT_RAISE;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.BARBELL_LUNGES;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.BARBELL_LYING_PULLOVER;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.BARBELL_MILITARY_PRESS;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.BARBELL_SHRUGS;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.BARBELL_SQUATS;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.CHEST_TRICEPS;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.CHIN_UPS;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.DUMPBELL_ARNOLD_PRESS;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.DUMPBELL_BICEPS_CURL;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.DUMPBELL_CHEST_FLYE;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.DUMPBELL_CHEST_PULLOVER;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.DUMPBELL_FRONT_SQUAT;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.DUMPBELL_LATERAL_RAISE;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.DUMPBELL_LUNGES;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.DUMPBELL_ONE_ARM_ROW;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.DUMPBELL_OVERHEAD_PRESS;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.HORIZONTAL_ROW;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.KILOGRAMS;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.LEG_PRESS;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.METERS;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.PLANK;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.PULL_UPS;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.SECONDS;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.SHOULDERS_LEGS;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.TIMES;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.TREADMILL;

@Data
@EqualsAndHashCode(callSuper = false)
public class FitnessPalDBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;

    private SQLiteDatabase database;
    private DataLoader dataLoader;

    public FitnessPalDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.database = db;
        dataLoader = new BasicDataLoader(database);


        dataLoader.ddl();
        dataLoader.dml();
//        ddl();
//        dml();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

//    private void ddl() {
//        getDatabase().execSQL(CREATE_MEASUREMENTS_TABLE);
//        getDatabase().execSQL(CREATE_EXERCISES_TABLE);
//        getDatabase().execSQL(CREATE_TRAINING_SESSION_TYPES_TABLE);
//        getDatabase().execSQL(CREATE_TRAINING_SESSIONS_TABLE);
//        getDatabase().execSQL(CREATE_SETS_TABLE);
//        getDatabase().execSQL(CREATE_REPS_TABLE);
//    }
//
//    private void ddlRollback(SQLiteDatabase db) {
//        db.execSQL(DROP_MEASUREMENTS_TABLE);
//    }
//
//    private void dml() {
//        createMeasurements();
//        createExercises();
//        createTrainingSessionTypes();
//        createSets();
//        createTrainingSessions();
//        createReps();
//    }
//
//    private void createMeasurements() {
//        getDatabase().insert("measurements", null, Measurement.getContentValues(new Measurement(KILOGRAMS)));
//        getDatabase().insert("measurements", null, Measurement.getContentValues(new Measurement(METERS)));
//        getDatabase().insert("measurements", null, Measurement.getContentValues(new Measurement(SECONDS)));
//        getDatabase().insert("measurements", null, Measurement.getContentValues(new Measurement(TIMES)));
//    }
//
//    private void createExercises() {
//        final Measurement measurementInKg = Measurement.getMeasurementByName(getDatabase(), KILOGRAMS);
//        final Measurement measurementInMeters = Measurement.getMeasurementByName(getDatabase(), METERS);
//        final Measurement measurementInSecs = Measurement.getMeasurementByName(getDatabase(), SECONDS);
//        final Measurement measurementInTimes = Measurement.getMeasurementByName(getDatabase(), TIMES);
//
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(BARBELL_SQUATS, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(BARBELL_DEADLIFT, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(BARBELL_LUNGES, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(BARBELL_BENCH_PRESS, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(BARBELL_LYING_PULLOVER, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(BARBELL_BENT_OVER_ROW, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(BARBELL_MILITARY_PRESS, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(BARBELL_FRONT_RAISE, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(BARBELL_SHRUGS, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(BARBELL_BICEPS_CURL, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(DUMPBELL_LATERAL_RAISE, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(DUMPBELL_LUNGES, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(DUMPBELL_OVERHEAD_PRESS, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(DUMPBELL_CHEST_FLYE, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(DUMPBELL_CHEST_PULLOVER, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(DUMPBELL_FRONT_SQUAT, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(DUMPBELL_ARNOLD_PRESS, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(DUMPBELL_BICEPS_CURL, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(DUMPBELL_ONE_ARM_ROW, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(BACK_EXTENSION, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(LEG_PRESS, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(HORIZONTAL_ROW, measurementInKg)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(PULL_UPS, measurementInTimes)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(CHIN_UPS, measurementInTimes)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(PLANK, measurementInSecs)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(AB_ROLLOUTS, measurementInTimes)));
//        getDatabase().insert("exercises", null,
//                Exercise.getContentValues(new Exercise(TREADMILL, measurementInMeters)));
//    }
//
//    private void createTrainingSessionTypes() {
//        getDatabase().insert("training_session_types",
//                null,
//                TrainingSessionType.getContentValues(new TrainingSessionType(CHEST_TRICEPS)));
//        getDatabase().insert("training_session_types",
//                null,
//                TrainingSessionType.getContentValues(new TrainingSessionType(BACK_BICEPS)));
//        getDatabase().insert("training_session_types",
//                null,
//                TrainingSessionType.getContentValues(new TrainingSessionType(SHOULDERS_LEGS)));
//    }
//
//    private void createSets() {
//        final TrainingSessionType backTrainingSessionType =
//                TrainingSessionType.getTrainingSessionTypeByName(getDatabase(), BACK_BICEPS);
//
//        final Exercise treadMill = Exercise.getExerciseByName(getDatabase(), TREADMILL);
//        final Exercise pullUps = Exercise.getExerciseByName(getDatabase(), PULL_UPS);
//        final Exercise dumpbellOneArm = Exercise.getExerciseByName(getDatabase(), DUMPBELL_ONE_ARM_ROW);
//        final Exercise horizontalRow = Exercise.getExerciseByName(getDatabase(), HORIZONTAL_ROW);
//        final Exercise backExtension = Exercise.getExerciseByName(getDatabase(), BACK_EXTENSION);
//        final Exercise barbellBicepsCurl = Exercise.getExerciseByName(getDatabase(), BARBELL_BICEPS_CURL);
//        final Exercise dumpBellBicepsCurl = Exercise.getExerciseByName(getDatabase(), DUMPBELL_BICEPS_CURL);
//        final Exercise legPress = Exercise.getExerciseByName(getDatabase(), LEG_PRESS);
//        final Exercise plank = Exercise.getExerciseByName(getDatabase(), PLANK);
//
//        getDatabase().insert("sets", null, Set.getContentValues(new Set(1, backTrainingSessionType, treadMill)));
//        getDatabase().insert("sets", null, Set.getContentValues(new Set(3, backTrainingSessionType, pullUps)));
//        getDatabase().insert("sets", null, Set.getContentValues(new Set(3, backTrainingSessionType, dumpbellOneArm)));
//        getDatabase().insert("sets", null, Set.getContentValues(new Set(3, backTrainingSessionType, horizontalRow)));
//        getDatabase().insert("sets", null, Set.getContentValues(new Set(3, backTrainingSessionType, backExtension)));
//        getDatabase().insert("sets", null, Set.getContentValues(new Set(3, backTrainingSessionType, barbellBicepsCurl)));
//        getDatabase().insert("sets", null, Set.getContentValues(new Set(3, backTrainingSessionType, dumpBellBicepsCurl)));
//        getDatabase().insert("sets", null, Set.getContentValues(new Set(3, backTrainingSessionType, legPress)));
//        getDatabase().insert("sets", null, Set.getContentValues(new Set(1, backTrainingSessionType, plank)));
//    }
//
//    private void createTrainingSessions() {
//        final TrainingSessionType chestTrainingSessionType =
//                TrainingSessionType.getTrainingSessionTypeByName(getDatabase(), BACK_BICEPS);
//
//        getDatabase().insert("training_sessions",
//                null,
//                TrainingSession.getContentValues(new TrainingSession(Utils.dateToMillis("21.09.2018"), chestTrainingSessionType)));
//
//        getDatabase().insert("training_sessions",
//                null,
//                TrainingSession.getContentValues(new TrainingSession(Utils.dateToMillis("28.09.2018"), chestTrainingSessionType)));
//
//        getDatabase().insert("training_sessions",
//                null,
//                TrainingSession.getContentValues(new TrainingSession(Utils.dateToMillis("05.10.2018"), chestTrainingSessionType)));
//    }
//
//    private void createReps() {
//        final TrainingSessionType backTrainingSessionType =
//                TrainingSessionType.getTrainingSessionTypeByName(getDatabase(), BACK_BICEPS);
//        final TrainingSession trainingSession =
//                TrainingSession.getTrainingSessionsByTrainingSessionType(getDatabase(), backTrainingSessionType).get(0);
//
//        final List<Set> sets = Set.getSetsByTrainingSessionType(getDatabase(), backTrainingSessionType);
//
//        final Set treadmillSet = Set.pickSetByExerciseName(getDatabase(), sets, TREADMILL);
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(1, 1000.0f, treadmillSet, trainingSession)));
//
//        final Set pullUpSet = Set.pickSetByExerciseName(getDatabase(), sets, PULL_UPS);
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(6, 1.0f, pullUpSet, trainingSession)));
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(4, 1.0f, pullUpSet, trainingSession)));
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(4, 1.0f, pullUpSet, trainingSession)));
//
//        final Set dumpbellOneArmSet = Set.pickSetByExerciseName(getDatabase(), sets, DUMPBELL_ONE_ARM_ROW);
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(10, 20.0f, dumpbellOneArmSet, trainingSession)));
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(10, 20.0f, dumpbellOneArmSet, trainingSession)));
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(8, 22.5f, dumpbellOneArmSet, trainingSession)));
//
//        final Set horizontalRowSet = Set.pickSetByExerciseName(getDatabase(), sets, HORIZONTAL_ROW);
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(10, 5.0f, horizontalRowSet, trainingSession)));
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(10, 5.0f, horizontalRowSet, trainingSession)));
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(9, 5.0f, horizontalRowSet, trainingSession)));
//
//        final Set backExtensionSet = Set.pickSetByExerciseName(getDatabase(), sets, BACK_EXTENSION);
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(10, 15.0f, backExtensionSet, trainingSession)));
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(10, 15.0f, backExtensionSet, trainingSession)));
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(10, 15.0f, backExtensionSet, trainingSession)));
//
//        final Set dumpbellBicepsCurlSet = Set.pickSetByExerciseName(getDatabase(), sets, DUMPBELL_BICEPS_CURL);
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(10, 10.0f, dumpbellBicepsCurlSet, trainingSession)));
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(8, 10.0f, dumpbellBicepsCurlSet, trainingSession)));
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(8, 10.0f, dumpbellBicepsCurlSet, trainingSession)));
//
//        final Set barbellBicepsCurlSet = Set.pickSetByExerciseName(getDatabase(), sets, DUMPBELL_BICEPS_CURL);
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(6, 20.0f, barbellBicepsCurlSet, trainingSession)));
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(8, 20.0f, barbellBicepsCurlSet, trainingSession)));
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(6, 20.0f, barbellBicepsCurlSet, trainingSession)));
//
//        final Set legPressSet = Set.pickSetByExerciseName(getDatabase(), sets, LEG_PRESS);
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(10, 50.0f, legPressSet, trainingSession)));
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(8, 100.0f, legPressSet, trainingSession)));
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(6, 150.0f, legPressSet, trainingSession)));
//
//        final Set plankSet = Set.pickSetByExerciseName(getDatabase(), sets, PLANK);
//        getDatabase().insert("reps", null, Rep.getContentValues(new Rep(1, 60.0f, legPressSet, trainingSession)));
//    }
//
    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = getWritableDatabase();
        }
        
        return database;
    }
}
