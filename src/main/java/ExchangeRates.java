import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;


public class ExchangeRates {
    public static Map<String,Double> getCurrentExchangeRateFromOnlineSource() {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpEntity entity;
        CloseableHttpResponse resp;
        String currencyRatesJson = null;
        Map<String, Double> map;
        try {
            resp = client.execute(new HttpGet("http://api.fixer.io/latest"));

            entity = resp.getEntity();


            currencyRatesJson = IOUtils.toString(entity.getContent(), "cp1251");

        } catch (UnknownHostException e){
            System.out.println("UnknownHostException caught");
            currencyRatesJson = defaultCurrData();
        } catch (IOException e) {
            System.out.println("IOException caught");
        }
        catch (NullPointerException e) {
            System.out.println("NullPointerException caught");
            currencyRatesJson = defaultCurrData();
        }
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        CurrencyRates cr;

        cr = gson.fromJson(currencyRatesJson, CurrencyRates.class);

        map = cr.getRates();
        map.put("EUR", 1.0);


        return map;
    }

    private static String defaultCurrData() {
        return "{\n" +
                "    \"base\": \"EUR\",\n" +
                "    \"date\": \"2017-05-26\",\n" +
                "    \"rates\": {\n" +
                "        \"AUD\": 1.5038,\n" +
                "        \"BGN\": 1.9558,\n" +
                "        \"BRL\": 3.6609,\n" +
                "        \"CAD\": 1.5077,\n" +
                "        \"CHF\": 1.0888,\n" +
                "        \"CNY\": 7.6782,\n" +
                "        \"CZK\": 26.434,\n" +
                "        \"DKK\": 7.4415,\n" +
                "        \"GBP\": 0.8719,\n" +
                "        \"HKD\": 8.7249,\n" +
                "        \"HRK\": 7.424,\n" +
                "        \"HUF\": 307.4,\n" +
                "        \"IDR\": 14892,\n" +
                "        \"ILS\": 4.0008,\n" +
                "        \"INR\": 72.218,\n" +
                "        \"JPY\": 124.38,\n" +
                "        \"KRW\": 1251.8,\n" +
                "        \"MXN\": 20.684,\n" +
                "        \"MYR\": 4.779,\n" +
                "        \"NOK\": 9.4018,\n" +
                "        \"NZD\": 1.5856,\n" +
                "        \"PHP\": 55.729,\n" +
                "        \"PLN\": 4.183,\n" +
                "        \"RON\": 4.5568,\n" +
                "        \"RUB\": 63.797,\n" +
                "        \"EUR\": 1.0,\n" +
                "        \"SEK\": 9.7275,\n" +
                "        \"SGD\": 1.5479,\n" +
                "        \"THB\": 38.122,\n" +
                "        \"TRY\": 3.9963,\n" +
                "        \"USD\": 1.1196,\n" +
                "        \"ZAR\": 14.411\n" +
                "    }\n" +
                "}";
    }
}
