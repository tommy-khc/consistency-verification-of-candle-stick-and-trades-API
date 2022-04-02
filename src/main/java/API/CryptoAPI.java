package API;

import Entity.TimeFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CryptoAPI {

    private final static Logger logger = LogManager.getLogger(CryptoAPI.class);

    private String instrumentName;

    private long intTime;

    private TimeFrame tF;

    public CryptoAPI(String instrumentName, long intTime, TimeFrame tF) {
        this.instrumentName = instrumentName;
        this.intTime = intTime;
        this.tF = tF;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public long getIntTime() {
        return intTime;
    }

    public void setIntTime(long intTime) {
        this.intTime = intTime;
    }

    public TimeFrame getTF() {
        return tF;
    }

    public void setTF(TimeFrame tF) {
        this.tF = tF;
    }

    public static JSONArray getTrade (String instrumentName) throws IOException, InterruptedException {

        if (instrumentName == null) {
            logger.error("No instrument name");
            return null;
        }

        String postEndpoint = "https://api.crypto.com/v2/public/get-trades?instrument_name=" + instrumentName;

        HttpResponse<String> response = POSTAPI.getResponse(postEndpoint);

        if (!POSTAPI.verifyHTTPStaus(response)) {
            return null;
        }

        return POSTAPI.getData(response);

    }

    public static JSONArray getCandlestick (String instrumentName, TimeFrame tF) throws IOException, InterruptedException {

        if (instrumentName == null) {
            logger.error("No instrument name");
            return null;
        }

        if (tF == null) {
            logger.error("tF == null");
            return null;
        }

//        https://{URL}/v2/public/get-candlestick?instrument_name=BTC_USDT&timeframe=5m
//        String postEndpoint = "https://api.crypto.com/v2/public/get-candlestick?instrument_name=" + instrumentName + "&timeframe=" + tF.getValues() + "&depth=" + 50;
        String postEndpoint = "https://api.crypto.com/v2/public/get-candlestick?instrument_name=" + instrumentName + "&timeframe=" + tF.getShortName();

        HttpResponse<String> response = POSTAPI.getResponse(postEndpoint);

        if (!POSTAPI.verifyHTTPStaus(response)) {
            return null;
        }

        return POSTAPI.getData(response);

    }

}
