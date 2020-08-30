/* *****************************************************************************
 *  Name: 2020 Google Kickstart D
 *  Date: 7/12/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AlienPiano {

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
        int cmin = 0, cmax = 0, max = 0, min = 0;
        int resets = 0;
        for (int i = 1; i < N; i++) {
            if (nums[i] > nums[i - 1]) {
                cmin++;
                if (cmin > max) max++;
                cmax = max;
            }
            else if (nums[i] < nums[i - 1]) {
                cmax--;
                if (cmax < min) min--;
                cmin = min;
            }
            if (max - min + 1 > 4) {
                resets++;
                // System.out.println(max + " " + min);
                max = 0;
                min = 0;
                cmax = 0;
                cmin = 0;
            }
        }
        return resets;
    }
}
