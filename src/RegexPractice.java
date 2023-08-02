import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class RegexPractice {
    public static void main(String[] args) {
        String regex = """
(?:(?<countryCode>\\d{1,2})[-.,\\s]?)? #Get's Country Code
(?:\\(?(?<areaCode>\\d{3})\\)?[-.,\\s]?) #Get's Area Code
(?:(?<exchange>\\d{3})[-.,\\s]?) #Get's Exchange
(?<lineNumber>\\d{4}) #Get's Line Number
""";
        String phoneNumber = "1-(232) 333-2365";



        Pattern pat = Pattern.compile(regex, Pattern.COMMENTS);
        Matcher mat = pat.matcher(phoneNumber);

        if(mat.matches()){
            System.out.format("Country Code: %s\n", mat.group("countryCode"));
            System.out.format("Area Code: %s\n", mat.group("areaCode"));
            System.out.format("Exchange Code: %s\n", mat.group("exchange"));
            System.out.format("Line Number: %s\n", mat.group("lineNumber"));
        }
    }
}
