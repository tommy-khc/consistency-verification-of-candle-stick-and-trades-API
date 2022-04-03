import API.CryptoAPI;
import Entity.CandleStick;
import Entity.TimeFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CandleStickTest {

    private final static Logger logger = LogManager.getLogger(CandleStickTest.class);

    @Test
    public void getFromCS () throws IOException, InterruptedException {
        String i = "BTC_USDT";
        CandleStick cS = CandleStick.getLatestElementFromGetCandleStick(CryptoAPI.getCandlestick(i, TimeFrame.ONE_MINUTE));
        logger.info(cS.toString());
    }
}
