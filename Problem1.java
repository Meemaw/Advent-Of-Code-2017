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
        int l = input.length();
        return IntStream.range(0, input.length())
                .map(ix -> compareAt(input, ix, (ix + offset) % l) ? input.charAt(ix) - '0' : 0)
                .sum();
    }

}
