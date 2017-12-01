import java.util.stream.IntStream;

public class Problem1 {

    private static boolean compareAt(String input, int ix1, int ix2) {
        return input.charAt(ix1) == input.charAt(ix2);
    }

    private static int solve1(String input) {
        return solve(input, 1);
    }

    private static int solve2(String input) {
        return solve(input, input.length() / 2);
    }

    private static int solve(String input, int offset) {
        String wrapCircular = input + input.substring(0, offset);
        return IntStream.range(0, wrapCircular.length() - offset)
                .map(ix -> compareAt(wrapCircular, ix, ix + offset) ? Character.getNumericValue(wrapCircular.charAt(ix)) : 0)
                .sum();
    }

}
