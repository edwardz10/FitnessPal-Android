package com.bignerdranch.android.fitnesspal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bignerdranch.android.fitnesspal.db.FitnessPalDBHelper;
import com.bignerdranch.android.fitnesspal.model.Exercise;
import com.bignerdranch.android.fitnesspal.model.Measurement;
import com.bignerdranch.android.fitnesspal.model.Rep;
import com.bignerdranch.android.fitnesspal.model.Set;
import com.bignerdranch.android.fitnesspal.model.TrainingSession;
import com.bignerdranch.android.fitnesspal.model.TrainingSessionType;

import java.util.List;

import static com.bignerdranch.android.fitnesspal.db.DbConstants.DATABASE_NAME;
import static com.bignerdranch.android.fitnesspal.db.dml.DataConstants.BACK_BICEPS;

public class MainActivity extends AppCompatActivity {

    private FitnessPalDBHelper fitnessPalDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fitnessPalDBHelper = new FitnessPalDBHelper(this);

        for (final Measurement measurement : Measurement.getMeasurements(fitnessPalDBHelper.getReadableDatabase())) {
            Log.i(DATABASE_NAME, measurement.toString());
        }

        for (final Exercise exercise : Exercise.getExercises(fitnessPalDBHelper.getReadableDatabase())) {
            Log.i(DATABASE_NAME, exercise.toString());
        }

        for (final TrainingSessionType trainingSessionType : TrainingSessionType.getTrainingSessionTypes(fitnessPalDBHelper.getReadableDatabase())) {
            Log.i(DATABASE_NAME, trainingSessionType.toString());
        }

        final TrainingSessionType backBicepsSessionType =
                TrainingSessionType.getTrainingSessionTypeByName(fitnessPalDBHelper.getReadableDatabase(), BACK_BICEPS);

        for (final Set set : Set.getSetsByTrainingSessionType(fitnessPalDBHelper.getReadableDatabase(), backBicepsSessionType)) {
            Log.i(DATABASE_NAME, set.toString());
        }

        final List<TrainingSession> traningSessions = TrainingSession.getTrainingSessionsByTrainingSessionType(
                fitnessPalDBHelper.getReadableDatabase(), backBicepsSessionType);

        for (final TrainingSession trainingSession : traningSessions) {
            Log.i(DATABASE_NAME, trainingSession.toString());
        }

        final TrainingSession trainingSession = TrainingSession.pickSetByTrainingSessionTypeName(
                fitnessPalDBHelper.getReadableDatabase(), traningSessions, BACK_BICEPS);

        final List<Rep> reps = Rep.getRepsByTrainingSession(fitnessPalDBHelper.getReadableDatabase(), trainingSession);
        for (final Rep rep : reps) {
            Log.i(DATABASE_NAME, rep.toString());
        }
    }
}
