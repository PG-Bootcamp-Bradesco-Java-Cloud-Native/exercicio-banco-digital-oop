package shared;

public class Helpers {
    public static class Console {
        public static void clear() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    public static class Parsers {
        public static Integer tryParseInteger(String s) {
            Integer value;
            try {
                value = Integer.parseInt(s);
            } catch (Exception e) {
                return null;
            }
            return value;
        }

        public static Double tryParseDouble(String s) {
            Double value;
            try {
                value = Double.parseDouble(s);
            } catch (Exception e) {
                return null;
            }
            return value;
        }
    }
}
