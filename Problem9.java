import java.util.HashMap;
import java.util.Map;

public class Problem9 {

    private enum State {
        GROUP_OPEN, GROUP_CLOSE, GARBAGE, IGNORE, OTHER;

        final Map<Character, State> transitions = new HashMap<>();

        static {
            OTHER.addTransition('{', GROUP_OPEN);
            OTHER.addTransition('}', GROUP_CLOSE);
            OTHER.addTransition('<', GARBAGE);
            GARBAGE.addTransition('!', IGNORE);
            GARBAGE.addTransition('>', OTHER);
            GROUP_OPEN.addTransition('}', GROUP_CLOSE);
            GROUP_OPEN.addTransition('<', GARBAGE);
            GROUP_OPEN.addTransition(',', OTHER);
            GROUP_CLOSE.addTransition('{', GROUP_OPEN);
            GROUP_CLOSE.addTransition('<', GARBAGE);
            GROUP_CLOSE.addTransition(',', OTHER);
            IGNORE.addTransition('!', GARBAGE);
        }

        private void addTransition(char c, State accept) {
            transitions.put(c, accept);
        }

        public State getTransition(char c) {
            return transitions.getOrDefault(c, this);
        }
    }

    private static int solve1(String input) {
        int depth = 0, acc = 0;
        State current = State.OTHER;

        for (char c : input.toCharArray()) {
            c = current == State.IGNORE ? '!' : c;
            State next = current.getTransition(c);
            switch (next) {
                case GROUP_OPEN:
                    depth++;
                    break;
                case GROUP_CLOSE:
                    acc += depth--;
                    break;
            }
            current = next;
        }
        return acc;
    }

    private static int solve2(String input) {
        int acc = 0;
        State current = State.OTHER;

        for (char c : input.toCharArray()) {
            c = current == State.IGNORE ? '!' : c;
            State next = current.getTransition(c);
            switch (next) {
                case GARBAGE:
                    if (current == State.GARBAGE) acc++;
            }
            current = next;
        }
        return acc;
    }
}
