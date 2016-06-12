package tieorange.edu.hellmanager.main.MyUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

/**
 * Created by tieorange on 05/05/16.
 */
public class MyTools {
    /**
     * return the example of start date of suffering process (to have more than 1000 years in result)
     * @return
     */
    public static Date getStartDateExample() {
        return new Date(700, 1, 1);
    }

    /**
     * return the example of end date of suffering process (to have more than 1000 years in result)
     * @return
     */
    public static Date getEndDateExample() {
        return new Date(2900, 1, 1);
    }

    /**
     * return the difference between first and last date in years
     * @param first
     * @param last
     * @return
     */
    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    /**
     * return Calendar initialized by date in a parameter
     * @param date
     * @return
     */
    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}
