/* *****************************************************************************
 *  Name: 2020 Google Kickstart B
 *  Date: 6/12/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class WanderingRobot {

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int W = in.nextInt();
            int H = in.nextInt();
            int L = in.nextInt();
            int U = in.nextInt();
            int R = in.nextInt();
            int D = in.nextInt();

            // double val = solve(W, H, L, U, R, D);
            // double val2 = solveOld(W, H, L, U, R, D);
            // System.out.println(val + " " + val2);
            System.out.println("Case #" + i + ": " + solve(W, H, L, U, R, D));
        }
    }

    private static double solve(int W, int H, int L, int U, int R, int D) {
        // precompute log factorials, using dp
        int maxN = Math.max(L + D - 2, R + U - 2);
        double[] logfact = new double[maxN + 1];
        for (int n = 2; n <= maxN; n++) { // log (1) = 0
            logfact[n] = logfact[n - 1] + Math.log(n) / Math.log(2); // base 2
        }

        // sum probs of passable squares for down/left
        double prob = 0;
        int y = D + 1;
        int x = L - 1;
        while (y <= H && x >= 1) { // 1-indexed
            // System.out.println(x + " " + y);
            if (y == H) {
                y--;
                while (x >= 1) {
                    prob += comb(x + y - 2, x - 1, logfact) * 0.5;
                    x--;
                }
            }
            else prob += comb(x + y - 2, x - 1, logfact);
            y++;
            x--;
        }
        // System.out.println(prob);

        // sum probs of passable squares for right/down
        double prob2 = 0;
        y = U - 1;
        x = R + 1;
        while (y >= 1 && x <= W) { // 1-indexed
            // System.out.println(x + " " + y);
            if (x == W) {
                x--;
                while (y >= 1) {
                    prob2 += comb(x + y - 2, x - 1, logfact) * 0.5;
                    y--;
                }
            }
            else prob2 += comb(x + y - 2, x - 1, logfact);
            y--;
            x++;
        }
        // System.out.println(prob2);

        // add each way if possible
        return prob + prob2;
    }

    private static double comb(int n, int k, double[] logfact) {
        double val = logfact[n] - logfact[k] - logfact[n - k] - n;
        return Math.pow(2, val);
    }

    // use brute-force to check
    // private static double solveOld(int W, int H, int L, int U, int R, int D) {
    //     double[] curr = new double[W + 1]; // 0 unused
    //     double[] next = new double[W + 1];
    //     curr[1] = 1;
    //     double failP = 0;
    //     for (int i = 1; i <= D; i++) {
    //         for (int j = 1; j <= R; j++) {
    //             if (j >= L && j <= R && i >= U && i <= D) { // hole, no next
    //                 failP += curr[j];
    //                 continue;
    //             }
    //             if (i == H && j == W) continue; // end, no next
    //             else if (i == H) curr[j + 1] += curr[j]; // one poss
    //             else if (j == W) next[j] += curr[j]; // one poss
    //             else { // two poss
    //                 curr[j + 1] += 0.5 * curr[j];
    //                 next[j] += 0.5 * curr[j];
    //             }
    //         }
    //         System.out.println(Arrays.toString(curr));
    //         if (i < H) {
    //             curr = next;
    //             next = new double[W + 1];
    //         }
    //     }
    //     System.out.println(Arrays.toString(curr));
    //
    //     return 1 - failP;
    // }
}
