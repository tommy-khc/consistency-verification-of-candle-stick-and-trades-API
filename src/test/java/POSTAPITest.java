import api.POSTAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpResponse;

public class POSTAPITest {

    private static final Logger logger = LogManager.getLogger(POSTAPITest.class);

    @Test
    public void checkPriceFromResponse () throws IOException, InterruptedException {
        String postEndpoint = "https://api.crypto.com/v2/public/get-trades?instrument_name=BTC_USDT";
        HttpResponse<String> response = POSTAPI.getResponse(postEndpoint);
        String body = response.body();
        logger.debug("response class type: " + body.getClass());
        logger.debug(body);
    }
}
