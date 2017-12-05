import java.util.Arrays;

public class Problem5 {

    @FunctionalInterface
    private interface OffsetFunction {
        int apply(int offset);
    }

    private static int solve(int[] jumps, OffsetFunction f) {
        int numSteps = 0;
        int ix = 0;

        while (ix < jumps.length && ix >= 0) {
            numSteps++;
            int offset = jumps[ix];
            jumps[ix] += f.apply(offset);
            ix += offset;
        }
        return numSteps;
    }

    public static int solve1(int[] jumps) {
        return solve(jumps, x -> 1);
    }

    public static int solve2(int[] jumps) {
        return solve(jumps, x -> x >= 3 ? -1 : 1);
    }

}
