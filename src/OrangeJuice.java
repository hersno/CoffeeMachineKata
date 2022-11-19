public class OrangeJuice implements IDrink{

    public static final String NAME = "orange juice";
    public static final String CODE = "O";

    @Override
    public String getCode() {
        return CODE;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public double getPrice() {
        return 0.6d;
    }
}
