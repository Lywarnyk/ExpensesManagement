import java.util.*;

public class Main {
    public static void main(String[] args) {

        Map<String,  Double> exchangeRatesMap = ExchangeRates.getCurrentExchangeRateFromOnlineSource();
        Map<Date,ArrayList<Expenses>> expensesMap = new TreeMap<>();
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            String commandLine = sc.nextLine();
            running = CommandProcessing.chooseCommand(commandLine, exchangeRatesMap, expensesMap);
        }

    }


}
