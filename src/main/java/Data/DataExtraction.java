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

        logger.info("getLatestFieldValue from " + methodName +", "+ fieldName + ", return: " + "(DataType = Object): " + o);
        return o;

    }
}
