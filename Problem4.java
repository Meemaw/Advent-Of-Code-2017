import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Problem4 {

    private static Map<String, Map<String, Long>> memo = new HashMap<>(10000);

    private static Map<String, Long> getOcurances(String word) {
        if (memo.containsKey(word)) return memo.get(word);
        Map<String, Long> occ = Arrays.stream(word.toLowerCase().split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        memo.put(word, occ);
        return occ;
    }

    private static boolean hasAnagrams(String[] s) {
        for (int i = 0; i < s.length - 1; i++) {
            Map<String, Long> w1 = getOcurances(s[i]);
            for (int j = i + 1; j < s.length; j++) {
                if (w1.entrySet().equals(getOcurances(s[j]).entrySet())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int solve1(String[][] input) {
        return Arrays.stream(input).filter(x -> Arrays.stream(x).distinct().toArray().length == x.length).toArray().length;
    }


    public static int solve2(String[][] input) {
        return Arrays.stream(input).filter(x -> !hasAnagrams(x)).toArray().length;
    }

}
