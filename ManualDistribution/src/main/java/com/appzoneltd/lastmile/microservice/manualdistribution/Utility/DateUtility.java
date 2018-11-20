package com.appzoneltd.lastmile.microservice.manualdistribution.Utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hashish on 3/29/2017.
 */
public class DateUtility {
    public static Date getDateTimeFormat(Date date, Date time) {
        // Date DateFormatter
        SimpleDateFormat timeDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /// Full String Date
        String fullString = getStringFromDate(date) + " " + getStringFromTime(time);
        /// Date to be Returned
        Date fullDate = null;

        try {
            fullDate = timeDateFormatter.parse(fullString);
        } catch (ParseException e) {
            e.printStackTrace();
        }//end try_catch Block
        /// return result
        return fullDate;
    }


    public static Date get24HourTimeFromString(String time) {
        // Time Formats
        DateFormat twelveHourFormat = new SimpleDateFormat("hh:mm a");
        DateFormat twentyFourHourFormat = new SimpleDateFormat("HH:mm:ss");
        //Date to be returned
        Date twentyFourHourDate = null;
        // Parsing the Date
        try {
            // converting String to twentyForHourDate
            Date twelveHourDate = twelveHourFormat.parse(time);
            String twentyFourHourString = twentyFourHourFormat.format(twelveHourDate);
            twentyFourHourDate = twentyFourHourFormat.parse(twentyFourHourString);
        } catch (ParseException e) {
            e.printStackTrace();
        }//end try_catch Block
        // return result
        return twentyFourHourDate;
    }//end


    public static String getStringFromDate(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String stringDate = sdf.format(date);

        return stringDate;

    }


    public static String getStringFromTime(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        String stringDate = sdf.format(date);

        return stringDate;

    }

    public static Date longToDate(long timeStamp) {
        Date now = new Date();
        Date date = new Date(timeStamp);
        date.setYear(now.getYear());
        date.setMonth(now.getMonth());
        date.setDate(now.getDate());
        return date;
    }

}
