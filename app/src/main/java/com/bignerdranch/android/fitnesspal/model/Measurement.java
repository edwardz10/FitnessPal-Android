package com.bignerdranch.android.fitnesspal.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

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

    public static List<Measurement> getMeasurements(final SQLiteDatabase database) {
        final List<Measurement> measurements = new LinkedList<>();

        final Cursor cursor = database.rawQuery("select * from measurements", null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            measurements.add(new Measurement(cursor.getLong(0), cursor.getString(1)));
            cursor.moveToNext();
        }

        return measurements;
    }

    public static Measurement getMeasurementByName(final SQLiteDatabase database, final String name) {
        final String query = "select * from measurements where name = '" + name + "';";
        final Cursor cursor = database.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return new Measurement(cursor.getLong(0), cursor.getString(1));
        }

        return null;
    }


}
