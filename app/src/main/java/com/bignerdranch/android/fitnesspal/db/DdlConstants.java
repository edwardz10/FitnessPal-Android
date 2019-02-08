package com.bignerdranch.android.fitnesspal.db;

interface DdlConstants {

    static final String CREATE_EXERCISE_TYPES_TABLE =
            "CREATE TABLE exercise_types(exercise_type_id integer primary key autoincrement, name text)";

    static final String CREATE_MEASUREMENTS_TABLE =
            "CREATE TABLE measurements(_id integer primary key autoincrement, name)";

    static final String CREATE_EXERCISES_TABLE =
            "CREATE TABLE exercises(exercise_id integer primary key autoincrement, "
                    + "name text, "
                    + "exercise_type_id int references exercise_types(exercise_type_id), "
                    + "measurement_id int references measurements(measurement_id))";

    static final String CREATE_TRAINING_SESSION_TYPES_TABLE =
            "CREATE TABLE training_session_types(training_session_type_id integer primary key autoincrement, name text)";

    static final String CREATE_EXERCISE_SETS_TABLE =
            "CREATE TABLE exercise_sets(exercise_set_id integer primary key autoincrement, "
                    + "count integer,"
                    + "exercise_id int references exercises(exercise_id), "
                    + "training_session_type_id int references training_session_types(training_session_type_id))";

    static final String CREATE_TRAINING_SESSIONS_TABLE =
            "CREATE TABLE training_sessions(training_session_id integer primary key autoincrement, "
                    + "date integer,"
                    + "training_session_type_id int references training_session_types(training_session_type_id))";
}