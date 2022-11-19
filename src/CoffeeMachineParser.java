import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class CoffeeMachineParser {
    private static final String SEPARATEUR = ":";
    private static final String ZERO = "0";
    private static final String EMPTY_STRING = "";
    private static final String M = "M:";
    private static final String YOU_SHOULD_ADD = "missing ";
    private static final String EUROS = " euros";
    private static final String H = "h";

    private List<IDrink> drinks ;
    private BeverageQuantityChecker beverageQuantityChecker;
    private EmailNotifier emailNotifier;

    public CoffeeMachineParser(BeverageQuantityChecker beverageQuantityChecker, EmailNotifier emailNotifier) {
       this.drinks = new ArrayList<>();
       this.beverageQuantityChecker = beverageQuantityChecker;
       this.emailNotifier = emailNotifier;
    }

    public String encode(CustomerRequest request){
        return checkMoney()
                      .andThen(encodeMessage())
                      .apply(request)
                      .getTranslateRequest();
    }

    Function<CustomerRequest, CustomerRequest> checkMoney() {
        return request -> request
                          .missingMoney(getMissingMoney(request) >=0? 0: Math.abs(getMissingMoney(request)))
                          .build();
    }

    private double getMissingMoney(CustomerRequest request) {
        return request.getMoneyInEuro() - request.getDrink().getPrice();
    }

    Function<CustomerRequest, CustomerRequest> encodeMessage() {
        return request -> Optional.of(request.getMissingMoney())
                .filter(missingMoney -> missingMoney>0)
                .map(missingMoney -> request
                                       .translateRequest(M + YOU_SHOULD_ADD + request.getMissingMoney() + EUROS)
                                       .build())
                .orElseGet(() -> {
                    this.drinks.add(request.getDrink());
                    sendNotificationIfEmptyDrink(request.getDrink());
                    return request.translateRequest(
                            new StringBuilder()
                                    .append(request.getDrink().getCode())
                                    .append(checkIfExtraHot(request))
                                    .append(SEPARATEUR)
                                    .append(request.getSugarNumber() > 0 ? String.valueOf(request.getSugarNumber()) : EMPTY_STRING)
                                    .append(SEPARATEUR)
                                    .append(request.getSugarNumber() > 0 ? ZERO : EMPTY_STRING)
                                    .toString()).build();
                });

    }

    private String checkIfExtraHot(CustomerRequest theRequest) {
        return Optional.of(theRequest.getDrink())
                .filter(drink ->drink instanceof HotDrink)
                .map(hotDrink -> ((HotDrink)hotDrink).isExtraHot()? H : EMPTY_STRING)
                .orElse(EMPTY_STRING);

    }

    public String printReport(){
        MachineService service = new MachineService(this.drinks);
        return service.printReport();
    }

    public void sendNotificationIfEmptyDrink(IDrink drink){
        if(this.beverageQuantityChecker.isEmpty(drink.getCode())){
            this.emailNotifier.notifyMissingDrink(drink.getCode());
        }
    }
}
