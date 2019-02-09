package com.bignerdranch.android.fitnesspal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bignerdranch.android.fitnesspal.db.FitnessPalDBHelper;
import com.bignerdranch.android.fitnesspal.model.Exercise;
import com.bignerdranch.android.fitnesspal.model.Measurement;

import static com.bignerdranch.android.fitnesspal.db.DbConstants.DATABASE_NAME;

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

    }
}
