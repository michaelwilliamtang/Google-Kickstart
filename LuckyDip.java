/* *****************************************************************************
 *  Name: 2018 Google Kickstart A2
 *  Date: 3/10/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class LuckyDip {
    // precondition: Vi >= 0
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] values = new int[n];
            for (int j = 0; j < n; j++) {
                values[j] = in.nextInt();
            }
            System.out.println("Case #" + i + ": " + expectedValue(n, k, values));
        }
    }

    // calculates suffix means for expectedValue
    private static double[] suffixMeans(int[] arr) {
        double[] sMeans = new double[arr.length];
        double total = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            total += arr[i];
            sMeans[i] = total / (arr.length - i);
        }
        return sMeans;
    }

    // finds first
    // private static int binarySearchLess()

    // calculates expected value with given prizes and number of allowed redips k
    private static double expectedValue(int n, int k, int[] values) {
        // init
        double[] expected = new double[k + 1];
        Arrays.sort(values);
        double[] sMeans = suffixMeans(values);
        System.out.println("Sorted values: " + Arrays.toString(values));
        System.out.println("SuffixMeans: " + Arrays.toString(sMeans));
        expected[0] = sMeans[0];
        // minEscape is smallest index for whose value we do not redip
        int minEscape = n - 1; // will not function at 0, initialized here for first search

        for (int i = 1; i <= k; i++) {
            double ePrev = expected[i - 1];
            // update minEscape
            for (int j = minEscape; j >= 0; j--) {
                if (values[j] < ePrev) {
                    minEscape = j + 1;
                    break;
                }
            }
            // System.out.println("minEscape: " + minEscape);
            // calc probability
            double pEscape = ((double) n - minEscape) / n;
            expected[i] = pEscape * sMeans[minEscape] + (1 - pEscape) * ePrev;
        }
        System.out.println("Expected: " + Arrays.toString(expected));

        return expected[k];
    }
}
