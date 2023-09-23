package com.clock.time.services;

import com.clock.time.exception.InvalidTimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class TimeService {

    private static final String[] TimeTens = {
            "", "ten ", "twenty ", "thirty ", "forty ", "fifty "
    };

    private static final String[] TimeOnes = {
            "twelve ", "one ", "two ", "three ", "four ", "five ",
            "six ", "seven ", "eight ", "nine ", "ten ", "eleven ",
            "twelve ", "thirteen ", "fourteen ", "fifteen ",
            "sixteen ", "seventeen ", "eighteen ", "nineteen "
    };

    public String convert(String time) {

        try {
            time.trim().split(":");
        } catch (NumberFormatException e) {
            throw new NumberFormatException("You need to pass a valid 24 hour format hour");
        }

        String[] hours = time.trim().split(":");
        return getTimeInWords(hours);
    }

    private String getTimeInWords(String[] hours) {
        int hour;
        int minute;
        try {
            hour = Integer.parseInt(hours[0]);
            minute = Integer.parseInt(hours[1]);
            if (hour > 24) {
                throw new InvalidTimeException("Hour should be less than or equal to 24");
            }
            if (minute >= 60) {
                throw new InvalidTimeException("Minute should be less than 60");
            }

        } catch (NumberFormatException e) {
            throw new NumberFormatException("You need to pass a valid 24 hour format hour");
        } catch (Exception e) {
            throw new InvalidTimeException("You need to pass a valid 24 hour format hour");
        }

        String result = "";
        String hourInWords = convertToWords(hour, minute);

        if (hourInWords.equals("")) {
            result = "It was not possible to convert the hour passed to words";
        } else {
            result = hourInWords;
        }
        return result;
    }

    private String convertToWords(int hours, int minutes) {

        StringBuilder result = new StringBuilder();

        if (minutes == 0) {

            if (hours == 12) {
                return result.append("It's Midday").toString();
            }

            if (hours == 24) {
                return result.append("It's Midnight").toString();
            }

            result.append("It's ").append(TimeOnes[hours % 12]);

        } else if (minutes % 10 == 0) {
            result.append("It's ").append(TimeOnes[hours % 12]).append(TimeTens[minutes / 10]);
        } else if (minutes < 10 || minutes > 20) {
            result.append("It's ").append(TimeOnes[hours % 12]).append(TimeTens[minutes / 10]).append(TimeOnes[minutes % 10]);
        } else {
            result.append("It's ").append(TimeOnes[hours % 12]).append(TimeOnes[minutes]);
        }

        return result.toString();
    }

    public String convertCurrentTime(Date currentTime) {
        SimpleDateFormat hourFormat = new SimpleDateFormat("hh");
        SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");


        int hour = Integer.parseInt(hourFormat.format(currentTime));
        int minute = Integer.parseInt(minuteFormat.format(currentTime));
        String[] time = new String[]{String.valueOf(hour), String.valueOf(minute)};
        String result = getTimeInWords(time);
        return result;
    }
}
