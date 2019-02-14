package com.bignerdranch.android.fitnesspal.db.dml;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.fitnesspal.model.Exercise;
import com.bignerdranch.android.fitnesspal.model.Measurement;
import com.bignerdranch.android.fitnesspal.model.Rep;
import com.bignerdranch.android.fitnesspal.model.Set;
import com.bignerdranch.android.fitnesspal.model.TrainingSession;
import com.bignerdranch.android.fitnesspal.model.TrainingSessionType;
import com.bignerdranch.android.fitnesspal.util.Utils;

import java.util.Arrays;
import java.util.List;

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

public class BasicDataLoader extends AbstractDataLoader {

    public BasicDataLoader(SQLiteDatabase database) {
        super(database);
    }

    @Override
    public List<ContentValues> getMeasurements() {
        return Arrays.asList(
                Measurement.getContentValues(new Measurement(KILOGRAMS)),
                Measurement.getContentValues(new Measurement(METERS)),
                Measurement.getContentValues(new Measurement(SECONDS)),
                Measurement.getContentValues(new Measurement(TIMES))
        );
    }

    @Override
    public List<ContentValues> getExercises() {
        final Measurement measurementInKg = Measurement.getMeasurementByName(getDatabase(), KILOGRAMS);
        final Measurement measurementInMeters = Measurement.getMeasurementByName(getDatabase(), METERS);
        final Measurement measurementInSecs = Measurement.getMeasurementByName(getDatabase(), SECONDS);
        final Measurement measurementInTimes = Measurement.getMeasurementByName(getDatabase(), TIMES);

        return Arrays.asList(
                Exercise.getContentValues(new Exercise(BARBELL_SQUATS, measurementInKg)),
                Exercise.getContentValues(new Exercise(BARBELL_DEADLIFT, measurementInKg)),
                Exercise.getContentValues(new Exercise(BARBELL_LUNGES, measurementInKg)),
                Exercise.getContentValues(new Exercise(BARBELL_BENCH_PRESS, measurementInKg)),
                Exercise.getContentValues(new Exercise(BARBELL_LYING_PULLOVER, measurementInKg)),
                Exercise.getContentValues(new Exercise(BARBELL_BENT_OVER_ROW, measurementInKg)),
                Exercise.getContentValues(new Exercise(BARBELL_MILITARY_PRESS, measurementInKg)),
                Exercise.getContentValues(new Exercise(BARBELL_FRONT_RAISE, measurementInKg)),
                Exercise.getContentValues(new Exercise(BARBELL_SHRUGS, measurementInKg)),
                Exercise.getContentValues(new Exercise(BARBELL_BICEPS_CURL, measurementInKg)),
                Exercise.getContentValues(new Exercise(DUMPBELL_LATERAL_RAISE, measurementInKg)),
                Exercise.getContentValues(new Exercise(DUMPBELL_LUNGES, measurementInKg)),
                Exercise.getContentValues(new Exercise(DUMPBELL_OVERHEAD_PRESS, measurementInKg)),
                Exercise.getContentValues(new Exercise(DUMPBELL_CHEST_FLYE, measurementInKg)),
                Exercise.getContentValues(new Exercise(DUMPBELL_CHEST_PULLOVER, measurementInKg)),
                Exercise.getContentValues(new Exercise(DUMPBELL_FRONT_SQUAT, measurementInKg)),
                Exercise.getContentValues(new Exercise(DUMPBELL_ARNOLD_PRESS, measurementInKg)),
                Exercise.getContentValues(new Exercise(DUMPBELL_BICEPS_CURL, measurementInKg)),
                Exercise.getContentValues(new Exercise(DUMPBELL_ONE_ARM_ROW, measurementInKg)),
                Exercise.getContentValues(new Exercise(BACK_EXTENSION, measurementInKg)),
                Exercise.getContentValues(new Exercise(LEG_PRESS, measurementInKg)),
                Exercise.getContentValues(new Exercise(HORIZONTAL_ROW, measurementInKg)),
                Exercise.getContentValues(new Exercise(PULL_UPS, measurementInTimes)),
                Exercise.getContentValues(new Exercise(CHIN_UPS, measurementInTimes)),
                Exercise.getContentValues(new Exercise(PLANK, measurementInSecs)),
                Exercise.getContentValues(new Exercise(AB_ROLLOUTS, measurementInTimes)),
                Exercise.getContentValues(new Exercise(TREADMILL, measurementInMeters))
        );
    }

    @Override
    public List<ContentValues> getTrainingSessionTypes() {
        return Arrays.asList(
                TrainingSessionType.getContentValues(new TrainingSessionType(CHEST_TRICEPS)),
                TrainingSessionType.getContentValues(new TrainingSessionType(BACK_BICEPS)),
                TrainingSessionType.getContentValues(new TrainingSessionType(SHOULDERS_LEGS))
        );
    }

    @Override
    public List<ContentValues> getSets() {
        final TrainingSessionType backTrainingSessionType =
                TrainingSessionType.getTrainingSessionTypeByName(getDatabase(), BACK_BICEPS);

        final Exercise treadMill = Exercise.getExerciseByName(getDatabase(), TREADMILL);
        final Exercise pullUps = Exercise.getExerciseByName(getDatabase(), PULL_UPS);
        final Exercise dumpbellOneArm = Exercise.getExerciseByName(getDatabase(), DUMPBELL_ONE_ARM_ROW);
        final Exercise horizontalRow = Exercise.getExerciseByName(getDatabase(), HORIZONTAL_ROW);
        final Exercise backExtension = Exercise.getExerciseByName(getDatabase(), BACK_EXTENSION);
        final Exercise barbellBicepsCurl = Exercise.getExerciseByName(getDatabase(), BARBELL_BICEPS_CURL);
        final Exercise dumpBellBicepsCurl = Exercise.getExerciseByName(getDatabase(), DUMPBELL_BICEPS_CURL);
        final Exercise legPress = Exercise.getExerciseByName(getDatabase(), LEG_PRESS);
        final Exercise plank = Exercise.getExerciseByName(getDatabase(), PLANK);

        return Arrays.asList(
                Set.getContentValues(new Set(1, backTrainingSessionType, treadMill)),
                Set.getContentValues(new Set(3, backTrainingSessionType, pullUps)),
                Set.getContentValues(new Set(3, backTrainingSessionType, dumpbellOneArm)),
                Set.getContentValues(new Set(3, backTrainingSessionType, horizontalRow)),
                Set.getContentValues(new Set(3, backTrainingSessionType, backExtension)),
                Set.getContentValues(new Set(3, backTrainingSessionType, barbellBicepsCurl)),
                Set.getContentValues(new Set(3, backTrainingSessionType, dumpBellBicepsCurl)),
                Set.getContentValues(new Set(3, backTrainingSessionType, legPress)),
                Set.getContentValues(new Set(1, backTrainingSessionType, plank))
        );
    }

    @Override
    public List<ContentValues> getTrainingSessions() {
        final TrainingSessionType chestTrainingSessionType =
                TrainingSessionType.getTrainingSessionTypeByName(getDatabase(), BACK_BICEPS);

        return Arrays.asList(
                TrainingSession.getContentValues(new TrainingSession(Utils.dateToMillis("21.09.2018"), chestTrainingSessionType)),
                TrainingSession.getContentValues(new TrainingSession(Utils.dateToMillis("28.09.2018"), chestTrainingSessionType)),
                TrainingSession.getContentValues(new TrainingSession(Utils.dateToMillis("05.10.2018"), chestTrainingSessionType))
        );
    }

    @Override
    public List<ContentValues> getReps() {
        final TrainingSessionType backTrainingSessionType =
                TrainingSessionType.getTrainingSessionTypeByName(getDatabase(), BACK_BICEPS);
        final TrainingSession trainingSession =
                TrainingSession.getTrainingSessionsByTrainingSessionType(getDatabase(), backTrainingSessionType).get(0);

        final List<Set> sets = Set.getSetsByTrainingSessionType(getDatabase(), backTrainingSessionType);
        final Set treadmillSet = Set.pickSetByExerciseName(getDatabase(), sets, TREADMILL);
        final Set pullUpSet = Set.pickSetByExerciseName(getDatabase(), sets, PULL_UPS);
        final Set dumpbellOneArmSet = Set.pickSetByExerciseName(getDatabase(), sets, DUMPBELL_ONE_ARM_ROW);
        final Set horizontalRowSet = Set.pickSetByExerciseName(getDatabase(), sets, HORIZONTAL_ROW);
        final Set backExtensionSet = Set.pickSetByExerciseName(getDatabase(), sets, BACK_EXTENSION);
        final Set dumpbellBicepsCurlSet = Set.pickSetByExerciseName(getDatabase(), sets, DUMPBELL_BICEPS_CURL);
        final Set barbellBicepsCurlSet = Set.pickSetByExerciseName(getDatabase(), sets, DUMPBELL_BICEPS_CURL);
        final Set legPressSet = Set.pickSetByExerciseName(getDatabase(), sets, LEG_PRESS);
        final Set plankSet = Set.pickSetByExerciseName(getDatabase(), sets, PLANK);

        return Arrays.asList(
                Rep.getContentValues(new Rep(1, 1000.0f, treadmillSet, trainingSession)),

                Rep.getContentValues(new Rep(6, 1.0f, pullUpSet, trainingSession)),
                Rep.getContentValues(new Rep(4, 1.0f, pullUpSet, trainingSession)),
                Rep.getContentValues(new Rep(4, 1.0f, pullUpSet, trainingSession)),

                Rep.getContentValues(new Rep(10, 20.0f, dumpbellOneArmSet, trainingSession)),
                Rep.getContentValues(new Rep(10, 20.0f, dumpbellOneArmSet, trainingSession)),
                Rep.getContentValues(new Rep(8, 22.5f, dumpbellOneArmSet, trainingSession)),

                Rep.getContentValues(new Rep(10, 5.0f, horizontalRowSet, trainingSession)),
                Rep.getContentValues(new Rep(10, 5.0f, horizontalRowSet, trainingSession)),
                Rep.getContentValues(new Rep(9, 5.0f, horizontalRowSet, trainingSession)),

                Rep.getContentValues(new Rep(10, 15.0f, backExtensionSet, trainingSession)),
                Rep.getContentValues(new Rep(10, 15.0f, backExtensionSet, trainingSession)),
                Rep.getContentValues(new Rep(10, 15.0f, backExtensionSet, trainingSession)),

                Rep.getContentValues(new Rep(10, 10.0f, dumpbellBicepsCurlSet, trainingSession)),
                Rep.getContentValues(new Rep(8, 10.0f, dumpbellBicepsCurlSet, trainingSession)),
                Rep.getContentValues(new Rep(8, 10.0f, dumpbellBicepsCurlSet, trainingSession)),

                Rep.getContentValues(new Rep(6, 20.0f, barbellBicepsCurlSet, trainingSession)),
                Rep.getContentValues(new Rep(8, 20.0f, barbellBicepsCurlSet, trainingSession)),
                Rep.getContentValues(new Rep(6, 20.0f, barbellBicepsCurlSet, trainingSession)),

                Rep.getContentValues(new Rep(10, 50.0f, legPressSet, trainingSession)),
                Rep.getContentValues(new Rep(8, 100.0f, legPressSet, trainingSession)),
                Rep.getContentValues(new Rep(6, 150.0f, legPressSet, trainingSession)),

                Rep.getContentValues(new Rep(1, 60.0f, plankSet, trainingSession))
        );
    }
}
