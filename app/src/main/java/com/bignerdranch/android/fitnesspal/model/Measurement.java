package com.bignerdranch.android.fitnesspal.model;

import android.content.ContentValues;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Measurement {
    private Long id;
    private String name;

    public Measurement(final String name) {
        this.name = name;
    }

    public static ContentValues getContentValues(final Measurement measurement) {
        ContentValues values = new ContentValues();
        values.put("name", measurement.name);
        return values;
    }
}
