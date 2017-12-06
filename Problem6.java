import java.util.*;
import java.util.stream.IntStream;

public class Problem6 {

    private static final List<String> l = new ArrayList<>();

    private static int[] redistribute(int[] memory) {
        int maxIx = IntStream.range(0, memory.length).reduce((a, b) -> memory[a] >= memory[b] ? a : b).orElse(0);
        int maxValue = memory[maxIx];
        memory[maxIx] = 0;
        for (int i = maxIx + 1; i < maxIx + maxValue + 1; i++) {
            memory[i % memory.length]++;
        }
        return memory;
    }

    private static int solve1(int[] memory) {
        int n = 1;
        String s = Arrays.toString(memory);
        l.add(s);
        while (!l.contains(s = Arrays.toString(memory = redistribute(memory)))) {
            l.add(s);
            n++;
        }
        l.add(s);
        return n;
    }

    private static int solve2(int[] memory) {
        if (l.isEmpty()) solve1(memory);
        return l.size() - l.indexOf(l.get(l.size() - 1));
    }

}
