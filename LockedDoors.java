/* *****************************************************************************
 *  Name: 2020 Google Kickstart D
 *  Date: 7/12/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LockedDoors {

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = in.nextInt();
            int Q = in.nextInt();
            int[] nums = new int[N];
            int[] ks = new int[Q];
            int[] starts = new int[Q];
            for (int j = 0; j < N; j++) nums[j] = in.nextInt();
            for (int j = 0; j < Q; j++) {
                starts[j] = in.nextInt();
                ks[j] = in.nextInt();
            }

            System.out.println("Case #" + i + ": " + solve(N, Q, nums, ks, starts));
        }
    }

    private static int solve(int N, int Q, int[] nums, int[] ks, int[] starts) {


        // long MAX = 1000000000;
        return -1;
    }
}
