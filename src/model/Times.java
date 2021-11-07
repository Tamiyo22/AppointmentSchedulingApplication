package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
/**
 * The model class for Time.
 * This class is a model time object. It stores helpful time methods.
 */
public class Times {

    /**
     * The method returns true if the appointment time is within business hours in NY.
     * This method converts the current time for the appointment into NY time, and checks if it falls within
     * business hours.
     *
     * @param start the start date and time
     * @param startHour am or pm
     * @param endHour am or pm
     * @param end the end date and time
     * @return boolean
     */
    public static boolean withInBusinessHours (LocalDateTime start,String startHour, String endHour, LocalDateTime end){


    LocalTime businessStart = LocalTime.of(8, 00);
    LocalTime businessEnd = LocalTime.of(22, 00);


// for start time conversion
    ZonedDateTime zdtStaring = start.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
    ZonedDateTime utcZonedStarting = zdtStaring.withZoneSameInstant(ZoneId.of("America/New_York"));
    LocalDateTime ldtInStarting = utcZonedStarting.toLocalDateTime();
    LocalTime starting = ldtInStarting.toLocalTime();
    ;
    //for end time conversion
    ZonedDateTime zdtEnding = end.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
    ZonedDateTime utcZonedEnding = zdtEnding.withZoneSameInstant(ZoneId.of("America/New_York"));
    LocalDateTime ldtInEnding = utcZonedEnding.toLocalDateTime();
    LocalTime ending = ldtInEnding.toLocalTime();


    if((ending.getHour() == 0) && startHour.equals("PM")){
        return true;
    }

  if(starting.isBefore(businessStart) || ending.isAfter(businessEnd) ){

      return true;

    }

      return false;

}

    /**
     * The method converts the time given into UTC time.
     * This method takes the time its given and converts it into UTC time for date base input.
     * @param time current appointment time.
     * @return UTC time.
     */
    public static LocalDateTime convertToUTC (LocalDateTime time){
    ZonedDateTime zdtStaring = time.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
    ZonedDateTime utcZonedStarting = zdtStaring.withZoneSameInstant(ZoneId.of("UTC"));
    LocalDateTime ldtInStarting = utcZonedStarting.toLocalDateTime();

    return ldtInStarting;
}

    /**
     * The method converts the time given into the users local time.
     * This method takes the time its given and converts it into local time for user viewing.
     * @param time current appointment time.
     * @return UTC time.
     */
    public static LocalTime convertToLocalTime (LocalDateTime time){
          String zone = ZoneId.systemDefault().getId();

        ZonedDateTime zdtStaring = time.atZone(ZoneId.of("UTC"));
        ZonedDateTime utcZonedStarting = zdtStaring.withZoneSameInstant(ZoneId.of(zone));
        LocalDateTime ldtInStarting = utcZonedStarting.toLocalDateTime();

        return ldtInStarting.toLocalTime();
    }

    /**
     * The method converts the time given into the users local date time.
     * This method takes the time its given and converts it into local time for user viewing.
     * @param time current appointment time.
     * @return UTC time.
     */
    public static LocalDateTime convertToLocalDateTime (LocalDateTime time){
        String zone = ZoneId.systemDefault().getId();

        ZonedDateTime zdtStaring = time.atZone(ZoneId.of("UTC"));
        ZonedDateTime utcZonedStarting = zdtStaring.withZoneSameInstant(ZoneId.of(zone));
        LocalDateTime ldtInStarting = utcZonedStarting.toLocalDateTime();


        return ldtInStarting;
    }







}
