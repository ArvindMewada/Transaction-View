package com.example.assignment.Utils;

import java.util.regex.Pattern;

public class Utils {
    public Pattern patternFun (){
        return Pattern.compile("(?i)(?:(?:RS|INR|MRP)\\.?\\s?)(\\d+(:?\\,\\d+)?(\\,\\d+)?)");
    }
}
