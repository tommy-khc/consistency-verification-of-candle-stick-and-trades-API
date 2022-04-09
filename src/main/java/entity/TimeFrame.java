package entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum TimeFrame {

    ONE_MINUTE("1m", 60000L),
    FIVE_MINUTE("5m", 300000L),
    FIFTEEN_MINUTE("15m", 900000L),
    THIRTY_MINUTE("30m", 1800000L),
    ONE_HOUR("1h", 3600000L),
    FOUR_HOUR("4h", 14400000L),
    SIX_HOUR("6h", 21600000L),
    TWELEVE_HOUR("12h", 43200000L),
    ONE_DAY("1D", 86400000L),
    ONE_WEEK("7D", 604800000L),
    TWO_WEEKS("14D", 1210000000L),
    ONE_MONTH("1M", 2629800000L);

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
