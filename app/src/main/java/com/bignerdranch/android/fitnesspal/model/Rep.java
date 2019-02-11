package com.bignerdranch.android.fitnesspal.model;

import android.content.ContentValues;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rep {

    private Long id;
    private Integer times;
    private Float measurementNumber;
    private Long setId;
    private Long trainingSessionId;

    public Rep(final Integer times,
               final Float measurementNumber,
               final Set set,
               final TrainingSession trainingSession) {
        this(times, measurementNumber, set.getId(), trainingSession.getTrainingSessionTypeId());
    }

    public Rep(final Integer times,
               final Float measurementNumber,
               final Long setId,
               final Long trainingSessionId) {
        this.times = times;
        this.measurementNumber = measurementNumber;
        this.setId = setId;
        this.trainingSessionId = trainingSessionId;
    }

    public static ContentValues getContentValues(final Rep rep) {
        ContentValues values = new ContentValues();
        values.put("times", rep.times);
        values.put("measurement_number", rep.measurementNumber);
        values.put("set_id", rep.setId);
        values.put("training_session_id", rep.trainingSessionId);
        return values;
    }
}
