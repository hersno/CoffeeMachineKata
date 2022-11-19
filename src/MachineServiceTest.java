import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MachineServiceTest {
    @Test
    public void shouldReturnReportValue(){
        List<IDrink> requestList = new ArrayList<>();
        requestList.add(new Coffee());
        requestList.add(new Coffee());
        requestList.add(new Chocolate());
        requestList.add(new Tea());
        requestList.add(new Tea());
        requestList.add(new Tea());
        StringBuilder expected = new StringBuilder();
        expected.append("chocolate: 0,5\n")
                .append("tea: 1,2\n")
                .append("coffee: 1,2\n")
                .append("Montant total: 2,9 euros");
        MachineService repository = new MachineService(requestList);
        Assertions.assertEquals(repository.printReport(),expected.toString());
    }
}
