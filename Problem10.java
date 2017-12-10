import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem10 {

    private static void reverse(int[] arr, int ix, int length) {
        for (int i = ix, j = 0; i < ix + (length / 2); i++, j++) {
            int startIndex = i % arr.length;
            int endIndex = (ix + length - j - 1) % arr.length;
            int temp = arr[startIndex];
            arr[startIndex] = arr[endIndex];
            arr[endIndex] = temp;
        }
    }

    private static int[] concat(int[] arr1, int[] arr2) {
        int[] arr = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, arr, 0, arr1.length);
        System.arraycopy(arr2, 0, arr, arr1.length, arr2.length);
        return arr;
    }

    private static int denseHash(int[] arr, int ix) {
        return IntStream.range(ix + 1, ix + 16).reduce(arr[ix], (acc, i) -> acc ^ arr[i]);
    }

    public static String solve2(int[] arr, String inputLengths) {
        int ix = 0, skipSize = 0, numRounds = 64;

        int[] lengths = inputLengths.chars().toArray();
        int[] suffix = new int[]{17, 31, 73, 47, 23};
        lengths = concat(lengths, suffix);

        for (int i = 0; i < numRounds; i++) {
            for (int length : lengths) {
                reverse(arr, ix, length);
                ix = (ix + length + skipSize++) % arr.length;
            }
        }

        int[] hashes = IntStream.range(0, arr.length / 16).map(x -> denseHash(arr, x * 16)).toArray();
        return Arrays.stream(hashes).mapToObj(x -> String.format("%02x", x)).collect(Collectors.joining(""));
    }

    public static int solve1(int[] arr, int[] lengths) {
        int skipSize = 0, ix = 0;

        for (int length : lengths) {
            reverse(arr, ix, length);
            ix = (ix + length + skipSize++) % arr.length;
        }
        return arr[0] * arr[1];
    }
    
}
