import java.util.*;


public class Main {
    public static void main(String[] args) {

        Map<String,  Double> exchangeRatesMap = ExchangeRates.getCurrentExchangeRateFromOnlineSource();
        Map<Date,ArrayList<Expenses>> expensesMap;
        if (!(UtilityData.getMapFromFile() == null)){
         expensesMap = new TreeMap<>(UtilityData.getMapFromFile());
        }
        else {expensesMap = new TreeMap<>();}
        Scanner sc = new Scanner(System.in);

        while (true) {
            String commandLine = sc.nextLine();
            if (commandLine.equals("stop")) break;
            CommandProcessor.chooseCommand(commandLine, exchangeRatesMap, expensesMap);
            UtilityData.saveMapToFile(expensesMap);
        }

    }


}
