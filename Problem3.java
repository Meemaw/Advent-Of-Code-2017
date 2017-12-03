import java.util.HashMap;
import java.util.Map;
import java.util.stream.LongStream;

public class Problem3 {

    private static Map<Long, Long> memo = new HashMap<>();

    public static class SpiralCircle {
        private long lowerBound;
        private long offset;
        private long spiralEdge;

        public SpiralCircle(long n) {
            this.spiralEdge = getCircle(n);
            this.offset = 2 * spiralEdge;
            this.lowerBound = circleCount(spiralEdge - 1) + 1;
        }

        public long[] getCorners() {
            long upperRight = lowerBound + offset - 1;
            long upperLeft = upperRight + offset;
            long lowerLeft = upperLeft + offset;
            long lowerRight = lowerLeft + offset;
            return new long[]{upperRight, upperLeft, lowerLeft, lowerRight};
        }

        public long getLowerBound() {
            return this.lowerBound;
        }

        public long getSpiralEdge() {
            return this.spiralEdge;
        }

        private long circleCount(long y) {
            if (y == 0) return 1;
            if (memo.containsKey(y)) return memo.get(y);
            long upperCount = y * 2 + 1;
            long lowerCount = upperCount - 2;
            long res = upperCount * 2 + lowerCount * 2 + circleCount(y - 1);
            memo.put(y, res);
            return res;
        }

        private long getCircle(long n) {
            return LongStream.iterate(1, i -> i + 1).filter(i -> circleCount(i) >= n).findFirst().getAsLong();
        }
    }


    private static long solve1(long n) {
        SpiralCircle spiral = new SpiralCircle(n);
        long spiralEdge = spiral.getSpiralEdge();
        long[] corners = spiral.getCorners();

        if (n <= corners[0]) return spiralEdge + Math.abs(spiral.getLowerBound() + (spiralEdge - 1) - n);
        else if (n < corners[1]) return Math.abs((corners[1] + corners[0]) / 2 - n) + spiralEdge;
        else if (n <= corners[2]) return spiralEdge + Math.abs((corners[2] + corners[1]) / 2 - n);
        else return Math.abs((corners[2] + corners[3]) / 2 - n) + spiralEdge;
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
