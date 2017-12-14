import java.math.BigInteger;
import java.util.stream.IntStream;

public class Problem14 {

    private static String hash(String input) {
        return Problem10.solve2(IntStream.range(0, 256).toArray(), input);
    }

    private static String hexToBinary(String input) {
        return String.format("%128s", new BigInteger(input, 16).toString(2)).replaceAll(" ", "0");
    }

    private static String transform(String input, int i) {
        return hexToBinary(hash(String.format("%s-%d", input, i)));
    }
    
    private static String markString(String s, int j) {
        char[] chars = s.toCharArray();
        chars[j] = 'x';
        return String.valueOf(chars);
    }

    private static boolean isTaken(String[] s, int i, int j) {
        return inBounds(s, i, j) && s[i].charAt(j) == '1';
    }

    private static boolean inBounds(String[] s, int i, int j) {
        return i >= 0 && j >= 0 && i < s.length && j < s[i].length();
    }

    private static void markGroup(String[] s, int i, int j) {
        s[i] = markString(s[i], j);
        if (isTaken(s, i + 1, j)) markGroup(s, i + 1, j);
        if (isTaken(s, i, j + 1)) markGroup(s, i, j + 1);
        if (isTaken(s, i, j - 1)) markGroup(s, i, j - 1);
        if (isTaken(s, i - 1, j)) markGroup(s, i - 1, j);
    }

    public static long solve1(String input) {
        return IntStream.range(0, 128)
                .mapToObj(x -> transform(input, x))
                .mapToLong(x -> x.chars().filter(c -> c == '1').count()).sum();
    }

    public static long solve2(String input) {
        String[] map = IntStream.range(0, 128).mapToObj(x -> transform(input, x)).toArray(String[]::new);
        long acc = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length(); j++) {
                if (map[i].charAt(j) == '1') {
                    markGroup(map, i, j);
                    acc += 1;
                }

            }
        }
        return acc;
    }

}
