package com.bignerdranch.android.fitnesspal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bignerdranch.android.fitnesspal.db.FitnessPalDBHelper;
import com.bignerdranch.android.fitnesspal.model.ExerciseType;
import com.bignerdranch.android.fitnesspal.model.Measurement;

import java.util.List;

import static com.bignerdranch.android.fitnesspal.db.DbConstants.DATABASE_NAME;

public class MainActivity extends AppCompatActivity {

    private FitnessPalDBHelper fitnessPalDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fitnessPalDBHelper = new FitnessPalDBHelper(this);

        final List<Measurement> measurements = fitnessPalDBHelper.getMeasurements();

        for (final Measurement measurement : measurements) {
            Log.i(DATABASE_NAME, measurement.toString());
        }

        final List<ExerciseType> exerciseTypes = fitnessPalDBHelper.getExerciseTypes();

        for (final ExerciseType exerciseType : exerciseTypes) {
            Log.i(DATABASE_NAME, exerciseType.toString());
        }

    }
}
