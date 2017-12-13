import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Problem13 {

    public static Map<Integer, Integer> readInput(String input) {
        return Arrays.stream(input.split("\n"))
                .map(x -> x.split(": "))
                .collect(Collectors.toMap(x -> Integer.parseInt(x[0]), x -> Integer.parseInt(x[1])));
    }

    private static int severity(Map<Integer, Integer> lengths, int packetNumber) {
        return (packetNumber % (2 * (lengths.get(packetNumber) - 1)) == 0) ? lengths.get(packetNumber) * packetNumber : 0;
    }

    private static boolean isLayerScanned(Map<Integer, Integer> lengths, int packetNumber, int delay) {
        return (packetNumber + delay) % (2 * (lengths.get(packetNumber) - 1)) == 0;
    }

    private static boolean isCaught(Map<Integer, Integer> lengths, int delay) {
        return lengths.keySet().stream().map(x -> isLayerScanned(lengths, x, delay)).anyMatch(x -> x);
    }

    public static int solve1(Map<Integer, Integer> lengths) {
        return lengths.keySet().stream().map(x -> severity(lengths, x)).mapToInt(i -> i).sum();
    }

    public static int solve2(Map<Integer, Integer> lengths) {
        int delay = 0;
        while (isCaught(lengths, delay)) delay++;
        return delay;
    }

}
