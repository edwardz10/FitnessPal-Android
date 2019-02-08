package com.bignerdranch.android.fitnesspal.model;

import android.content.ContentValues;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Exercise {
    private Long id;
    private String name;
    private Long measurementId;

    public Exercise(final String name, final Measurement measurement) {
        this.name = name;
        this.measurementId = measurement.getId();
    }

    public static ContentValues getContentValues(final Exercise exercise) {
        ContentValues values = new ContentValues();
        values.put("name", exercise.name);
        values.put("measurement_id", exercise.measurementId);
        return values;
    }
}
