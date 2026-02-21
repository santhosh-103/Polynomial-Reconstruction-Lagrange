import java.io.FileReader;
import java.math.*;
import java.util.*;
import org.json.JSONObject;
import org.json.JSONTokener;

public class FindConstantTerm {

    static class Point {
        BigDecimal x, y;
        Point(BigDecimal x, BigDecimal y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {

        FileReader reader = new FileReader("testcases.json");
        JSONObject json = new JSONObject(new JSONTokener(reader));

        Iterator<String> testcaseNames = json.keys();

        while (testcaseNames.hasNext()) {

            String testcaseName = testcaseNames.next();
            JSONObject testcase = json.getJSONObject(testcaseName);
            int k = testcase.getJSONObject("keys").getInt("k");

            List<String> pointKeys = new ArrayList<>();
            Iterator<String> it = testcase.keys();

            while (it.hasNext()) {
                String key = it.next();
                if (!key.equals("keys")) pointKeys.add(key);
            }

            pointKeys.sort((a,b)-> new BigInteger(a).compareTo(new BigInteger(b)));

            List<Point> points = new ArrayList<>();

            for (int i = 0; i < k; i++) {
                String key = pointKeys.get(i);

                JSONObject obj = testcase.getJSONObject(key);
                int base = Integer.parseInt(obj.getString("base"));
                BigInteger yInt = new BigInteger(obj.getString("value"), base);

                points.add(new Point(new BigDecimal(key), new BigDecimal(yInt)));
            }

            BigDecimal secret = lagrangeAtZero(points);
            System.out.println(testcaseName + " Secret = " + secret.toBigInteger());
        }
    }

    static BigDecimal lagrangeAtZero(List<Point> points) {

        MathContext mc = new MathContext(100);
        BigDecimal result = BigDecimal.ZERO;

        int k = points.size();

        for (int i = 0; i < k; i++) {

            BigDecimal xi = points.get(i).x;
            BigDecimal yi = points.get(i).y;
            BigDecimal term = yi;

            for (int j = 0; j < k; j++) {
                if (i == j) continue;

                BigDecimal xj = points.get(j).x;
                term = term.multiply(xj.negate(), mc)
                           .divide(xi.subtract(xj), mc);
            }

            result = result.add(term, mc);
        }

        return result.setScale(0, RoundingMode.HALF_UP);
    }
}