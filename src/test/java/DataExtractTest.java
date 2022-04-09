import api.CryptoAPI;
import businessserivce.Polling;
import data.DataExtraction;
import entity.CandleStick;
import entity.TimeFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataExtractTest {

    private final static Logger logger = LogManager.getLogger(DataExtractTest.class);

    @Test
    public void getLatestFieldValue() throws IOException, InterruptedException {
        String i = "BTC_USDT";
        JSONArray arr = CryptoAPI.getTrade(i);
        Object o = DataExtraction.getLatestFieldValue("getTrade", arr, "t");
        logger.info("getLatestFieldValue, return: " + o.toString());
    }

    @Test
    public void getLatestFieldValueMethodName() throws IOException, InterruptedException {
        String i = "BTC_USDT";
        JSONArray arr = CryptoAPI.getTrade(i);
        Object o = DataExtraction.getLatestFieldValue("sss", arr, "t");
        logger.info("getLatestFieldValue, return: " + o);
    }

    @Test
    public void getLatestFieldValueFieldName() throws IOException, InterruptedException {
        String i = "BTC_USDT";
        JSONArray arr = CryptoAPI.getTrade(i);
        Object o = DataExtraction.getLatestFieldValue("getTrade", arr, "ss");
        logger.info("getLatestFieldValue, return: " + o);
    }

    @Test
    public void getOldestFieldValue() throws IOException, InterruptedException {
        String i = "BTC_USDT";
        JSONArray arr = CryptoAPI.getTrade(i);
        Object o = DataExtraction.getOldestFieldValue("getTrade", arr, "t");
        logger.info("getLatestFieldValue, return: " + o.toString());
    }

    @Test
    public void getOldestFieldValueMethodName() throws IOException, InterruptedException {
        String i = "BTC_USDT";
        JSONArray arr = CryptoAPI.getTrade(i);
        Object o = DataExtraction.getOldestFieldValue("sss", arr, "t");
        logger.info("getOldestFieldValue, return: " + o);
    }

    @Test
    public void getOldestFieldValueFieldName() throws IOException, InterruptedException {
        String i = "BTC_USDT";
        JSONArray arr = CryptoAPI.getTrade(i);
        Object o = DataExtraction.getOldestFieldValue("getTrade", arr, "ss");
        logger.info("getOldestFieldValue, return: " + o);
    }

    @Test
    public void getOpenPriceFromTrades() throws IOException, InterruptedException, ParseException {
        String i = "BTC_USDT";
        JSONArray arr = CryptoAPI.getTrade(i);
        String dateStr = "2022-04-2 17:59:00.0";
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(dateStr);
        long intTime = date.getTime();
        Double o = DataExtraction.getOpenPriceFromTrades(arr, intTime);
        logger.info("getOpenPriceFromTrades, return: " + o);
    }

    //Not enough Time
    @Test
    public void remainingMethods() throws IOException, InterruptedException, ParseException {
        String i = "BTC_USDT";
        String dateStr = "2022-04-2 20:01:00.0";
        TimeFrame tF = TimeFrame.ONE_MINUTE;
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(dateStr);
        CandleStick trade = Polling.getTrade(i, date, tF);
        CandleStick cS = CandleStick.getLatestElementFromGetCandleStick(CryptoAPI.getCandlestick(i, tF));
        logger.info("getOpenPriceFromTrades, return: " + cS);
    }
}
