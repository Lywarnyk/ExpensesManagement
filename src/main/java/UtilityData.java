import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class UtilityData {
    public static void totalSumByRequestedCurrency(String[] splitCommands, Map<String, Double> exchangeRatesMap, Map<Date, ArrayList<Expenses>> expensesMap) {

        if (splitCommands.length< 2) {
            System.out.println("Invalid command format");
            return;
        }
        if (expensesMap.isEmpty()) {
            System.out.println("You have no expenses yet");
            return;
        }

        String requestedCurrency = splitCommands[1];
        double sum = 0.0 ;
        double rate;

        if (ifRequestedCurrencyExists(requestedCurrency, exchangeRatesMap)){
            rate = exchangeRatesMap.get(requestedCurrency);
        }
        else {
            System.out.println("Invalid currency format");
            return;
        }
        for (Map.Entry<Date, ArrayList<Expenses>> pair : expensesMap.entrySet()){
            for (Expenses e : pair.getValue()){
                double amount = e.getAmount();
                String currentExchangeRateByCurrency = e.getCurrency();
                sum+= amount / exchangeRatesMap.get(currentExchangeRateByCurrency)*rate;
            }
        }


        System.out.println(String.format("%(.2f", sum)+" "+requestedCurrency);
    }



    public static void showListExpenses(Map<Date, ArrayList<Expenses>> expensesMap) {
        if (expensesMap.isEmpty()){
            System.out.println("You have no expenses yet.");
            return;
        }
        for(Map.Entry<Date,ArrayList<Expenses>> pair: expensesMap.entrySet()){
            Date date = pair.getKey();
            printDate(date);
            for (Expenses e : pair.getValue()) {
                System.out.println(e);
            }
            System.out.println("");
        }
    }

    private static void printDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(date));
    }

    public static void addExpensesToMap(String[] splitCommands, Map<String, Double> exchangeRatesMap, Map<Date, ArrayList<Expenses>> expensesMap) {
        if (splitCommands.length<5){
            System.out.println("Invalid command format");
            return;
        }
        Date dateOfExpense;
        double amount;
        try {
            dateOfExpense = parsingStringToDate(splitCommands[1]);
            amount = parsingStringToAmount(splitCommands[2]);
        } catch (NullPointerException e) {
            return;
        }
        if (amount < 0) {
            System.out.println("Invalid Amount format");
            return;
        }
        if(!ifRequestedCurrencyExists(splitCommands[3], exchangeRatesMap)){
            System.out.println("Invalid currency format");
            return;
        }
        String product="";
        if (splitCommands.length>5){
            for (int i = 4; i<splitCommands.length;i++){
                product += splitCommands[i]+ " ";

            }
        }
        else product = splitCommands[4];

        if (expensesMap.containsKey(dateOfExpense))
            expensesMap.get(dateOfExpense).add(new Expenses(amount, splitCommands[3],product));
        else {
            expensesMap.put(dateOfExpense, new ArrayList<Expenses>());
            expensesMap.get(dateOfExpense).add(new Expenses(amount, splitCommands[3],product));
        }

    }

    private static boolean ifRequestedCurrencyExists(String splitCommand, Map<String, Double> exchangeRatesMap) {
        return exchangeRatesMap.containsKey(splitCommand);
    }

    private static double parsingStringToAmount(String splitCommand) {
        double amount;
        try {
            amount = Double.parseDouble(splitCommand);
            return amount;

        } catch (NumberFormatException e) {
            System.out.println("Invalid Amount format");
            return -1;
        }
    }

    public static void clearListByDate(String[] splitCommands, Map<Date, ArrayList<Expenses>> expensesMap){

        Date dateToDelete;
        if (expensesMap.isEmpty()) {
            System.out.println("You have no expenses yet.");
            return;
        }

        try {
            dateToDelete = parsingStringToDate(splitCommands[1]);
        } catch (NullPointerException e) {
            return;
        }

        if(expensesMap.containsKey(dateToDelete))
            expensesMap.remove(dateToDelete);
        else {
            System.out.println("You have no expenses on this date");
        }
    }

    private static Date parsingStringToDate(String splitCommand)  {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;

        try {
            date = sdf.parse(splitCommand);
            return date;
        } catch (ParseException e) {
            System.out.println("Incorrect date format");
            return null;
        }


    }
}
