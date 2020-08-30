/* *****************************************************************************
 *  Name: 2020 Google Kickstart C
 *  Date: 6/13/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;

public class PerfectSubarray {

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = in.nextInt();
            int[] arr = new int[N];
            for (int j = 0; j < N; j++) arr[j] = in.nextInt();

            // System.out.println(solveOld(N, arr) + " old");
            System.out.println("Case #" + i + ": " + solve(N, arr));
        }
    }

    private static long solve(int N, int[] arr) {
        // precompute perfect squares
        int maxSum = 100 * N;
        int maxRoot = (int) Math.sqrt(maxSum);
        int[] squares = new int[maxRoot + 1];
        for (int i = 0; i <= maxRoot; i++) squares[i] = i * i;

        // find offset for prefix sum lookup (max neg prefix sum, or 0)
        int offset = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            if (sum < offset) offset = sum;
        }
        offset *= -1;

        // build numSubs, perfectSubs at same time
        long[] numSubs = new long[maxSum + 1]; // num prefix subarrs summing to i
        // int[] perfectSubs = new int[N]; // num prefix subarrs summing to perfect sq, ending at i
        long val = 0;
        sum = 0;
        numSubs[sum + offset]++;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            for (int s = 0; s <= maxRoot; s++) {
                if (squares[s] - sum > offset) continue;
                val += numSubs[sum - squares[s] + offset];
            }
            numSubs[sum + offset]++;
        }
        // System.out.println(Arrays.toString(numSubs));
        return val;
    }

    private static int solveOld(int N, int[] arr) {
        // long MAX = 1000000000;
        // max sum is 10^7
        int[] sums = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sums[i] = sums[i - 1] + arr[i - 1];
        }
        HashSet<Integer> perfect = new HashSet<Integer>();

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (sums[j] - sums[i] < 0) break; // no negs
                if (perfect.contains(sums[j] - sums[i])) count++;
                else {
                    double val = Math.sqrt(sums[j] - sums[i]);
                    if (val == (int) val) {
                        perfect.add(sums[j] - sums[i]); // memo
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
