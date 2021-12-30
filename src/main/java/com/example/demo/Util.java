package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class Util {
    public static final String emailPattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    public static boolean patternMatches(String regexPattern, String input) {
        return Pattern.compile(regexPattern)
                .matcher(input)
                .matches();
    }

    public static boolean validateDateFormat(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
        }catch (ParseException ex) {
            return false;
        }
        return true;
    }
}
