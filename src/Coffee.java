public class Coffee implements HotDrink{

    private static final String NAME = "coffee";
    private static final String CODE = "C";

    private boolean isExtrathot;

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

    @Override
    public void setExtrathot(boolean isExtrathot) {
        this.isExtrathot = isExtrathot;
    }

    @Override
    public boolean isExtraHot() {
        return isExtrathot;
    }
}
