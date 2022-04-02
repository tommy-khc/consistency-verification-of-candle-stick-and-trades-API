import API.CryptoAPI;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CryptoAPITest {

    @Test
    public void getTrade() throws IOException,InterruptedException {
        String i = "BTC_USDT";
        CryptoAPI.getTrade(i);
    }

    //Wrong Instrument Name
    @org.junit.Test
    public void getTradeInstrumentName() throws IOException, InterruptedException {
        String i = "all";
        CryptoAPI.getTrade(i);
    }

}
