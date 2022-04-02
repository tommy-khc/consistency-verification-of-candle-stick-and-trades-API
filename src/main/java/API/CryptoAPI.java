package API;

import Data.DataExtraction;
import Entity.CandleStick;
import Entity.TimeFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.TimerTask;

public class CryptoAPI extends TimerTask {

    private final static Logger logger = LogManager.getLogger(CryptoAPI.class);

    private String methodName;
    
    private final String instrumentName;

    private final long intTime;

    private final long endTime;

    private final TimeFrame tF;

    private CandleStick cS;

    private Boolean finishPolling;

    public CryptoAPI(String instrumentName, long intTime, TimeFrame tF, String methodName) {
        this.instrumentName = instrumentName;
        this.intTime = intTime;
        this.tF = tF;
        this.endTime = intTime + tF.getDuration();
        this.methodName = methodName;
        this.cS = new CandleStick(methodName);
        this.finishPolling = Boolean.FALSE;
    }

    public CandleStick getcS() {
        return cS;
    }

    public Boolean getFinishPolling() {
        return finishPolling;
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
        //TODO dealing the latest element only in arr is much easier but it will be more bugs
        //TODO Too Complex? But trading record speed is faster than 0.01 which is the speed of sending response
        try {
            //Assume the latency is zero
            JSONArray arr = getTrade(instrumentName);

            //Time
            long latestTime = (long) DataExtraction.getLatestFieldValue("getTrade", arr, "t");
            long oldestTime = (long) DataExtraction.getOldestFieldValue("getTrade", arr, "t");
            long timeDiffLast = latestTime - intTime;
            long timeDiffOld = oldestTime - intTime;
            long duration = tF.getDuration();


            cS.setH( DataExtraction.getHighFromTrades(arr, intTime, endTime, cS.getH()));
            logger.info("high price is updated to " + cS.getH());

            cS.setL( DataExtraction.getLowFromTrades(arr, intTime, endTime, cS.getL()));
            logger.info("low price is updated to " + cS.getL());

            cS.setV( DataExtraction.getVolumeFromTrades(arr, intTime, endTime, cS.getV()));
            logger.info("volume is updated to " + cS.getL());

            //TODO fix bugs (due to delay of receiving request in sever ) -- send earlier, then tF.getDuration() + e.g 500 ?
            //TODO No really, because responses are delayed
            //timeDiffLast > tF.getDuration() && timeDiffOld > tF.getDuration()
            if (timeDiffOld > duration) {
                cS.setT(latestTime);
                logger.info("run(), " + cS.getTradeToString());
                cancel();
                finishPolling = Boolean.TRUE;
            }

            //codes below will be executed even it executed cancel()
            //TODO H is similar
            if (timeDiffOld <= duration) {
                //Diff <= 6000
                //determine field o
                if (Double.isNaN(cS.getO())) {
                    cS.setO(DataExtraction.getOpenPriceFromTrades(arr, intTime));
                    logger.info("open price is updated to " + cS.getO());
                }

                //determine field c
                cS.setC( DataExtraction.getClosePriceFromTrades(arr, timeDiffLast, timeDiffOld, duration, endTime, cS.getC()));
                logger.info("close price is updated to " + cS.getC());

                if (timeDiffLast == tF.getDuration()) {
                    cS.setT(latestTime);
                    cS.setC( (double) DataExtraction.getLatestFieldValue("getTrade", arr, "p") );
                    logger.info("close price is updated to " + cS.getC());
                    logger.info("run(), " + cS.getTradeToString());
                    cancel();
                    finishPolling = Boolean.TRUE;
                }

                if (timeDiffLast > duration && timeDiffOld < duration) {
                    //Aim: cS.setT(latestTime which is closest to )
                    cS.setT( (long) DataExtraction.getCloestToEndTimeFieldFromTrade(arr, endTime, "t"));
                    logger.info("run(), " + cS.getTradeToString());
                    cancel();
                    finishPolling = Boolean.TRUE;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
