/* *****************************************************************************
 *  Name: 2020 Google Kickstart A
 *  Date: 6/12/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Template {

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
        return -1;
    }
}
