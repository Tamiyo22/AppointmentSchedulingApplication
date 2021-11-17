package util;

import java.time.*;

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
     * @param start     the start date and time
     * @param startHour am or pm
     * @param endHour   am or pm
     * @param end       the end date and time
     * @return boolean
     */
    public static boolean withInBusinessHours(LocalDateTime start, String startHour, String endHour, LocalDateTime end) {


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


        if ((ending.getHour() == 0) && startHour.equals("PM")) {
            return true;
        }

        if (starting.isBefore(businessStart) || ending.isAfter(businessEnd)) {

            return true;

        }

        return false;

    }


}


