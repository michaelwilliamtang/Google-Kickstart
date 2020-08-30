/* *****************************************************************************
 *  Name: 2018 Google Kickstart A
 *  Date: 3/8/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Allocation {
    public static void main(String[] args) {
        // test100(1L, (long) Math.pow(10, 16));
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = in.nextInt();
            int B = in.nextInt();
            int[] costs = new int[N];
            for (int j = 0; j < N; j++) {
                costs[j] = in.nextInt();
            }
            System.out.println("Case #" + i + ": " + maxHouses(costs, B));
        }
    }

    // private static void test100(long min, long max) {
    //     for (int i = 0; i < 100; i++) {
    //         String num = Long.toString(min + (long) (Math.random() * (max - min)));
    //         System.out.println(
    //                 "Case " + i + ": " + num + " " + maxHouses(num));
    //     }
    // }

    private static int maxHouses(int[] costs, int B) {
        int houses = 0;
        Arrays.sort(costs);
        for (int i = 0; i < costs.length; i++) {
            int cost = costs[i];
            if (cost > B) break;
            B -= cost;
            houses++;
        }
        return houses;
    }
}
