import java.text.DecimalFormat;

public class Utils {
    public static String decimalFormat(double montant) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(montant);
    }
}
