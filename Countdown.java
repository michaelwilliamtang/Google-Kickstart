/* *****************************************************************************
 *  Name: 2020 Google Kickstart C
 *  Date: 6/13/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Countdown {

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = in.nextInt();
            int K = in.nextInt();

            int[] nums = new int[N];
            for (int j = 0; j < N; j++) nums[j] = in.nextInt();
            System.out.println("Case #" + i + ": " + solve(N, K, nums));
        }
    }

    private static int solve(int N, int K, int[] nums) {
        // long MAX = 1000000000;
        int count = 0;
        boolean counting = false;
        int curr = -1;
        for (int i = 0; i < N; i++) {
            if (counting) {
                if (curr == nums[i]) {
                    if (curr == 1) {
                        counting = false;
                        count++;
                    }
                    else curr--;
                }
                else counting = false;
            }
            if (nums[i] == K) {
                counting = true;
                curr = K - 1;
            }
        }
        return count;
    }
}
