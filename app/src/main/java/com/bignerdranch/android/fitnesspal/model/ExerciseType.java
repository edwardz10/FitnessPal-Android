package com.bignerdranch.android.fitnesspal.model;

import android.content.ContentValues;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExerciseType {
    private Long id;
    private String name;

    public static ContentValues getContentValues(final String name) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        return values;
    }
}
