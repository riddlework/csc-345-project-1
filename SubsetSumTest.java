import java.util.Arrays;
import java.util.Random;

public class SubsetSumTest {
    private static Random gen;

    // how to choose target sum? DONE
    // compare sum against target sum for testing

    public static void main(String[] args) {
        gen = new Random(System.currentTimeMillis());

        double score = 0;
        int testNum = 1;
        double ptsPoss = 1.25;

        // basic cases
        // edge cases
        // random cases - these probably need the most code

        // impossible cases
        // empty set case
        // zero case
        // negative case

        //Test 1
        score += test(testNum++, 10, 0, 10, ptsPoss);
        //Test 2
        score += test(testNum++, 20, 0, 10, ptsPoss);
        //Test 3
        score += test(testNum++, 40, 0, 10, ptsPoss);
        //Test 4
        score += test(testNum++, 80, 0, 10, ptsPoss);
        //Test 5
        score += test(testNum++, 160, 0, 100, ptsPoss);
        //Test 6
        score += test(testNum++, 320, -100, 100, ptsPoss);
        //Test 7
        score += test(testNum++, 640, -10, 10, ptsPoss);
        //Test 8
        score += test(testNum++, 1280, 0, 100, ptsPoss);
        System.out.println("Total: " + score);
    }

    private static double test(int num, int size, int min, int max, double pts) {
        System.out.println("Begin test " + num + "...");
        int[] array = getArray(size, min, max);
        int[] copy = getCopy(array);
//        FiveQuick.sort(array);
        Arrays.sort(copy);
        boolean passed = checkArrays(array, copy);
        printMsg(num, passed, pts, array, copy);
        if(passed)
            return pts;
        return 0;
    }

    private static void printMsg(int test, boolean passed, double points,
                                 int[] array, int[] copy) {
        if(passed)
            System.out.println("Test " + test + " passed! (+" + points + " pts)");
        else {
            System.out.println("Test " + test + " failed.");
            System.out.println("Expected: " + Arrays.toString(copy));
            System.out.println("Actual: " + Arrays.toString(array));
        }
    }

    private static int[] getArray(int size, int min, int max) {
        int[] ret = new int[size];
        Random gen = new Random(System.currentTimeMillis());
        for(int i = 0; i < size; i++)
            ret[i] = gen.nextInt(max - min) + min;
        return ret;
    }

    private static int[] getCopy(int[] array) {
        int[] copy = new int[array.length];
        for(int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        return copy;
    }

    private static boolean checkArrays(int[] array1, int[] array2) {
        if(array1.length != array2.length)
            return false;
        for(int i = 0; i < array1.length; i++) {
            if(array1[i] != array2[i])
                return false;
        }
        return true;
    }
}