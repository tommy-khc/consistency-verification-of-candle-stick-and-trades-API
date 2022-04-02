import API.CryptoAPI;
import BusinessSerivce.Polling;
import Entity.CandleStick;
import Entity.TimeFrame;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws ParseException, IOException, InterruptedException {
        //user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Instrument Code?");
        String i = sc.nextLine();
        System.out.println("Start time? (\"yyyy-MM-dd HH:mm:ss.S\") ?");
        String dateStr = sc.nextLine();
        System.out.println("Time frame? (i.e. \"1m”, ”15m”, “30m”, “1h”, “4h”, “6h”, “12h”, “1D”, “7D”, “14D”, “1M\")");
        String tFStr = sc.nextLine();
        System.out.println("Please wait. It will start at start time.");

        TimeFrame tF = TimeFrame.getInstance(tFStr);
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(dateStr);



        //Test five CandleSticks
        List<String> strL = new ArrayList<>();
        for (int k : new int[5]) {
            CandleStick trade = Polling.getTrade(i, date, tF);

            CandleStick cS = CandleStick.getLatestElementFromGetCandleStick(CryptoAPI.getCandlestick(i, tF));

            String consistence = "**********CandleStick is consistence with getTrade: " + cS.equals(trade);
            System.out.println(consistence);

            if (!cS.equals(trade)) {

                String getTradeToStr = "**********CandleStick of getTrade: " + trade.getTradeToString();
                String getCandleStickToStr = "*******************getCandleStick: " + cS.toString();

                System.out.println(getTradeToStr);
                System.out.println(getCandleStickToStr);

                strL.add(getTradeToStr);
                strL.add(getCandleStickToStr);
            }

            strL.add(consistence);

            Thread.sleep(1000);
        }

        for (String str : strL) {
            System.out.println(str);
        }

        Thread.interrupted();
    }
}
