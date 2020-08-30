/* *****************************************************************************
 *  Name: 2020 Google Kickstart B
 *  Date: 6/12/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class RobotPathDecoding {

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            String moves = in.next();

            long[] ans = solve(moves);
            System.out.println("Case #" + i + ": " + ans[0] + " " + ans[1]);
        }
    }

    private static long[] solve(String moves) {
        long MAX = 1000000000;
        Stack<Integer> mod = new Stack<Integer>();
        Stack<Long> curr = new Stack<Long>();
        curr.push(Long.valueOf(1));
        long dy = 0;
        long dx = 0;
        for (int i = 0; i < moves.length(); i++) {
            char c = moves.charAt(i);
            // System.out.println(c);
            switch (c) {
                case 'N':
                    dy -= curr.peek();
                    break;
                case 'S':
                    dy += curr.peek();
                    break;
                case 'W':
                    dx -= curr.peek();
                    break;
                case 'E':
                    dx += curr.peek();
                    break;
                case ')':
                    mod.pop();
                    curr.pop();
                    break;
                default:
                    int val = Character.getNumericValue(c);
                    i++; // ignore opening parenthesis
                    // System.out.println(val);
                    mod.push(val);
                    curr.push(curr.peek() * val % MAX);
                    break;
            }

            if (Math.abs(dy) >= MAX) dy %= MAX;
            if (Math.abs(dx) >= MAX) dx %= MAX;
        }

        if (dy < 0) dy += MAX;
        if (dx < 0) dx += MAX;
        return new long[] { dx + 1, dy + 1 };
    }
}
