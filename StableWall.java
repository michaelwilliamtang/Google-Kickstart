/* *****************************************************************************
 *  Name: 2020 Google Kickstart C
 *  Date: 6/13/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class StableWall {

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int R = in.nextInt();
            int C = in.nextInt();

            String[] wall = new String[R];
            for (int r = 0; r < R; r++) wall[r] = in.next();
            System.out.println("Case #" + i + ": " + solve(R, C, wall));
        }
    }

    private static String solve(int R, int C, String[] wall) {
        // long MAX = 1000000000;
        int[] stable = new int[26]; // mark 1, 0, -1 = not queried
        for (int i = 0; i < 26; i++) stable[i] = -1;
        boolean[][] checked = new boolean[R][C];
        StringBuilder sb = new StringBuilder();
        for (int r = R - 1; r >= 0; r--) {
            for (int c = C - 1; c >= 0; c--) {
                if (checked[r][c]) continue;
                char id = wall[r].charAt(c);
                if (!isStable(id, r, c, R, C, wall, checked, stable, sb)) return "-1";
                stable[id - 'A'] = 1;
                sb.append(id);
            }
        }
        return sb.toString();
    }

    private static boolean isStable(char id, int r, int c, int R, int C,
                                    String[] wall, boolean[][] checked, int[] stable,
                                    StringBuilder sb) {
        // skip
        if (r < 0 || c < 0 || r == R || c == C) return true;
        if (wall[r].charAt(c) != id || checked[r][c]) return true;

        // check this
        // System.out.println(id + " " + r + " " + c);
        checked[r][c] = true;
        if (r != R - 1 && wall[r + 1].charAt(c) != id &&
                stable[wall[r + 1].charAt(c) - 'A'] != 1) {
            if (stable[wall[r + 1].charAt(c) - 'A'] == 0) return false;
            if (stable[wall[r + 1].charAt(c) - 'A'] == -1) {
                stable[id - 'A'] = 0; // prevent calling each other infinitely
                if (!isStable(wall[r + 1].charAt(c), r + 1, c, R, C, wall, checked, stable, sb))
                    return false;
                stable[wall[r + 1].charAt(c) - 'A'] = 1;
                sb.append(wall[r + 1].charAt(c));
                stable[id - 'A'] = -1;
            }
        }

        // recurse, check neighbors
        if (!isStable(id, r - 1, c, R, C, wall, checked, stable, sb)) return false;
        if (!isStable(id, r + 1, c, R, C, wall, checked, stable, sb)) return false;
        if (!isStable(id, r, c - 1, R, C, wall, checked, stable, sb)) return false;
        if (!isStable(id, r, c + 1, R, C, wall, checked, stable, sb)) return false;

        return true;
    }
}
