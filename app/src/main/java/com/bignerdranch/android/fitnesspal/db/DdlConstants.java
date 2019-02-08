package com.bignerdranch.android.fitnesspal.db;

interface DdlConstants {

    static final String CREATE_MEASUREMENTS_TABLE =
            "CREATE TABLE measurements(_id integer primary key, name)";

    static final String CREATE_EXERCISES_TABLE =
            "CREATE TABLE exercises(_id integer primary key , "
                    + "name text, "
                    + "measurement_id int references measurements(measurement_id))";

    static final String CREATE_TRAINING_SESSION_TYPES_TABLE =
            "CREATE TABLE training_session_types(_id integer primary key , name text)";

    static final String CREATE_EXERCISE_SETS_TABLE =
            "CREATE TABLE exercise_sets(_id integer primary key , "
                    + "count integer,"
                    + "exercise_id int references exercises(exercise_id), "
                    + "training_session_type_id int references training_session_types(training_session_type_id))";

    static final String CREATE_TRAINING_SESSIONS_TABLE =
            "CREATE TABLE training_sessions(_id integer primary key , "
                    + "date integer,"
                    + "training_session_type_id int references training_session_types(training_session_type_id))";
}