package com.bignerdranch.android.fitnesspal.db;

interface DdlConstants {

    static final String CREATE_MEASUREMENTS_TABLE =
            "CREATE TABLE measurements(_id integer primary key, name)";

    static final String CREATE_EXERCISES_TABLE =
            "CREATE TABLE exercises(_id integer primary key , "
                    + "name text, "
                    + "measurement_id integer references measurements(_id))";

    static final String CREATE_TRAINING_SESSION_TYPES_TABLE =
            "CREATE TABLE training_session_types(_id integer primary key , name text)";

    static final String CREATE_TRAINING_SESSIONS_TABLE =
            "CREATE TABLE training_sessions(_id integer primary key , "
                    + "date integer,"
                    + "training_session_type_id integer references training_session_types(_id))";

    static final String CREATE_SETS_TABLE =
            "CREATE TABLE sets(_id integer primary key , "
                    + "count integer,"
                    + "exercise_id integer references exercises(_id), "
                    + "training_session_type_id integer references training_session_types(_id))";

    static final String CREATE_REPS_TABLE =
            "CREATE TABLE reps(_id integer primary key , "
                    + "count integer,"
                    + "set_id integer references sets(_id))";

}