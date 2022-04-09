import api.CryptoAPI;
import entity.TimeFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CryptoAPITest {

    private final static Logger logger = LogManager.getLogger(CryptoAPITest.class);

    @Test
    public void getTrade() throws IOException,InterruptedException {
        String i = "BTC_USDT";
        JSONArray arr = CryptoAPI.getTrade(i);
        logger.info("getTrade, return: " + arr.toString());
    }

    //Wrong Instrument Name
    @Test
    public void getTradeInstrumentName() throws IOException, InterruptedException {
        String i = "all";
        JSONArray arr = CryptoAPI.getTrade(i);
        logger.info("getTradeInstrumentName, return: " + arr.toString());
    }

    @Test
    public void getCandlestick() throws IOException, InterruptedException {
        String i = "BTC_USDT";
        TimeFrame tF = TimeFrame.ONE_MINUTE;
        JSONArray arr = CryptoAPI.getCandlestick(i, tF);
        logger.info("getCandlestick, return: " + arr.toString());
    }

    @Test
    public void getCandlestickInstrumentName() throws IOException, InterruptedException {
        String i = "BTC_USDTsss";
        TimeFrame tF = TimeFrame.ONE_MINUTE;
        JSONArray arr = CryptoAPI.getCandlestick(i, tF);
        logger.info("getCandlestickInstrumentName, return: " + arr);
    }

    @Test
    public void getCandlestickTimeFrame() throws IOException, InterruptedException {
        String i = "BTC_USDTsss";
        TimeFrame tF = TimeFrame.ONE_MINUTE;
        JSONArray arr = CryptoAPI.getCandlestick(i, null);
        logger.info("getCandlestickTimeFrame, return: " + arr);
    }

    @Test
    public void getCandlestickOneMonthTimeFrame() throws IOException, InterruptedException {
        String i = "BTC_USDTsss";
        TimeFrame tF = TimeFrame.ONE_MONTH;
        JSONArray arr = CryptoAPI.getCandlestick(i, tF);
        logger.info("getCandlestickOneMonthTimeFrame, return: " + arr);
    }

}
