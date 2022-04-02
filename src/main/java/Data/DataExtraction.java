package Data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataExtraction {

    private static final Logger logger = LogManager.getLogger(DataExtraction.class);

    //TODO Too complex?

    // methodName is the method name in CryptoAPI
    public static Object getLatestFieldValue (String methodName, JSONArray arr, String fieldName) {

        if (arr == null) {
            logger.error("getLatestFieldValue, trades == null");
            return null;
        }

        if (fieldName == null) {
            logger.error("getLatestFieldValue, fieldName == null");
            return null;
        }

        //TODO create a method to replace it ?
        if (methodName.equals("getTrade")) {

            if (!fieldName.equals("d") &&
                    !fieldName.equals("s") &&
                    !fieldName.equals("p") &&
                    !fieldName.equals("q") &&
                    !fieldName.equals("t") &&
                    !fieldName.equals("i")) {
                logger.error("getLatestFieldValue,, fieldName is invalid");
                return null;
            }
        } else if (methodName.equals("getCandlestick")) {

            if (!fieldName.equals("t") &&
                    !fieldName.equals("o") &&
                    !fieldName.equals("h") &&
                    !fieldName.equals("l") &&
                    !fieldName.equals("c") &&
                    !fieldName.equals("v")) {
                logger.error("getLatestFieldValue, fieldName is invalid");
                return null;
            }
        } else {
            logger.error("getLatestFieldValue, methodName is invalid");
            return null;
        }

        Object latestO = arr.get(0);
        JSONObject latestJ = (JSONObject) latestO;
        Object o = (Object) latestJ.get(fieldName);

        logger.debug("getLatestFieldValue from " + methodName +", "+ fieldName + ", return: " + "(DataType = Object): " + o);
        return o;

    }

    public static Object getOldestFieldValue (String methodName, JSONArray arr, String fieldName) {

        if (arr == null) {
            logger.error("getOldestFieldValue, trades == null");
            return null;
        }

        if (fieldName == null) {
            logger.error("getOldestFieldValue, fieldName == null");
            return null;
        }

        //TODO create a method to replace it ?
        if (methodName.equals("getTrade")) {

            if (!fieldName.equals("d") &&
                    !fieldName.equals("s") &&
                    !fieldName.equals("p") &&
                    !fieldName.equals("q") &&
                    !fieldName.equals("t") &&
                    !fieldName.equals("i")) {
                logger.error("getOldestFieldValue,, fieldName is invalid");
                return null;
            }
        } else if (methodName.equals("getCandlestick")) {

            if (!fieldName.equals("t") &&
                    !fieldName.equals("o") &&
                    !fieldName.equals("h") &&
                    !fieldName.equals("l") &&
                    !fieldName.equals("c") &&
                    !fieldName.equals("v")) {
                logger.error("getOldestFieldValue, fieldName is invalid");
                return null;
            }
        } else {
            logger.error("getOldestFieldValue, methodName is invalid");
            return null;
        }

        int latestIndex = arr.size()-1;
        Object oldestO = arr.get(latestIndex);
        JSONObject oldestJ = (JSONObject) oldestO;
        Object o = (Object) oldestJ.get(fieldName);

        logger.debug("getOldestFieldValue from " + methodName +", "+ fieldName + ", return: " + "(DataType = Object): " + o);
        return o;
    }

    //make sure JSONArray trades is from getTrade
    public static double getOpenPriceFromTrades (JSONArray trades, long intTime) {

        if (trades == null) {
            logger.error("trades == null");
            return Double.NaN;
        }

        if (intTime == 0L) {
            logger.error("intTime == 0");
            return Double.NaN;
        }

        for (int i = trades.size() - 1; i >= 0; i--) {
            JSONObject j = (JSONObject) trades.get(i);
            long t = (long) j.get("t");
            if (t >= intTime) {
                double o = (double) j.get("p");
                logger.info("getOpenPriceFromTrades, return: " + o);
                return o;
            }
        }

        logger.info("send request early");
        return Double.NaN;
    }

    public static double getClosePriceFromTrades (JSONArray trades, long timeDiffLast, long timeDiffOld,
                                                  long duration, long endTime, double currentClosePrice) {

        if (trades == null) {
            logger.error("trades == null");
            return Double.NaN;
        }

        if (timeDiffLast < 0) {
            logger.info("send request early");
            return Double.NaN;
        }

        if (duration == -1L) {
            logger.error("ONE_MONTH is select");
            return Double.NaN;
        }

        if (timeDiffLast < duration) {
            return (double) DataExtraction.getLatestFieldValue("getTrade", trades, "p");
        }

//        if (timeDiffLast == duration), do it run()

        // part of the time interval of JSONArray trades is overlapped with intTime-EndTime interval, part of it is not
        if (timeDiffLast > duration && timeDiffOld < duration) { //get max. time
            return (double) DataExtraction.getCloestToEndTimeFieldFromTrade(trades, endTime, "p");
        }

        //        if (timeDiffLast == duration), do it run()
        logger.info("getClosePriceFromTrades, case: timeDiffLast == duration");
        return currentClosePrice;
    }

    //use it when timeDiffLast > duration && timeDiffOld < duration
    public static Object getCloestToEndTimeFieldFromTrade (JSONArray trades, long endTime, String fieldName) {

        if (trades == null) {
            logger.error("trades == null");
            return null;
        }

        if (endTime < 0) {
            logger.error("endTime < 0");
            return null;
        }

        if (fieldName == null) {
            logger.error("fieldName == null");
            return null;
        }

        for (Object o : trades) {
            JSONObject j = (JSONObject) o;
            long t = (long) j.get("t");
            if (t <= endTime) {
                return j.get(fieldName);
            } //else (t > endTime)
        }

        logger.error("getCloestToEndTimeFieldFromTrade, used wrongly");
        return null;
    }

    public static double getHighFromTrades (JSONArray arr, long intTime, long endTime, double h) {

        if (arr == null) {
            logger.error("arr == null");
            return Double.NaN;
        }

        // intTime is from "2022-04-2 00:54:00.0" , it will be checked in other code
        //endTime = intTime + duration, it should be ok

        //Initial value of h is Double.MIN_VALUE
        if (h == Double.NaN) {
            logger.error("h == Double.NaN"); // properly because of arr == null
            return h;
        }

        for (Object o : arr) {
            JSONObject j = (JSONObject) o;
            long t = (long) j.get("t");
            if (intTime <= t && t <= endTime) {
                h = Double.max(h, (double) j.get("p"));
            }
        }

        return h;
    }
    
    public static double getLowFromTrades (JSONArray arr, long intTime, long endTime, double l) {
        if (arr == null) {
            logger.error("arr == null");
            return Double.NaN;
        }

        // intTime is from "2022-04-2 00:54:00.0" , it will be checked in other code
        //endTime = intTime + duration, it should be ok

        //Initial value of l is Double.MAX_VALUE
        if (l == Double.NaN) {
            logger.error("l == Double.NaN"); // properly because of arr == null
            return l;
        }

        for (Object o : arr) {
            JSONObject j = (JSONObject) o;
            long t = (long) j.get("t");
            if (intTime <= t && t <= endTime) {
                l = Double.min(l, (double) j.get("p"));
            }
        }

        return l;
    }

}
