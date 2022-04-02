import API.CryptoAPI;
import Data.DataExtraction;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DataExtractTest {

    @Test
    public void getLatestFieldValue() throws IOException, InterruptedException {
        String i = "BTC_USDT";
        JSONArray arr = CryptoAPI.getTrade(i);
        DataExtraction.getLatestFieldValue("getTrade", arr, "t");
    }

    @Test
    public void getLatestFieldValueMethodName() throws IOException, InterruptedException {
        String i = "BTC_USDT";
        JSONArray arr = CryptoAPI.getTrade(i);
        DataExtraction.getLatestFieldValue("sss", arr, "t");
    }

    @Test
    public void getLatestFieldValueFieldName() throws IOException, InterruptedException {
        String i = "BTC_USDT";
        JSONArray arr = CryptoAPI.getTrade(i);
        DataExtraction.getLatestFieldValue("getTrade", arr, "ss");
    }


}
