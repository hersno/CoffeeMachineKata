import java.util.List;

public class MachineService {
    private static final String INLINE = "\n";
    private static final String TEA = "tea: ";
    private static final String COFFEE = "coffee: ";
    private static final String MONTANT_TOTAL = "Montant total: ";
    private static final String EUROS = " euros";
    private static final String CHOCOLATE = "chocolate: ";
    List<IDrink> drinks;
    public MachineService(List<IDrink> drinks){
        this.drinks = drinks;
    }

    public String printReport(){
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(CHOCOLATE)
                     .append(countByDrinkType(new Chocolate()))
                     .append(INLINE)
                     .append(TEA)
                     .append(countByDrinkType(new Tea()))
                     .append(INLINE)
                     .append(COFFEE)
                     .append(countByDrinkType(new Coffee()))
                     .append(INLINE)
                     .append(MONTANT_TOTAL)
                     .append(montantTotal())
                     .append(EUROS)
                     .toString();
    }

    private String countByDrinkType(IDrink drink1) {
        return Utils.decimalFormat(this.drinks
                   .stream()
                   .filter(drink -> drink.getCode().equals(drink1.getCode()))
                   .mapToDouble(IDrink::getPrice)
                   .sum());
    }


    private String montantTotal() {
        return Utils.decimalFormat(this.drinks
                .stream()
                .mapToDouble(IDrink::getPrice)
                .sum());
    }
}
