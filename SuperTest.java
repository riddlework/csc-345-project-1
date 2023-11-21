/**
 * File: SubsetSum.java
 * Author: Maria Fay Garcia
 * Purpose: To test superincreasing set generator.
 */

import java.util.Arrays;
import java.util.Random;

/**
 * Test superincreasing set generation. Arrays should be superincreasing
 * and contain positive distinct longs.
 */
public class SuperTest {
    private static Random gen;

    public static void main(String[] args) {
        gen = new Random(System.currentTimeMillis());
        int testNum = 1;

        testSuper(testNum++, 2);
        testSuper(testNum++, 3);
        testSuper(testNum++, 5);
        testSuper(testNum++, 10);
        testSuper(testNum++, 15);
        testSuper(testNum++, 20);
        testSuper(testNum++, 25);
        testSuper(testNum++, 30);
        testSuper(testNum++, 35);
        testSuper(testNum++, 40);
    }

    /**
     * Test superincreasing set generator.
     * @param num Test number.
     * @param size Size of superincreasing set to be generated.
     */
    private static void testSuper(int num, int size) {
        System.out.println("Begin test " + num + "...");
        long[] superArray = Super.genSuper(size);
        boolean passed = isSuper(superArray);
        if (isSuper(superArray)) System.out.println("Test " + num + " passed!");
        else System.out.println("Test " + num + " failed. Set is not superincreasing.");
        System.out.println("Superincreasing set: " + Arrays.toString(superArray));
    }

    /**
     * Check if an array is a superincreasing set.
     * @param array The array to be checked.
     * @return A boolean.
     */
    public static boolean isSuper(long[] array) {
        Arrays.sort(array);
        for (int i = 1; i < array.length-1; i++) {
            long cur = 0;
            for (int j = 0; j < i; j++) {
                cur += array[j];
            }
            if (cur >= array[i]) return false;
        }
        return true;
    }
}
