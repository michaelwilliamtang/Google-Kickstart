/* *****************************************************************************
 *  Name: 2020 Google Kickstart A
 *  Date: 6/12/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Workout {
    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = in.nextInt();
            int K = in.nextInt();

            int[] minutes = new int[N];
            for (int m = 0; m < N; m++) minutes[m] = in.nextInt();

            System.out.println("Case #" + i + ": " + solve(N, K, minutes));
        }
    }

    private static int solve(int N, int K, int[] minutes) {
        // calc diffs
        int[] diffs = new int[N - 1];
        int prev = minutes[0];
        for (int i = 1; i < N; i++) {
            diffs[i - 1] = minutes[i] - prev;
            prev = minutes[i];
        }
        int D = diffs.length;

        // find max diff
        int maxDiff = 0;
        for (int i = 0; i < D; i++) maxDiff = Math.max(maxDiff, diffs[i]);

        // binary search minimum poss with given K
        int lo = 1;
        int hi = maxDiff;
        int kNeeded, mid;
        do {
            mid = (lo + hi) / 2;
            // System.out.println(lo + " " + mid + " " + hi);
            kNeeded = 0;
            for (int i = 0; i < D; i++) kNeeded += (int) Math.ceil((double) diffs[i] / mid) - 1;
            if (kNeeded <= K) hi = mid; // this could be our max, mid is viable
            else lo = mid + 1; // mid is unviable
        }
        while (lo != hi);

        return lo;
    }
}
