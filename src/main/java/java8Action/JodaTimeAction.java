package java8Action;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * @author gavin
 * @createDate 2020/3/6
 * https://www.ibm.com/developerworks/cn/java/j-jodatime.html
 */
public class JodaTimeAction {

    public static void main(String[] args) {

        jodaTimeFunction();

    }

    public static void jodaTimeFunction(){

        System.out.println(new DateTime().toString("yyyy/MM/dd HH:mm:ss"));
        System.out.println(new LocalDate().toString("yyyy/MM/dd"));
        System.out.println(new LocalTime().toString("HH:mm:ss"));
        System.out.println(new DateTime().year().get());
        System.out.println(new DateTime().monthOfYear().get());
        System.out.println(new DateTime().dayOfMonth().get());
        System.out.println(new DateTime().weekOfWeekyear().get());
        System.out.println(new DateTime().dayOfWeek().get());
        System.out.println(new DateTime().hourOfDay().get());
        System.out.println(new DateTime().minuteOfHour().get());
        System.out.println(new DateTime().plusYears(1));
        System.out.println(new DateTime().plusMonths(1));
        System.out.println(new DateTime().plusWeeks(1));
        System.out.println(new DateTime().plusDays(1));
        System.out.println(new DateTime().plusHours(1));
        System.out.println(new DateTime().plusMinutes(1));

    }
}
