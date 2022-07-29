package il.cshaifasweng.OCSFMediatorExample.server.Helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RefundCheck {

    public int Refund (String Date , String Time)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Date += " " + Time;
        LocalDateTime RemainingTime = LocalDateTime.parse(Date, formatter);
        LocalDateTime TimeNow = LocalDateTime.now().plusHours(3);
        if (TimeNow.isBefore(RemainingTime))
            return 3;
        TimeNow = LocalDateTime.now().plusHours(1);
        if(TimeNow.isBefore(RemainingTime))
            return 1;
        return 0;
    }

    public int Delivery(String Date, String Time)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Date += " " + Time;
        LocalDateTime RemainingTime = LocalDateTime.parse(Date, formatter);
        LocalDateTime TimeNow = LocalDateTime.now();
        if (TimeNow.isBefore(RemainingTime))
        {
            return 0;
        }
        else {
            return 1;
        }
    }

}
