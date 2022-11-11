import api.CryptoAPI;
import businessserivce.Polling;
import entity.CandleStick;
import entity.TimeFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws ParseException, IOException, InterruptedException {

        //user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Instrument Code? (e.g. BTC_USDT) Go to https://exchange-docs.crypto.com/spot/index.html?java#public-get-instruments to find out more");
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
            strL.add(consistence);
            logger.info(consistence);

            if (!cS.equals(trade)) {

                String getTradeToStr = "**********CandleStick of getTrade: " + trade.getTradeToString();
                String getCandleStickToStr = "*******************getCandleStick: " + cS.toString();

                strL.add(getTradeToStr);
                strL.add(getCandleStickToStr);

                logger.info(getTradeToStr);
                logger.info(getCandleStickToStr);

            }

            date = new Date(date.getTime() + tF.getDuration());

            Thread.sleep(1000);
        }

        logger.info(i +"  "+ dateStr + "  " + tFStr);
        for (String str : strL) {
            logger.info(str);
        }

        Thread.interrupted();
    }
}
