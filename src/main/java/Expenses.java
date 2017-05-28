public class Expenses {
      private double amount;
   private String currency;
   private String product;

    public Expenses(double amount, String currency, String product){
        this.amount = amount;
        this.currency = currency;
        this.product = product;
    }

    public String toString(){
        String result = product+" "+amount+" "+currency;
        return result;
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }
}

