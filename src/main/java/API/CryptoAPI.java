package API;

import Data.DataExtraction;
import Entity.CandleStick;
import Entity.TimeFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.TimerTask;

public class CryptoAPI extends TimerTask {

    private final static Logger logger = LogManager.getLogger(CryptoAPI.class);

    private String instrumentName;

    private long intTime;

    private TimeFrame tF;

    private CandleStick cS;

    public CryptoAPI(String instrumentName, long intTime, TimeFrame tF) {
        this.instrumentName = instrumentName;
        this.intTime = intTime;
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

    @Override
    public void run() {
        try {
            //Assume the latency is zero
            JSONArray arr = getTrade(instrumentName);

            //Time
            long latestTime = (long) DataExtraction.getLatestFieldValue("trade", arr, "t");
            long timeDiff = latestTime - intTime;

            if (timeDiff > tF.getDuration()) {
                logger.info("run(), " + cS.toString());
                cancel();
            }


            //Diff <= 6000
            if (Double.isNaN(cS.getO())) {
                cS.setO(DataExtraction.getOpenPriceFromTrades(arr, intTime));
                logger.info("open price is updated to " + cS.getO());
            }

            //determine field c -- always get the latest price
            cS.setC( (double) DataExtraction.getLatestFieldValue("trade", arr, "p") );
            logger.info("close price is updated to " + cS.getC());


            if (timeDiff == 60000) {
                logger.info("run(), " + cS.toString());
                cancel();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
