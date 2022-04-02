package BusinessSerivce;

import API.CryptoAPI;
import Entity.TimeFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.Timer;

public class Polling {

    private final static Logger logger = LogManager.getLogger(Polling.class);

    public static void callRepeatedly (String instrument, Date intTime, TimeFrame tF) {

        if (instrument == null) {
            logger.error("instrument == null");
        }

        if (intTime == null) {
            logger.error("intTime == null");
        }

        if (tF == null) {
            logger.error("tF == null");
        }

        Timer t = new Timer();
        CryptoAPI trade = new CryptoAPI(instrument, intTime.getTime(), tF);
        t.scheduleAtFixedRate(trade, intTime, 1L);
    }
}
