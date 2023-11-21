/**
 * File: SubsetSum.Java
 * Author: Maria Fay Garcia
 * Purpose: To implement several approximation algorithms of the subset sum problem.
 */
import java.util.*;


public class SubsetSum {
    // Given a superincreasing set of positive integers A and a positive integer c, find a subset of A
    // which sums to c, using a greedy algorithm.

    // r is the approximation

    // r=1/2 approximation
    public static ArrayList<Integer> halfApprox(int[] set, int target) {
        ArrayList<Integer> subset = new ArrayList<>();

        // sort the inputs in descending order
        dSort(set);

        // initialize current sum
        int curSum = 0;

        // repeatedly put the next-largest input into the subset, as long as it fits there.
        for (int i = 0; i < set.length; i++) {
            int cur = set[i];
            if (curSum + cur <= target) {
                subset.add(cur);
                curSum += cur;
            }
        }

        return subset;
    }


    // Auxiliary methods for halfApprox:

    /**
     * Sort an ArrayList of Integers in ascending order using insertion sort.
     * @param a The ArrayList to be sorted.
     */
    public static void aSort(ArrayList<Integer> a) {
        for (int i = 0; i < a.size(); i++) {
            int j = i;
            while (j-1 >= 0) {
                if (a.get(j) < a.get(j-1)) {
                    swap(a, i, j);
                    j--;
                } else break;
            }
        }
    }

    /**
     * Sort an array in descending order using insertion sort.
     * @param a The array to be sorted
     */
    public static void dSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int j = i;
            while (j-1 >= 0) {
                if (a[j] > a[j-1]) {
                    swap(a, j, j-1);
                    j--;
                } else break;
            }
        }
    }


    // -------------------------------------------------- //

    // fully polynomial time approximation
    public static int FPTAS(int[] set, int targetSum) {
        if (targetSum == 0) return 0;

        // Initialize a list list to contain one element 0
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);

        // for each i from 1 to n
        for (int i = 1; i < set.length; i++) {
            // Let Ui be a list containing all elements min in list, and all sums xi + min for all min in list.
            ArrayList<Integer> curSums = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                curSums.add(list.get(j));
                curSums.add(list.get(j) + set[i]);
            }

            // sort curSums in ascending order
            aSort(curSums);

            // make list empty
            list = new ArrayList<>();

            // let min be the smallest element of curSums
            int min = min(curSums);

            // add min to list
            list.add(min);

            for (int j = 0; j < curSums.size(); j++) {
                Integer z = curSums.get(j);

                // Prune possibilities by deleting numbers that are close together
                if ((min + targetSum)/set.length < z && z <= targetSum) {
                    min = z;
                    list.add(z);
                }
            }
        }

        // return the maximum element of the list, which is the solution
        return max(list);
    }


    // FPTAS auxiliary methods:
    public static int min(ArrayList<Integer> set) {
        if (set.isEmpty()) throw new IllegalArgumentException("Set must be non-empty.");
        int min = set.get(0);
        for (int i = 0; i < set.size(); i++) {
            int cur = set.get(i);
            if (cur < min) min = cur;
        }
        return min;
    }

    public static int max(ArrayList<Integer> set) {
        if (set.isEmpty()) throw new IllegalArgumentException("Set must be non-empty.");
        int max = set.get(0);
        for (int i = 0; i < set.size(); i++) {
            int cur = set.get(i);
            if (cur > max) max = cur;
        }
        return max;
    }


    /**
     * Swap two elements of the given array.
     * @param array The array to be modified.
     * @param i The first integer index.
     * @param j The second integer index.
     */
    private static void swap(int[] array, int i, int j) {
        if (i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private static void swap(ArrayList<Integer> array, int i, int j) {
        if (i != j) {
            Integer temp = array.get(i);
            array.set(i, array.get(j));
            array.set(j, temp);
        }
    }

    public static void main(String[] args) {
        int[] superArray = {2, 4, 8, 16, 32, 64, 128, 256, 512};
//        System.out.println(Arrays.toString(superArray));
//        System.out.println("length: " + superArray.length);
//        int[] superArray = Super.genSuper(10);
        int targetSum = Super.chooseTargetSum(superArray);
        System.out.println(targetSum);
//        ArrayList<Integer> result = PTAS(superArray, 24);
        System.out.println(FPTAS(superArray, 0));
    }
}

