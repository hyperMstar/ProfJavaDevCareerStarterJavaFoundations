import jdk.jfr.Percentage;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class CompoundInterestCalc {

    private static final NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormatter = NumberFormat.getPercentInstance();

    public static BigDecimal calculate(String principal, String rate, int period, String contribution) throws ParseException {

        String rateAsPercent = percentFormatter.parse(rate).toString();
        BigDecimal a = BigDecimal.ONE.add(new BigDecimal(rateAsPercent).pow(period));// (1 + r)
        BigDecimal c = a.subtract(BigDecimal.ONE); // ((1+r)^Y-1)
        BigDecimal d = c.divide(new BigDecimal(rateAsPercent));// ((1+r)^Y-1) / r
        BigDecimal e = d.multiply(new BigDecimal(moneyFormatter.parse(contribution).toString()));// c[((1+r)^Y-1) / r]

        BigDecimal f = a.multiply(new BigDecimal(moneyFormatter.parse(principal).toString())); //P(1 +r)Y
        BigDecimal g = f.add(e);
        return g;

    }

    public static void main(String[] args) throws ParseException {
        DecimalFormat df = new DecimalFormat("$#,###.00");
        BigDecimal balance = CompoundInterestCalc.calculate("$25,300", "8%", 10, "$7,500");
        System.out.println(df.format(balance));
        System.out.printf("$%,(.2f%n", balance);
    }
}
