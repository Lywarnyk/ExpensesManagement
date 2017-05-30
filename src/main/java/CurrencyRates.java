import java.util.HashMap;
import java.util.Map;


public class CurrencyRates {
    private String base;
    private String date;
    private Map<String, Double> rates = new HashMap<String, Double>();



    public CurrencyRates(String date, Map<String, Double> currencyData, String base){
        this.date = date;
        this.rates = currencyData;
        this.base= base;

    }

    public Map<String,Double> getRates() {
        return rates;
    }


}
