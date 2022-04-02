import API.CryptoAPI;
import Entity.CandleStick;
import Entity.TimeFrame;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CandleStickTest {

    @Test
    public void getFromCS () throws IOException, InterruptedException {
        String i = "BTC_USDT";
        CandleStick cS = CandleStick.getLatestElementFromGetCandleStick(CryptoAPI.getCandlestick(i, TimeFrame.ONE_MINUTE));
        System.out.println(cS.toString());
    }
}
