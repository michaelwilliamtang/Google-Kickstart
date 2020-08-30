/* *****************************************************************************
 *  Name: 2020 Google Kickstart D
 *  Date: 7/12/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RecordBreaker {

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = in.nextInt();
            int[] nums = new int[N];
            for (int j = 0; j < N; j++) nums[j] = in.nextInt();

            System.out.println("Case #" + i + ": " + solve(N, nums));
        }
    }

    private static int solve(int N, int[] nums) {
        // long MAX = 1000000000;
        int ct = 0;
        int max = -1;
        for (int i = 0; i < N; i++) {
            if ((max == -1 || nums[i] > max) && (i == N - 1 || nums[i] > nums[i + 1])) {
                // System.out.println(i);
                ct++;
            }
            max = Math.max(max, nums[i]);
        }
        return ct;
    }
}
