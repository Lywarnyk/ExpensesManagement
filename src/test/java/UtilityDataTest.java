import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;


public class UtilityDataTest {

    public static Map<String, Double> exchangeRates;
    public static TreeMap<Date, ArrayList<Expenses>> expectedResultExpensesMap;
    public static TreeMap <Date, ArrayList<Expenses>> actualResultExpensesMap;

    @Before
    public void setUp() throws ParseException {
        exchangeRates = new HashMap<>();
        exchangeRates.put("EUR", 1.0);
        exchangeRates.put("USD", 2.0);

        //creating result map which will be processing
        actualResultExpensesMap = new TreeMap<>();
        ArrayList<Expenses> list = new ArrayList<>();
        list.add(new Expenses(3.0, "USD", "Jam"));
        actualResultExpensesMap.put(parsingStringToDate("2017-10-10"), list);
        ArrayList<Expenses> secondList = new ArrayList<>();
        secondList.add(new Expenses(10.0, "EUR", "Apple juice"));
        actualResultExpensesMap.put(parsingStringToDate("2017-10-11"),secondList);

        //creating actual result map
        expectedResultExpensesMap = new TreeMap<>(actualResultExpensesMap);
    }



    @Test
    public void totalSumByRequestedCurrencyCalculatorTest() {
        String expectedResult = "11,50 EUR";
        String actualResult  = UtilityData.totalSumByRequestedCurrencyCalculator("EUR",exchangeRates,actualResultExpensesMap);
        assertEquals(expectedResult, actualResult);
        expectedResult = "23,00 USD";
        actualResult  = UtilityData.totalSumByRequestedCurrencyCalculator("USD",exchangeRates,actualResultExpensesMap);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void addExpensesToMapTest() throws ParseException {
        Date date = parsingStringToDate("2020-12-12");
        String[] splitCommands = {"add","2020-12-12","50","USD", "Cheese"};
        UtilityData.addExpensesToMap(splitCommands, exchangeRates, actualResultExpensesMap);
        assertTrue(actualResultExpensesMap.containsKey(date));

       Expenses expenses = new Expenses(50,"USD", "Cheese");
        assertEquals(expenses.getAmount(), actualResultExpensesMap.get(date).get(0).getAmount(), 0.001);
        assertEquals(expenses.getCurrency(), actualResultExpensesMap.get(date).get(0).getCurrency());
        assertEquals(expenses.getProduct(), actualResultExpensesMap.get(date).get(0).getProduct());
    }

    @Test
    public void clearListByDateTest() throws ParseException {
        String[] splitCommands = {"clear","2017-10-10"};
        Date date = parsingStringToDate(splitCommands[1]);
        UtilityData.clearListByDate(splitCommands, actualResultExpensesMap);
        expectedResultExpensesMap.remove(date);
        assertEquals(expectedResultExpensesMap, actualResultExpensesMap);
        assertFalse(actualResultExpensesMap.containsKey(date));
    }

    @After
    public void tearDownForUtilityTests(){
        expectedResultExpensesMap = null;
        actualResultExpensesMap = null;
    }

    private Date parsingStringToDate(String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
            date = sdf.parse(s);
            return date;
    }

}
