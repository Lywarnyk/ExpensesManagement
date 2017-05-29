import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class UtilityDataTest {
    public static Map<String, Double> exchangeRates;
    public static TreeMap <Date, ArrayList<Expenses>> expectedResultExpensesMap;
    public static TreeMap <Date, ArrayList<Expenses>> actualResultExpensesMap;
    @BeforeAll
    public static void setUpForUtilityDateTests(){
        exchangeRates = new HashMap<>();
        exchangeRates.put("EUR", 1.0);
        exchangeRates.put("USD", 2.0);

        //creating result map which will be processing
        actualResultExpensesMap = new TreeMap<>();
        ArrayList<Expenses> list = new ArrayList<>();
        list.add(new Expenses(3.0, "USD", "Jam"));
        actualResultExpensesMap.put(UtilityData.parsingStringToDate("2017-10-10"), list);
        ArrayList<Expenses> secondList = new ArrayList<>();
        secondList.add(new Expenses(10.0, "EUR", "Apple juice"));
        actualResultExpensesMap.put(UtilityData.parsingStringToDate("2017-10-11"),secondList);

        //creating actual result map
        expectedResultExpensesMap = new TreeMap<>(actualResultExpensesMap);


    }
    @Test
    public void totalSumByRequestedCurrencyCalculatorTestEUR() {
        String expectedResult = "11,50 EUR";
        String actualResult  = UtilityData.totalSumByRequestedCurrencyCalculator("EUR",exchangeRates,actualResultExpensesMap);
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void totalSumByRequestedCurrencyCalculatorTestUSD() {
        String expectedResult = "23,00 USD";
        String actualResult  = UtilityData.totalSumByRequestedCurrencyCalculator("USD",exchangeRates,actualResultExpensesMap);
        assertEquals(expectedResult, actualResult);

    }
    @Disabled
    @Test
    void addExpensesToMapTest() {

        String[] splitCommands = {"add","2020-12-12","50","USD", "Cheese"};
        ArrayList<Expenses> actualList = new ArrayList<>();
        actualList.add(new Expenses(50, "USD", "Cheese"));
        expectedResultExpensesMap.put(UtilityData.parsingStringToDate("2020-12-12"), actualList );
        UtilityData.addExpensesToMap(splitCommands, exchangeRates, actualResultExpensesMap);
        assertEquals(expectedResultExpensesMap, actualResultExpensesMap);


    }

    @Test
    void clearListByDateTest() {
        String[] splitCommands = {"clear","2017-10-10"};
        UtilityData.clearListByDate(splitCommands, actualResultExpensesMap);
        expectedResultExpensesMap.remove(UtilityData.parsingStringToDate(splitCommands[1]));
        assertEquals(expectedResultExpensesMap, actualResultExpensesMap);
    }

    @AfterAll
    public static void tearDownForUtilityTests(){
        expectedResultExpensesMap = null;
        actualResultExpensesMap = null;
    }

}