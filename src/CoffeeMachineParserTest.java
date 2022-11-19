import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CoffeeMachineParserTest {

    CoffeeMachineParser parser;
    CustomerRequest customerRequest;

    BeverageQuantityChecker beverageQuantityChecker;

    EmailNotifier emailNotifier;

    @BeforeEach
    public void beforeEach(){
        beverageQuantityChecker = new BeverageQuantityCheckerImpl();
        emailNotifier = new EmailNotifierImpl();
        parser = new CoffeeMachineParser(beverageQuantityChecker,emailNotifier);
        customerRequest = new CustomerRequest();
    }

    @Test
    public void shouldReturnT10WhenTeaWithSugarAreOrdered () {
        customerRequest
                .withDrink(new Tea())
                .withSugarNumber(1)
                .withMoneyInEuro(0.4)
                .build();
        Assertions.assertEquals("T:1:0",parser.encode(customerRequest));
    }

    @Test
    public void shouldReturnHWhenChocolateWithNoSugarAreOrdered () {
        customerRequest
                .withDrink(new Chocolate())
                .withMoneyInEuro(0.5)
                .build();
        Assertions.assertEquals("H::",parser.encode(customerRequest));
    }

    @Test
    public void shouldReturnC20WhenCoffeeWithTwoSugarAreOrdered () {
        customerRequest
                .withDrink(new Coffee())
                .withSugarNumber(2)
                .withMoneyInEuro(0.6)
                .build();
        Assertions.assertEquals("C:2:0",parser.encode(customerRequest));
    }

    @Test
    public void shouldReturnMissingMoney () {
        customerRequest
                .withDrink(new Coffee())
                .withMoneyInEuro(0.01)
                .build();
        Assertions.assertEquals("M:missing 0.59 euros",parser.encode(customerRequest));
    }

    @Test
    public void shouldReturnOrangeJuiceWithNoSugarAndNoStick () {
        customerRequest
                .withDrink(new OrangeJuice())
                .withMoneyInEuro(0.6)
                .build();
        Assertions.assertEquals("O::",parser.encode(customerRequest));
    }

    @Test
    public void shouldReturnHotChocolateWithSugarAndStick() {
        Chocolate chocolate = new Chocolate();
        chocolate.setExtrathot(true);
        customerRequest
                .withDrink(chocolate)
                .withSugarNumber(1)
                .withMoneyInEuro(0.5)
                .build();
        Assertions.assertEquals("Hh:1:0",parser.encode(customerRequest));
    }

    @Test
    public void shouldReturnChWhenHotCoffeeWithNoSugarAreOrdered () {
        Coffee coffee = new Coffee();
        coffee.setExtrathot(true);
        customerRequest
                .withDrink(coffee)
                .withMoneyInEuro(0.6)
                .build();
        Assertions.assertEquals("Ch::",parser.encode(customerRequest));
    }

    @Test
    public void shouldReturnTh20WhenHotTeaWitSugarAreOrdered () {
        Tea tea = new Tea();
        tea.setExtrathot(true);
        customerRequest
                .withDrink(tea)
                .withSugarNumber(2)
                .withMoneyInEuro(0.6)
                .build();
        Assertions.assertEquals("Th:2:0",parser.encode(customerRequest));
    }
    @Test
    public void shouldPrintReport() {
        customerRequest
                .withDrink(new Chocolate())
                .withSugarNumber(2)
                .withMoneyInEuro(0.6)
                .build();
        parser.encode(customerRequest);
        customerRequest
                .withDrink(new Tea())
                .withSugarNumber(2)
                .withMoneyInEuro(0.6)
                .build();
        parser.encode(customerRequest);
        customerRequest
                .withDrink(new Coffee())
                .withSugarNumber(2)
                .withMoneyInEuro(0.6)
                .build();
        parser.encode(customerRequest);
        StringBuilder expected = new StringBuilder();
        expected.append("chocolate: ")
                .append("0,5\n")
                .append("tea: ")
                .append("0,4\n")
                .append("coffee: ")
                .append("0,6\n")
                .append("Montant total: 1,5 euros");

        Assertions.assertEquals(parser.printReport(),expected.toString());
    }

    @Test
    public void shouldSendOnceEmail(){
        Tea tea = new Tea();
        tea.setExtrathot(true);
        customerRequest
                .withDrink(tea)
                .withSugarNumber(2)
                .withMoneyInEuro(0.6)
                .build();
        when(this.beverageQuantityChecker.isEmpty(tea.getCode())).thenReturn(true);
        parser.encode(customerRequest);
        verify(this.emailNotifier, times(1)).notifyMissingDrink("tea");

    }
    @Test
    public void shouldNotSendEmail(){
        Tea tea = new Tea();
        tea.setExtrathot(true);
        customerRequest
                .withDrink(tea)
                .withSugarNumber(2)
                .withMoneyInEuro(0.6)
                .build();
        when(this.beverageQuantityChecker.isEmpty(tea.getCode())).thenReturn(false);
        parser.encode(customerRequest);
        verify(this.emailNotifier, never()).notifyMissingDrink("tea");

    }
}
