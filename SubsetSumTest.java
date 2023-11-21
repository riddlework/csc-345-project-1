/**
 * File: SubsetSumTest.java
 * Author: Maria Fay Garcia
 * Purpose: Test implementation of approximation algorithms for subset sum problem.
 */
import java.util.ArrayList;
import java.util.Random;

/**
 * Test implementation of approximation algorithms for subset sum problem.
 */
public class SubsetSumTest {
    private static Random gen;

    public static void main(String[] args) {
        gen = new Random(System.currentTimeMillis());
        int testNum = 1;

        // r = 1/2 approx. tests
        System.out.println("Now testing r=1/2 approximation scheme...");
        testHalfApprox(testNum++, 5);
        testHalfApprox(testNum++, 10);
        testHalfApprox(testNum++, 15);
        testHalfApprox(testNum++, 20);
        testHalfApprox(testNum++, 25);
        testHalfApprox(testNum++, 30);
        testHalfApprox(testNum++, 35);
        testHalfApprox(testNum++, 40);

        // FPTAS tests
        testNum = 1;
        System.out.println("Now test fully polynomial time approximation scheme...");
        testFPTAS(testNum++, 5);
        testFPTAS(testNum++, 10);
        testFPTAS(testNum++, 15);
        testFPTAS(testNum++, 20);
        testFPTAS(testNum++, 25);
        testFPTAS(testNum++, 30);
        testFPTAS(testNum++, 35);
        testFPTAS(testNum++, 40);
    }

    /**
     * Test the r=1/2 approximation algorithm for the subset sum problem.
     * @param num The test number.
     * @param size The size of the superincreasing set to be generated.
     */
    private static void testHalfApprox(int num, int size) {
        System.out.println("Begin test " + num + "...");
        long[] superArray = Super.genSuper(size);
        long targetSum = Super.chooseTargetSum(superArray);

        ArrayList<Long> subset = SubsetSum.halfApprox(superArray, targetSum);
        int actualSum = sum(subset);

        float approxRatio = (float) actualSum/targetSum;

        System.out.println("Target sum: " + targetSum);
        System.out.println("Actual sum: " + actualSum);
        System.out.println("Subset: " + subset);
        System.out.println("Approximation ratio = " + approxRatio);
    }

    /**
     * Test the fully polynomial time approximation algorithm for the subset sum problem.
     * @param num The test number.
     * @param size The size of the superincreasing set to be generated.
     */
    private static void testFPTAS(int num, int size) {
        System.out.println("Begin test " + num + "...");
        long[] superArray = Super.genSuper(size);
        long targetSum = Super.chooseTargetSum(superArray);


        long actualSum = SubsetSum.FPTAS(superArray, targetSum);

        float approxRatio = (float) actualSum/targetSum;

        System.out.println("Target sum: " + targetSum);
        System.out.println("Actual sum: " + actualSum);
        System.out.println("Approximation ratio = " + approxRatio);
    }

    /**
     * Return the sum of the given ArrayList of longs.
     * @param array The ArrayList to be summed.
     * @return The integer sum of the ArrayList.
     */
    private static int sum(ArrayList<Long> array) {
        int sum = 0;
        for (int i = 0; i < array.size(); i++) sum += array.get(i);
        return sum;
    }
}