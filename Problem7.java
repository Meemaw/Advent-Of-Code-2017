import java.util.*;
import java.util.stream.Collectors;

public class Problem7 {

    private static class Program {

        public Program setName(String name) {
            this.name = name;
            return this;
        }

        public Program setWeight(int weight) {
            this.weight = weight;
            return this;
        }

        public Program setChildren(Program[] childs) {
            this.children = childs;
            return this;
        }

        public Program setParent(Program parent) {
            this.parent = parent;
            return this;
        }

        public boolean isBalanced() {
            if (children == null) return true;
            else return Arrays.stream(children).map(x -> x.getTotalWeight()).distinct().count() == 1;
        }

        public int getTotalWeight() {
            if (children == null) return weight;
            else return weight + Arrays.stream(children).map(x -> x.getTotalWeight()).mapToInt(i -> i).sum();
        }

        public Program getRoot() {
            if (parent == null) return this;
            else return this.parent.getRoot();
        }

        private String name;
        private int weight;
        private Program[] children;
        private Program parent;
    }

    private static Map<Integer, Long> mapByFrequencies(Program[] childs) {
        return Arrays.stream(childs).collect(Collectors.groupingBy(Program::getTotalWeight, Collectors.counting()));
    }

    private static Program findOutlier(Program p) {
        if (p.isBalanced()) return p;
        return findOutlier(Arrays.stream(p.children).filter(x -> mapByFrequencies(p.children).get(x.getTotalWeight()) == 1).findFirst().get());
    }

    private static Program[] readInput(String input) {
        Map<String, Program> map = new HashMap<>();
        return Arrays.stream(input.split("\n")).map(line -> {
            String[] items = line.split("\\)");
            String[] nameWeight = items[0].split(" \\(");
            String programName = nameWeight[0];
            Program p = map.getOrDefault(programName, new Program()).setName(programName).setWeight(Integer.parseInt(nameWeight[1]));
            map.put(programName, p);

            if (items.length > 1) {
                String[] children = items[1].substring(4).split(", ");

                Program[] childs = Arrays.stream(children).map(x -> {
                    Program pp = map.getOrDefault(x, new Program()).setParent(p).setName(x);
                    map.put(x, pp);
                    return pp;
                }).toArray(Program[]::new);
                p.setChildren(childs);
            }
            return p;
        }).toArray(Program[]::new);
    }

    private static String solve1(String input) {
        return readInput(input)[0].getRoot().name;
    }

    private static int solve2(String input) {
        Program root = readInput(input)[0].getRoot();
        Program outlier = findOutlier(root);
        int outlierWeight = outlier.getTotalWeight();
        int balancedWeight = Arrays.stream(outlier.parent.children)
                .mapToInt(child -> child.getTotalWeight())
                .filter(weight -> weight != outlierWeight).findFirst().getAsInt();
        return outlier.weight + (balancedWeight - outlierWeight);
    }
    
}
