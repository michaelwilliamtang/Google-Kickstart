/* *****************************************************************************
 *  Name: 2020 Google Kickstart A
 *  Date: 6/12/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Training {

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = in.nextInt();
            int P = in.nextInt();
            int[] skills = new int[N];
            for (int j = 0; j < N; j++) skills[j] = in.nextInt();
            System.out.println("Case #" + i + ": " + solve(N, P, skills));
        }
    }

    private static long solve(int N, int P, int[] skills) {
        // long MAX = 1000000000;
        Arrays.sort(skills);
        long sum = 0;
        int curr = skills[P - 1];
        for (int i = 0; i < P; i++) sum += curr - skills[i];
        long min = sum;
        for (int i = P; i < N; i++) {
            sum += (skills[i] - curr) * P;
            sum -= skills[i] - skills[i - P];
            curr = skills[i];
            // System.out.println(sum);
            min = Math.min(sum, min);
        }
        return min;
    }
}
