/* *****************************************************************************
 *  Name: 2020 Google Kickstart C
 *  Date: 6/13/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Candies {

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = in.nextInt();
            int Q = in.nextInt();

            int[] arr = new int[N];
            for (int j = 0; j < N; j++) arr[j] = in.nextInt();
            char[] queries = new char[Q];
            int[][] param = new int[Q][2];
            for (int j = 0; j < Q; j++) {
                queries[j] = in.next().charAt(0);
                param[j][0] = in.nextInt();
                param[j][1] = in.nextInt();
            }
            // System.out.println("solveOld: " + solveOld(N, Q, arr, queries, param));
            System.out.println("Case #" + i + ": " + solve(N, Q, arr, queries, param));
        }
    }

    private static long solve(int N, int Q, int[] arr, char[] queries, int[][] param) {
        // long MAX = 1000000000;
        long[] sums = new long[N + 1];
        long[] mSums = new long[N + 1];
        long[] vals = new long[N + 1];
        long[] mVals = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            if (i % 2 != 0) {
                vals[i] = arr[i - 1];
                mVals[i] = arr[i - 1] * i;
                update(i, arr[i - 1], sums);
                update(i, arr[i - 1] * i, mSums);
            }
            else {
                vals[i] = -1 * arr[i - 1];
                mVals[i] = -1 * arr[i - 1] * i;
                update(i, -1 * arr[i - 1], sums);
                update(i, -1 * arr[i - 1] * i, mSums);
            }
        }
        // System.out.println(Arrays.toString(sums));
        // System.out.println(Arrays.toString(mSums));

        long qSum = 0;
        for (int i = 0; i < Q; i++) {
            if (queries[i] == 'Q') { // query
                int l = param[i][0] - 1;
                int r = param[i][1];
                long dq = rangeSum(r, mSums) - rangeSum(l, mSums) -
                        (rangeSum(r, sums) - rangeSum(l, sums)) * l;
                if (l % 2 != 0) dq *= -1;
                qSum += dq;
                // System.out.println(dq);
            }
            else { // update
                int x = param[i][0];
                long v = param[i][1];
                if (x % 2 == 0) v *= -1;
                update(x, v - vals[x], sums);
                update(x, v * x - mVals[x], mSums);
                vals[x] = v;
                mVals[x] = v * x;
                // System.out.println(Arrays.toString(sums));
                // System.out.println(Arrays.toString(mSums));
            }
        }
        return qSum;
    }

    public static long rangeSum(int i, long[] tree) {
        long s = 0;
        while (i > 0) {
            s += tree[i];
            i -= i & (-i);
        }
        return s;
    }

    public static void update(int i, long v, long[] tree) {
        while (i < tree.length) {
            tree[i] += v;
            i += i & (-i);
        }
    }

    // private static int solveOld(int N, int Q, int[] arr, char[] queries, int[][] param) {
    //     // long MAX = 1000000000;
    //     int qSum = 0;
    //     arr = arr.clone();
    //     for (int i = 0; i < Q; i++) {
    //         if (queries[i] == 'Q') {
    //             // query
    //             int maxJ = param[i][1] - param[i][0];
    //             int prev = qSum;
    //             for (int j = 0; j <= maxJ; j++) {
    //                 if (j % 2 == 0) qSum += arr[param[i][0] - 1 + j] * (j + 1);
    //                 else qSum -= arr[param[i][0] - 1 + j] * (j + 1);
    //             }
    //             // System.out.println(qSum - prev);
    //         }
    //         else {
    //             // update
    //             arr[param[i][0] - 1] = param[i][1];
    //         }
    //     }
    //     return qSum;
    // }
}
