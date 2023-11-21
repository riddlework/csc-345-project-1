/**
 * File: SubsetSum.Java
 * Author: Maria Fay Garcia
 * Purpose: To implement several approximation algorithms of the subset sum problem.
 */
import java.util.*;

/**
 * Given a superincreasing set of positive Longs and a target sum,
 * the subset sum problem asks us to find a subset of the set which sums
 * to the target sum.
 *
 * We approximate solutions to the subset sum problem here using two greedy
 * algorithms. Our approximations find a subset with a sum of at least r
 * times the target sum.
 */
public class SubsetSum {
    /**
     * This approximation guarantees an r value of 1/2.
     * @param set The superincreasing set.
     * @param targetSum The long target sum to be found.
     * @return A subset of the input set whose elements sum to at least 1/2
     *      times the target sum.
     */
    public static ArrayList<Long> halfApprox(long[] set, long targetSum) {
        if (targetSum < 0) throw new IllegalArgumentException("Target sum must be greater than 0");
        ArrayList<Long> subset = new ArrayList<>();

        // sort the inputs in descending order
        dSort(set);

        // initialize current sum
        long curSum = 0;

        // repeatedly put the next-largest input into the subset, as long as it fits there.
        for (int i = 0; i < set.length; i++) {
            long cur = set[i];
            if (curSum + cur <= targetSum) {
                subset.add(cur);
                curSum += cur;
            }
        }

        return subset;
    }


    // Auxiliary methods for halfApprox:

    /**
     * Sort an ArrayList of Longs in ascending order using insertion sort.
     * @param a The ArrayList to be sorted.
     */
    public static void aSort(ArrayList<Long> a) {
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
     * Sort a primitive array in descending order using insertion sort.
     * @param a The array to be sorted
     */
    public static void dSort(long[] a) {
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


    /**
     * A fully polynomial time approximation to the subset sum problem.
     * @param set The superincreasing set.
     * @param targetSum The target sum.
     * @return The Long sum of the solution subset.
     */
    public static long FPTAS(long[] set, long targetSum) {
        if (targetSum < 0) throw new IllegalArgumentException("Target sum must be greater than zero.");
        if (targetSum == 0) return 0;

        // Initialize a list list to contain one element 0
        ArrayList<Long> list = new ArrayList<>();
        list.add((long) 0);

        // for each i from 1 to n
        for (int i = 1; i < set.length; i++) {
            // Let Ui be a list containing all elements min in list, and all sums xi + min for all min in list.
            ArrayList<Long> curSums = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                curSums.add(list.get(j));
                curSums.add(list.get(j) + set[i]);
            }

            // sort curSums in ascending order
            aSort(curSums);

            // make list empty
            list = new ArrayList<>();

            // let min be the smallest element of curSums
            long min = min(curSums);

            // add min to list
            list.add(min);

            for (int j = 0; j < curSums.size(); j++) {
                Long z = curSums.get(j);

                // Prune possibilities by not considering numbers that are close together
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

    /**
     * Find the minimum of an ArrayList of Longs.
     * @param set The ArrayList whose min we want to find.
     * @return The minimum of the ArrayList.
     */
    public static long min(ArrayList<Long> set) {
        if (set.isEmpty()) throw new IllegalArgumentException("Set must be non-empty.");
        long min = set.get(0);
        for (int i = 0; i < set.size(); i++) {
            long cur = set.get(i);
            if (cur < min) min = cur;
        }
        return min;
    }

    /**
     * Find the maximum of an ArrayList of Longs.
     * @param set The ArrayList whose max we want to find.
     * @return The maximum of the ArrayList.
     */
    public static long max(ArrayList<Long> set) {
        if (set.isEmpty()) throw new IllegalArgumentException("Set must be non-empty.");
        long max = set.get(0);
        for (int i = 0; i < set.size(); i++) {
            long cur = set.get(i);
            if (cur > max) max = cur;
        }
        return max;
    }


    /**
     * Swap two elements of the given array.
     * @param array The array to be modified.
     * @param i The first Long index.
     * @param j The second Long index.
     */
    private static void swap(long[] array, int i, int j) {
        if (i != j) {
            long temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    /**
     * Swap two elements of the given ArrayList.
     * @param array The ArrayList whose elements we want to swap.
     * @param i The first Long index.
     * @param j The second Long index.
     */
    private static void swap(ArrayList<Long> array, int i, int j) {
        if (i != j) {
            Long temp = array.get(i);
            array.set(i, array.get(j));
            array.set(j, temp);
        }
    }
}

