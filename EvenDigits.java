/* *****************************************************************************
 *  Name: 2018 Google Kickstart A1
 *  Date: 3/8/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class EvenDigits {
    // precondition: num given > 0
    public static void main(String[] args) {
        // test100(1L, (long) Math.pow(10, 16));
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            System.out.println("Case #" + i + ": " + distToEven(in.next()));
        }
    }

    private static void test100(long min, long max) {
        for (int i = 0; i < 100; i++) {
            String num = Long.toString(min + (long) (Math.random() * (max - min)));
            System.out.println(
                    "Case " + i + ": " + num + " " + distToEven(num));
        }
    }

    // finds distance to closest even-digit number
    private static long distToEven(String s) {
        int firstOdd = firstOdd(s);
        if (firstOdd == -1) return 0; // check if even-digit already

        // compute possible eve-digit dneighbors
        long actual = Long.parseLong(s);
        long floor = Long.parseLong(evenFloor(s, firstOdd));
        long ceiling = Long.parseLong(evenCeiling(s, firstOdd));

        // pick closer neighbor
        // System.out.println("floor: " + floor);
        // System.out.println("ceiling: " + ceiling);
        if (actual - floor < ceiling - actual) // floor closer
            return actual - floor;
        return ceiling - actual; // ceiling closer
    }

    // finds position of first odd digit
    private static int firstOdd(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.getNumericValue(s.charAt(i)) % 2 != 0) return i;
        }
        return -1;
    }

    // find largest all-even-digit num less than s
    private static String evenFloor(String s, int pos) {
        // we can always decrease, as smallest odd = 1 > 0
        int newDigit = Character.getNumericValue(s.charAt(pos)) - 1;

        // check if digit is now leading zero; if so we omit
        // exception: when number is exactly 1, we keep the 0
        if (newDigit == 0 && pos == 0 && s.length() > 1)
            return replaceRest(s, pos + 1, '8');
        return s.substring(0, pos) + newDigit + replaceRest(s, pos + 1, '8');
    }

    // find smallest all-even-digit num > val
    private static String evenCeiling(String s, int pos) {
        // we can increase unless digit is 9; if so we must backtrack to round up
        // hence we generalize this function to be able to recurse on itself for even digits
        if (pos == -1) { // no digits < 8 in backtracking
            return "2" + replaceRest(s, 0, '0');
        }
        int digit = Character.getNumericValue(s.charAt(pos));
        if (digit >= 8) {
            return evenCeiling(s, pos - 1);
        }

        int newDigit;
        if (digit % 2 != 0) {
            newDigit = digit + 1;
        }
        else {
            newDigit = digit + 2;
        }
        return s.substring(0, pos) + newDigit + replaceRest(s, pos + 1, '0');
    }

    // replace remaining digits with character c
    private static String replaceRest(String s, int start, char c) {
        if (start >= s.length()) return ""; // avoid useless StringBuilders
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < s.length(); i++) {
            sb.append(c);
        }
        return sb.toString();
    }
}
