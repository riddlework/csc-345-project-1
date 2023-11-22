/**
 * File: Super.java
 * Author: Maria Fay Garcia
 * Purpose: To implement the functionality of a superincreasing set.
 */
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * Implement the functionality of a superincreasing set.
 */
public class Super {
    private static Random gen;
    /**
     * Generate a pseudorandom superincreasing set, represented as an array
     *      of positive longs which is of size n.
     * @param n The long size of the array.
     * @return The array.
     */
    public static long[] genSuper(int n) {
        if (n <= 0) throw new IllegalArgumentException("Array size must be greater than 0");

        long[] array = new long[n];
        gen = new Random(System.currentTimeMillis());

        // Generate the first element randomly
        array[0] = (long) gen.nextInt(5) + 1; // Here we ensure a min. val. of 1

        // Now generate the rest of the array
        for (int i = 0; i < n; i++) {
            // Each element must be greater than the sum of the elements that come before it
            // Let's enforce that here:
            long sum = Arrays.stream(array, 0, i).sum();
            array[i] = sum + (long) gen.nextInt(5) + 1; // Here we also ensure a min. val. of 1
        }
        return array;
    }

    /**
     * Generate a pseudorandom target sum based on the elements of the array.
     * @param superArray A superincreasing array of Longs.
     * @return Some sum of the elements of the array.
     */
    public static long chooseTargetSum(long[] superArray) {
        gen = new Random(System.currentTimeMillis());
        HashSet<Integer> visited = new HashSet<>();
        long targetSum = 0;
        long n = gen.nextInt(superArray.length);
        for (long i = 0; i < n; i++) {
            int index = gen.nextInt(superArray.length);
            if (!visited.contains(index)) {
                targetSum += superArray[index];
                visited.add(index);
            }
        }
        return targetSum;
    }


}



