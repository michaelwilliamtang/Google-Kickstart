/* *****************************************************************************
 *  Name: 2020 Google Kickstart B
 *  Date: 6/12/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BusRoutes {

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = in.nextInt();
            long D = in.nextLong();

            long[] nums = new long[N];
            for (int j = 0; j < N; j++) nums[j] = in.nextLong();

            System.out.println("Case #" + i + ": " + solve(N, D, nums));
        }
    }

    private static long solve(int N, long D, long[] nums) {
        long curr = D;
        for (int i = nums.length - 1; i >= 0; i--) {
            curr = curr / nums[i] * nums[i];
        }
        return curr;
    }
}
