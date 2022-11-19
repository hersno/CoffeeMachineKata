public class Tea implements HotDrink{

    private static final String CODE = "T";
    private static final String NAME = "tea";

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
        return 0.4d;
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
