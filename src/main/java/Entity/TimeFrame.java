package Entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum TimeFrame {

    ONE_MINUTE("1m", 60000),
    FIVE_MINUTE("5m", 300000),
    FIFTEEN_MINUTE("15m", 900000),
    THIRTY_MINUTE("30m", 1800000),
    ONE_HOUR("1h", 3600000),
    FOUR_HOUR("4h", 14400000),
    SIX_HOUR("6h", 21600000),
    TWELEVE_HOUR("12h", 43200000),
    ONE_DAY("1D", 86400000),
    ONE_WEEK("7D", 604800000),
    TWO_WEEKS("14D", 1210000000),
    ONE_MONTH("1M", -1); //2,628,000,000 is not accurate and it is too large for long

    private final String shortName;

    private final long duration;

    private TimeFrame(String s, long d) {
        this.shortName =s;
        this.duration=d;
    }

    public String getShortName() {
        return shortName;
    }

    public long getDuration() {
        return duration;
    }

    public static TimeFrame getInstance(String s) {
        for (TimeFrame tF : TimeFrame.values()) {
            if (tF.getShortName().equals(s)) {
                return tF;
            }
        }
        logger.error("invalid input");
        return null;
    }

    private static final Logger logger = LogManager.getLogger(TimeFrame.class);
}
