/* *****************************************************************************
 *  Name: 2020 Google Kickstart A
 *  Date: 3/21/20 -> 6/12/20 revisit
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Plates {
    public static void main(String[] args) {
        // test100(1L, (long) Math.pow(10, 16));

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = in.nextInt();
            int K = in.nextInt();
            int P = in.nextInt();

            // int[][] stacks = new int[N][K];
            int[][] sums = new int[N][K];
            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int k = 0; k < K; k++) {
                    int val = in.nextInt();
                    // stacks[j][k] = val;
                    sum += val;
                    sums[j][k] = sum;
                }
            }

            System.out.println("Case #" + i + ": " + solve(N, K, P, sums));
        }
    }

    // private static void test100(long min, long max) {
    //     for (int i = 0; i < 100; i++) {
    //         String num = Long.toString(min + (long) (Math.random() * (max - min)));
    //         System.out.println(
    //                 "Case " + i + ": " + num + " " + maxHouses(num));
    //     }
    // }

    private static int solve(int N, int K, int P, int[][] sums) {
        int[][] dp = new int[P + 1][N];
        for (int p = 0; p <= P; p++) {
            for (int n = 0; n < N; n++) { // n is index
                if (p == 0) {
                    dp[p][n] = 0;
                    continue;
                }
                if (n == 0) {
                    dp[p][n] = sums[n][Math.min(p - 1, K - 1)];
                    continue;
                }
                int max = dp[p][n - 1];
                for (int k = 1; k <= Math.min(p, K); k++) { // k is # plates, not index
                    max = Math.max(max, dp[p - k][n - 1] + sums[n][k - 1]);
                }
                dp[p][n] = max;
            }
        }
        // for (int[] arr : dp) System.out.println(Arrays.toString(arr));
        return dp[P][N - 1];
    }
}
