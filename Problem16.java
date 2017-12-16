import java.util.*;

public class Problem16 {

    private static String shiftRight(String programs, int n) {
        return programs.substring(programs.length() - n) + programs.substring(0, programs.length() - n);
    }

    private static String swap(String programs, int i, int j) {
        char[] chars = programs.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return String.valueOf(chars);
    }

    private static String swapP(String programs, char a, char b) {
        return swap(programs, programs.indexOf(a), programs.indexOf(b));
    }

    private static String apply(String acc, String command) {
        String[] split = command.split("/");
        switch (split[0].charAt(0)) {
            case 's':
                return shiftRight(acc, Integer.parseInt(split[0].substring(1)));
            case 'x':
                return swap(acc, Integer.parseInt(split[0].substring(1)), Integer.parseInt(split[1]));
            default:
                return swapP(acc, split[0].charAt(1), split[1].charAt(0));
        }
    }

    public static String part1(String[] commands, String startingAcc) {
        return Arrays.stream(commands).reduce(startingAcc, Problem16::apply);
    }

    public static String part2(String[] commands) {
        String acc = "abcdefghijklmnop";
        List<String> l = new ArrayList<>();

        while (!l.contains(acc)) {
            l.add(acc);
            acc = part1(commands, acc);
        }
        return l.get(1000000000 % l.size());
    }

    public static String part1(String[] commands) {
        return part1(commands, "abcdefghijklmnop");
    }

}
