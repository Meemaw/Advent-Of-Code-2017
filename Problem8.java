import java.util.HashMap;
import java.util.Map;

public class Problem8 {

    private static class Instruction {
        String register;
        int value;
        boolean decrement;

        public Instruction(String register, int value, boolean decrement) {
            this.register = register;
            this.decrement = decrement;
            this.value = decrement ? -value : value;
        }

        public void execute(Map<String, Integer> registers) {
            if (!registers.containsKey(register)) registers.put(register, 0);
            registers.put(register, registers.get(register) + value);
        }
    }

    private static boolean evaluateCondition(String conditionType, int r1, int r2) {
        switch (conditionType) {
            case "==":
                return r1 == r2;
            case "!=":
                return r1 != r2;
            case ">=":
                return r1 >= r2;
            case "<=":
                return r1 <= r2;
            case ">":
                return r1 > r2;
            case "<":
                return r1 < r2;
            default:
                return false;
        }
    }

    private static int solve(String[] instructions, boolean solve2) {
        Map<String, Integer> registers = new HashMap<>();
        int maxValue = 0;
        for (String instruction : instructions) {
            String[] parts = instruction.split(" ");
            boolean truthy = evaluateCondition(parts[5], registers.getOrDefault(parts[4], 0), Integer.parseInt(parts[6]));
            if (truthy) {
                Instruction inst = new Instruction(parts[0], Integer.parseInt(parts[2]), parts[1].equals("dec"));
                inst.execute(registers);
                if (registers.get(inst.register) > maxValue) {
                    maxValue = registers.get(inst.register);
                }
            }
        }
        return solve2 ? maxValue : registers.values().stream().mapToInt(i -> i).max().getAsInt();
    }

    public static int solve1(String[] instructions) {
        return solve(instructions, false);
    }

    public static int solve2(String[] instructions) {
        return solve(instructions, true);
    }

}
