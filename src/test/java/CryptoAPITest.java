import API.CryptoAPI;
import Entity.TimeFrame;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CryptoAPITest {

    @Test
    public void getTrade() throws IOException,InterruptedException {
        String i = "BTC_USDT";
        CryptoAPI.getTrade(i);
    }

    //Wrong Instrument Name
    @Test
    public void getTradeInstrumentName() throws IOException, InterruptedException {
        String i = "all";
        CryptoAPI.getTrade(i);
    }

    @Test
    public void getCandlestick() throws IOException, InterruptedException {
        String i = "BTC_USDT";
        TimeFrame tF = TimeFrame.ONE_MINUTE;
        CryptoAPI.getCandlestick(i, tF);
    }

    @Test
    public void getCandlestickInstrumentName() throws IOException, InterruptedException {
        String i = "BTC_USDTsss";
        TimeFrame tF = TimeFrame.ONE_MINUTE;
        CryptoAPI.getCandlestick(i, tF);
    }

    @Test
    public void getCandlestickTimeFrame() throws IOException, InterruptedException {
        String i = "BTC_USDTsss";
        TimeFrame tF = TimeFrame.ONE_MINUTE;
        CryptoAPI.getCandlestick(i, null);
    }

    @Test
    public void getCandlestickOneMonthTimeFrame() throws IOException, InterruptedException {
        String i = "BTC_USDTsss";
        TimeFrame tF = TimeFrame.ONE_MONTH;
        CryptoAPI.getCandlestick(i, tF);
    }

}
