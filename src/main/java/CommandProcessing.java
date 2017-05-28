
import java.util.*;


public class CommandProcessing extends UtilityData {
    public static boolean chooseCommand(String commandLine, Map<String, Double> exchangeRatesMap, Map<Date, ArrayList<Expenses>> expensesMap) {
        String[] splitCommands = commandLine.split(" ");


        switch (splitCommands[0]) {
            case "add":
                addExpensesToMap(splitCommands, exchangeRatesMap, expensesMap);
                return true;
            case "list":
                showListExpenses(expensesMap);
                return true;
            case "clear":
                clearListByDate(splitCommands, expensesMap);
                return true;
            case "total":
                totalSumByRequestedCurrency(splitCommands, exchangeRatesMap, expensesMap);
                return true;
            case "stop":
                return false;
            default:
                System.out.println("Invalid command");
                return true;
        }

    }



}
