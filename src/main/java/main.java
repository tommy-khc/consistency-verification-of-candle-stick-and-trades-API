import BusinessSerivce.Polling;
import Entity.TimeFrame;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class main {

    public static void main(String[] args) throws ParseException {
        String i = "BTC_USDT";
        String dateStr = "2022-04-2 12:38:00.0";
        TimeFrame tF = TimeFrame.ONE_MINUTE;
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(dateStr);
        Polling.getTrade(i, date, tF);
    }
}
