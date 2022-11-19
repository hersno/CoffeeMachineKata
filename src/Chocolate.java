public class Chocolate implements HotDrink {

    private static final String NAME = "chocolate";
    private static final String CODE = "H";

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
        return 0.5d;
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
