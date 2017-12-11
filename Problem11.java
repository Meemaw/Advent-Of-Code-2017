public class Problem11 {
    
    private static int solve(String[] directions, boolean solve1) {
        int x = 0, y = 0, z = 0, max = 0;
        for (String direction : directions) {
            if (direction.equals("n")) {
                y++;
                z--;
            } else if (direction.equals("ne")) {
                x++;
                z--;
            } else if (direction.equals("se")) {
                y--;
                x++;
            } else if (direction.equals("s")) {
                y--;
                z++;
            } else if (direction.equals("sw")) {
                x--;
                z++;
            } else {
                y++;
                x--;
            }
            int dist = calculateDistance(x, y, z);
            if (dist > max) {
                max = dist;
            }
        }
        return solve1 ? calculateDistance(x, y, z) : max;
    }

    public static int solve1(String[] directions) {
        return solve(directions, true);
    }

    public static int solve2(String[] directions) {
        return solve(directions, false);
    }

    private static int calculateDistance(int x, int y, int z) {
        return (Math.abs(x) + Math.abs(y) + Math.abs(z)) / 2;
    }

}
