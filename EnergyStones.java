/* *****************************************************************************
 *  Name: 2019 Google Kickstart B
 *  Date: 7/3/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class EnergyStones {

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = in.nextInt();
            Stone[] stones = new Stone[N];
            for (int j = 0; j < N; j++)
                stones[j] = new Stone(in.nextInt(),
                                      in.nextInt(), in.nextInt());

            System.out.println("Case #" + i + ": " + solve(N, stones));
        }
    }

    private static class Stone implements Comparable<Stone> {
        int S, E, L;

        public Stone(int s, int e, int l) {
            S = s;
            E = e;
            L = l;
        }

        public int compareTo(Stone s) {
            if (L * s.S == s.L * S) return Integer.compare(E, s.E);
            return Integer.compare(s.L * S, L * s.S);
        }
    }

    private static int solve(int N, Stone[] stones) {
        // long MAX = 1000000000;
        Arrays.sort(stones);
        int maxTime = 0;
        for (Stone s : stones) maxTime += s.S;

        // debug
        // for (Stone s : stones) System.out.println(s.S + " " + s.E + " " + s.L);

        int[][] dp = new int[N][maxTime + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int t = 0; t <= maxTime; t++) {
                if (i == N - 1) dp[i][t] = Math.max(stones[i].E - stones[i].L * t, 0);
                else {
                    if (t + stones[i].S > maxTime) dp[i][t] = dp[i + 1][t]; // filler
                    else {
                        dp[i][t] = Math.max(dp[i + 1][t], dp[i + 1][t + stones[i].S]
                                + Math.max(stones[i].E - stones[i].L * t, 0));
                    }
                }
            }
        }
        return dp[0][0];
    }
}
