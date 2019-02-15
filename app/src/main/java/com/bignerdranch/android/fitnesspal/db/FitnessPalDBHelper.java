package com.bignerdranch.android.fitnesspal.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.fitnesspal.db.dml.BasicDataLoader;
import com.bignerdranch.android.fitnesspal.db.dml.DataLoader;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.bignerdranch.android.fitnesspal.db.DbConstants.DATABASE_NAME;

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = getWritableDatabase();
        }
        
        return database;
    }
}
