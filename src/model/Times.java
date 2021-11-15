package model;

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

    /**
     * The method converts the time given into UTC time.
     * This method takes the time its given and converts it into UTC time for date base input.
     *
     * @param time current appointment time.
     * @return UTC time.
     */
    public static LocalDateTime convertToUTC(LocalDateTime time) {


        ZonedDateTime zdtStaring = time.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime utcZonedStarting = zdtStaring.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime ldtInStarting = utcZonedStarting.toLocalDateTime();
        System.out.println(ldtInStarting);


        return ldtInStarting;
    }

    /**
     * The method converts the time given into the users local time.
     * This method takes the time its given and converts it into local time for user viewing.
     *
     * @param time current appointment time.
     * @return UTC time.
     */
    public static LocalTime convertToLocalTime(LocalDateTime time) {
        String zone = ZoneId.systemDefault().getId();
        ZonedDateTime zdtStaring = time.atZone(ZoneId.of("UTC"));
        ZonedDateTime utcZonedStarting = zdtStaring.withZoneSameInstant(ZoneId.of(zone));
        LocalDateTime ldtInStarting = utcZonedStarting.toLocalDateTime();
        return ldtInStarting.toLocalTime();
    }

    /**
     * The method converts the time given into the users local date time.
     * This method takes the time its given and converts it into local time for user viewing.
     *
     * @param time current appointment time.
     * @return UTC time.
     */
    public static LocalDateTime convertToLocalDateTime(Instant time) {
        // TimeZone zoneTest = Calendar.getInstance().getTimeZone();


        //////////////////////////////////////////////////
/*
        LocalDateTime instant = time; //can be LocalDateTime
        ZoneId systemZone = ZoneId.systemDefault(); // my timezone
        ZoneOffset currentOffsetForMyZone = systemZone.getRules().getOffset(instant);
*/
        //////////////////////////////

        // System.out.println(currentOffsetForMyZone);


        // ZonedDateTime zdtStaring = time.atZone(ZoneId.of("UTC"));
        // ZonedDateTime utcZonedStarting = zdtStaring.withZoneSameInstant(ZoneId.of(zone));
        // LocalDateTime ldtInStarting = utcZonedStarting.toLocalDateTime();
/*
        LocalDateTime converted = LocalDateTime.of(ldtInStarting.toLocalDate(), ldtInStarting.toLocalTime());
       if(ldtInStarting.toLocalTime().getHour() > 12) {
            converted = LocalDateTime.of(ldtInStarting.toLocalDate(), ldtInStarting.toLocalTime().minusHours(12));
       }

*/
        //ZonedDateTime zdtStaring = time.atZone(ZoneId.of("UTC"));
        //ZonedDateTime utcZonedStarting = zdtStaring.withZoneSameInstant(ZoneId.of(zone));

        ///////////////////////////////////////////////////////

/*
        String dateInString = time.toLocalDateTime().toString();

        Instant instant = Instant.parse(dateInString);

        System.out.println("Instant : " + instant);

        //get date time only
        LocalDateTime result = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));

        //get localdate
        System.out.println("LocalDate : " + result.toLocalDate());

        //get date time + timezone
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of(zone));
        System.out.println(zonedDateTime);

        //get date time + timezone
        ZonedDateTime zonedDateTime2 = instant.atZone(ZoneId.of("America/New_York"));
        System.out.println(zonedDateTime2);

*/

        String zone = ZoneId.systemDefault().getId();
        ZonedDateTime zdtStaring = time.atZone(ZoneId.of("UTC"));
        ZonedDateTime utcZonedStarting = zdtStaring.withZoneSameInstant(ZoneId.of(zone));
        LocalDateTime ldtInStarting = zdtStaring.toLocalDateTime();

        return ldtInStarting;


    }

    public static LocalDateTime UTCTime(LocalDate date, String hour, String minute) {
        LocalDateTime ldt = LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), Integer.parseInt(hour), Integer.parseInt(minute));
        ZonedDateTime locZdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        ZonedDateTime utcZdt = locZdt.withZoneSameInstant(ZoneOffset.UTC);

        return utcZdt.toLocalDateTime();

    }

    public static LocalDateTime dateTimeLocal(LocalDate date, String hour, String minute) {
        LocalDateTime ldt = LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), Integer.parseInt(hour), Integer.parseInt(minute));
        ZonedDateTime locZdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        LocalDateTime appointmentDateTime = locZdt.toLocalDateTime();

        return appointmentDateTime;

    }

    //this works
    public static LocalDateTime toLocalTime(LocalDateTime time){
        String zone = ZoneId.systemDefault().getId();
        ZonedDateTime zdtStaring =time.atZone(ZoneId.of("UTC"));
        ZonedDateTime utcZonedStarting2 = zdtStaring.withZoneSameInstant(ZoneId.of(zone));
        LocalDateTime ldtInStarting = utcZonedStarting2.toLocalDateTime();

        return ldtInStarting;

    }
}


