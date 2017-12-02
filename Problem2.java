import java.util.Arrays;

public class Problem2 {


    @FunctionalInterface
    private interface RowFunction {
        int apply(int[] arr);
    }

    private static int rowDifference(int[] row) {
        return Arrays.stream(row).max().orElse(0) - Arrays.stream(row).min().orElse(0);
    }

    private static int evenDivider(int[] row, int n) {
        return Arrays.stream(row).filter(x -> x > n).filter(x -> x % n == 0).findFirst().orElse(0);
    }

    private static int evenlyDivisibleSum(int[] row) {
        return Arrays.stream(row).map(x -> evenDivider(row, x) / x).sum();
    }

    private static int solve(int[][] spreadsheet, RowFunction f) {
        return Arrays.stream(spreadsheet).map(f::apply).mapToInt(i -> i).sum();
    }

    private static int solve1(int[][] spreadsheet) {
        return solve(spreadsheet, Problem2::rowDifference);
    }

    private static int solve2(int[][] spreadsheet) {
        return solve(spreadsheet, Problem2::evenlyDivisibleSum);
    }

}
