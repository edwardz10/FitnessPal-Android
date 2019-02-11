package com.bignerdranch.android.fitnesspal.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import lombok.SneakyThrows;

public class Utils {

    private static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @SneakyThrows
    public static Long dateToMillis(final String date) {
        return dateFormat.parse(date).getTime();
    }
}
