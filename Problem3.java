import java.util.HashMap;
import java.util.Map;
import java.util.stream.LongStream;

public class Problem3 {


    private static class SpiralCircle {
        private static Map<Long, Long> memo = new HashMap<>();

        private long firstNumberInCircle;
        private long cornerEdgeOffset;
        private long spiralRadius;

        public SpiralCircle(long n) {
            this.spiralRadius = calculateRadius(n);
            this.cornerEdgeOffset = 2 * spiralRadius;
            this.firstNumberInCircle = numItemsInSpiral(spiralRadius - 1) + 1;
        }

        public long[] getCorners() {
            long upperRight = firstNumberInCircle + cornerEdgeOffset - 1;
            long upperLeft = upperRight + cornerEdgeOffset;
            long lowerLeft = upperLeft + cornerEdgeOffset;
            long lowerRight = lowerLeft + cornerEdgeOffset;
            return new long[]{upperRight, upperLeft, lowerLeft, lowerRight};
        }

        public long getFirstNumberInCircle() {
            return this.firstNumberInCircle;
        }

        public long getSpiralRadius() {
            return this.spiralRadius;
        }

        private long numItemsInSpiral(long y) {
            if (y == 0) return 1;
            if (memo.containsKey(y)) return memo.get(y);
            long upperCount = y * 2 + 1;
            long lowerCount = upperCount - 2;
            long res = upperCount * 2 + lowerCount * 2 + numItemsInSpiral(y - 1);
            memo.put(y, res);
            return res;
        }

        private long calculateRadius(long n) {
            return LongStream.iterate(1, i -> i + 1).filter(i -> numItemsInSpiral(i) >= n).findFirst().getAsLong();
        }
    }


    private static long solve1(long n) {
        SpiralCircle spiral = new SpiralCircle(n);
        long spiralRadius = spiral.getSpiralRadius();
        long[] corners = spiral.getCorners();

        if (n <= corners[0]) return spiralRadius + Math.abs(spiral.getFirstNumberInCircle() + (spiralRadius - 1) - n);
        else if (n < corners[1]) return Math.abs((corners[1] + corners[0]) / 2 - n) + spiralRadius;
        else if (n <= corners[2]) return spiralRadius + Math.abs((corners[2] + corners[1]) / 2 - n);
        else return Math.abs((corners[2] + corners[3]) / 2 - n) + spiralRadius;
    }

    private static long solve2(int n) {
        int mazeDiameter = 100;
        long[][] map = new long[mazeDiameter][mazeDiameter];
        map[mazeDiameter / 2][mazeDiameter / 2] = 1;
        return inner(map, n, mazeDiameter / 2 + 1, mazeDiameter / 2, 2);
    }


    private static long inner(long[][] map, int n, int x, int y, long ix) {
        map[x][y] = getNeighbourSum(map, x, y);
        if (map[x][y] >= n) return map[x][y];

        SpiralCircle spiral = new SpiralCircle(ix);
        long[] corners = spiral.getCorners();

        if (ix < corners[0]) return inner(map, n, x, y + 1, ix + 1);
        else if (ix < corners[1]) return inner(map, n, x - 1, y, ix + 1);
        else if (ix < corners[2]) return inner(map, n, x, y - 1, ix + 1);
        else return inner(map, n, x + 1, y, ix + 1);
    }


    private static long getNeighbourSum(long[][] map, int x, int y) {
        return map[x - 1][y] + map[x - 1][y - 1] + map[x - 1][y + 1] + map[x][y + 1] + map[x][y - 1] + map[x + 1][y] + map[x + 1][y + 1] + map[x + 1][y - 1];
    }
}
