import java.util.Iterator;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;

public class Problem15 {

    private static boolean bitsEquals(Iterator<Long> a, Iterator<Long> b) {
        return (a.next() & 65535) == (b.next() & 65535);
    }

    private static LongStream generator(long start, long nTimes, LongPredicate predicate, long limit) {
        return LongStream.iterate(start, i -> i * nTimes % 2147483647).filter(predicate).limit(limit);
    }

    private static long solve(long startA, long startB, LongPredicate pA, LongPredicate pB, long limit) {
        long acc = 0;
        Iterator<Long> itA = generator(startA, 16807, pA, limit).iterator();
        Iterator<Long> itB = generator(startB, 48271, pB, limit).iterator();
        while (itA.hasNext() && itB.hasNext()) {
            if (bitsEquals(itA, itB)) acc++;
        }
        return acc;
    }

    public static long solve1(long startA, long startB) {
        return solve(startA, startB, i -> true, i -> true, 40000000);
    }

    public static long solve2(long startA, long startB) {
        return solve(startA, startB, i -> i % 4 == 0, i -> i % 8 == 0, 5000000);
    }

}

