package api;

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

public class POSTAPI {

    private final static Logger logger = LogManager.getLogger(POSTAPI.class);

    public static HttpResponse<String> getResponse (String postEndpoint) throws IOException, InterruptedException {

        if (postEndpoint == null) {
            logger.error("postEndpoint == null");
            return null;
        }

        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create(postEndpoint))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        logger.debug(response);
        return response;
    }

    public static JSONArray getData (HttpResponse<String> response) {

        if (response == null) {
            logger.error("getData, " + "response == null");
            return null;
        }

        String body = response.body();
        logger.info("getData, " + "body: " + body);

        Object o = JSONValue.parse(body);
        JSONObject j = (JSONObject) o;
        Object result = j.get("result");
        JSONObject jsonResult = (JSONObject) result;
        try {
            Object data = jsonResult.get("data");
            JSONArray dataArray = (JSONArray) data;
            logger.info("getData, " + "return: " + dataArray);
            return dataArray;
        }
        catch (NullPointerException e) {
            logger.error("getData, " + "NullPointerException, " + "return: " + null);
            return null;
        }

    }

    public static Boolean verifyHTTPStaus (HttpResponse<String> response) {

        if (response == null) {
            logger.error("response == null");
            return Boolean.FALSE;
        }

        int HTTPStaus = response.statusCode();
        logger.info("HTTPStaus: " + HTTPStaus);

        //200 means success or partial success
        if (HTTPStaus != 200) {
            logger.error("Wrong HTTPStatus");
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
