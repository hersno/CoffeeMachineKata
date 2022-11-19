public class CustomerRequest {
    private IDrink drink;
    private int sugarNumber;

    private double moneyInEuro;

    private double missingMoney;

    private String translateRequest;

    public CustomerRequest() {
    }

    public CustomerRequest build(){
        return this;
    }

    public IDrink getDrink() {
        return drink;
    }

    public CustomerRequest withDrink(IDrink drink) {
        this.drink = drink;
        return this;
    }

    public int getSugarNumber() {
        return sugarNumber;
    }

    public CustomerRequest withSugarNumber(int sugarNumber) {
        this.sugarNumber = sugarNumber;
        return this;
    }

    public double getMoneyInEuro() {
        return moneyInEuro;
    }

    public CustomerRequest withMoneyInEuro(double moneyInEuro) {
        this.moneyInEuro = moneyInEuro;
        return this;
    }

    public double getMissingMoney() {
        return missingMoney;
    }

    public CustomerRequest missingMoney(double missingMoney) {
        this.missingMoney = missingMoney;
        return this;
    }

    public String getTranslateRequest() {
        return translateRequest;
    }

    public CustomerRequest translateRequest(String request) {
        this.translateRequest = request;
        return this;
    }
}
