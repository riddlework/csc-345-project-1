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
     * Generate a pseudorandom superincreasing set, represented as an array of integers which is of size n.
     * @param n The integer size of the array.
     * @return The array.
     */
    public static int[] genSuper(int n) {
        if (n <= 0) throw new IllegalArgumentException("Array size must be greater than 0");

        int[] array = new int[n];
        gen = new Random(System.currentTimeMillis());

        // Generate the first element randomly
        array[0] = gen.nextInt(100) + 1; // Here we ensure a min. val. of 1

        // Now generate the rest of the array
        for (int i = 0; i < n; i++) {
            // Each element must be greater than the sum of the elements that come before it
            // Let's enforce that here:
            int sum = Arrays.stream(array, 0, i).sum();
            for (int j = 0; j < i; j++) {
                sum += array[j];
            }
            array[i] = sum + gen.nextInt(100) + 1; // Here we also ensure a min. val. of 1
        }
        return array;
    }

    /**
     * Generate a random target sum based on the elements of the array.
     * @param superArray A superincreasing array of integers.
     * @return Some sum of the elements of the array.
     */
    public static int chooseTargetSum(int[] superArray) {
        gen = new Random(System.currentTimeMillis());
        HashSet<Integer> visited = new HashSet<>();
        int targetSum = 0;
        int n = gen.nextInt(superArray.length);
        for (int i = 0; i < n; i++) {
            int index = gen.nextInt(superArray.length);
            if (!visited.contains(index)) {
                targetSum += superArray[index];
                visited.add(index);
            }
        }
        return targetSum;
    }

    /**
     * Check if the array is a superincreasing set.
     * @param array
     * @return
     */
    public static boolean isSuper(int[] array) {
        // For testing purposes
        Arrays.sort(array);
        for (int i = 1; i < array.length-1; i++) {
            int cur = 0;
            for (int j = 0; j < i; j++) {
                cur += array[j];
            }
            if (cur >= array[i]) return false;
        }
        return true;
    }
}



