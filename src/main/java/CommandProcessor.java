import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class CommandProcessor {
    public static void chooseCommand(String commandLine, Map<String, Double> exchangeRatesMap, Map<Date, ArrayList<Expenses>> expensesMap) {
        String[] splitCommands = commandLine.split(" ");

        switch (splitCommands[0]) {
            case "add":
                UtilityData.addExpensesToMap(splitCommands, exchangeRatesMap, expensesMap);
                break;
            case "list":
                UtilityData.showListExpenses(expensesMap);
                break;
            case "clear":
                UtilityData.clearListByDate(splitCommands, expensesMap);
                break;
            case "total":
                UtilityData.totalSumByRequestedCurrency(splitCommands, exchangeRatesMap, expensesMap);
                break;
            default:
                System.out.println("Invalid command");

        }

    }

}
