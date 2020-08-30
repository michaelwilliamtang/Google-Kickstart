/* *****************************************************************************
 *  Name: 2020 Google Kickstart C
 *  Date: 6/13/20
 *  Description: Alternate implementation with segment tree
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Candies2 {

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
        int h = (int) (Math.ceil(Math.log(N) / Math.log(2)));
        int N2 = (int) Math.pow(2, h);
        long[] sums = new long[2 * N2];
        long[] mSums = new long[2 * N2];

        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                update(i, arr[i], N2, sums);
                update(i, arr[i] * (i + 1), N2, mSums);
            }
            else {
                update(i, -1 * arr[i], N2, sums);
                update(i, -1 * arr[i] * (i + 1), N2, mSums);
            }
        }
        // System.out.println(Arrays.toString(sums));
        // System.out.println(Arrays.toString(mSums));

        long qSum = 0;
        for (int i = 0; i < Q; i++) {
            if (queries[i] == 'Q') { // query
                int l = param[i][0] - 1;
                int r = param[i][1] - 1;
                long dq = rangeSum(l, r, N2, mSums) -
                        rangeSum(l, r, N2, sums) * l;
                if (l % 2 != 0) dq *= -1;
                qSum += dq;
                // System.out.println(dq);
            }
            else { // update
                int x = param[i][0] - 1;
                long v = param[i][1];
                if (x % 2 != 0) v *= -1;
                update(x, v, N2, sums);
                update(x, v * (x + 1), N2, mSums);
                // System.out.println(Arrays.toString(sums));
                // System.out.println(Arrays.toString(mSums));
            }
        }
        return qSum;
    }

    public static long rangeSum(int a, int b, int N, long[] tree) {
        long s = 0;
        a += N;
        b += N;
        while (a <= b) {
            if (a % 2 != 0) s += tree[a++];
            if (b % 2 == 0) s += tree[b--];
            a /= 2;
            b /= 2;
        }
        return s;
    }

    public static void update(int i, long v, int N, long[] tree) {
        i += N;
        tree[i] = v;
        for (i /= 2; i > 0; i /= 2) tree[i] = tree[2 * i] + tree[2 * i + 1];
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
