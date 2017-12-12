import java.util.*;

public class Problem12 {


    public static class Graph {
        int V;
        List<Set<Integer>> nodes;

        public Graph(int V) {
            this.V = V;
            this.nodes = new ArrayList<>(V);
            for (int i = 0; i < V; i++) {
                this.nodes.add(new HashSet<>());
            }
        }

        void addEdge(int u, int w) {
            this.nodes.get(u).add(w);
            this.nodes.get(w).add(u);
        }
    }

    private static int hasFalse(boolean[] arr) {
        for (int i = 0; i < arr.length; i++) if (!arr[i]) return i;
        return -1;
    }

    private static int solve(Graph g, int id, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(id);

        Set<Integer> reachableNodes = new HashSet<>();

        while (!queue.isEmpty()) {
            int u = queue.poll();
            reachableNodes.add(u);

            for (int n : g.nodes.get(u)) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        return reachableNodes.size();
    }

    public static int solve1(Graph g) {
        return solve(g, 0, new boolean[g.V]);
    }

    public static int solve2(Graph g) {
        int nGroups = 0, ix;
        boolean[] visited = new boolean[g.V];

        while ((ix = hasFalse(visited)) >= 0) {
            solve(g, ix, visited);
            nGroups++;
        }
        return nGroups;
    }

    private static Graph parseInput(String input) {
        String[] lines = input.split("\n");
        Graph g = new Graph(lines.length);

        for (String line : lines) {
            int id = Integer.parseInt(line.split(" <-> ")[0]);
            String[] adj = line.split(" <-> ")[1].split(", ");
            for (String a : adj) {
                g.addEdge(id, Integer.parseInt(a));
            }
        }
        return g;
    }

}
