/* *****************************************************************************
 *  Name: 2020 Google Kickstart A
 *  Date: 6/12/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Parcels {

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int R = in.nextInt();
            int C = in.nextInt();
            boolean[][] office = new boolean[R][C];
            Queue<Pos> toCheck = new LinkedList<Pos>();
            for (int r = 0; r < R; r++) {
                String line = in.next();
                for (int c = 0; c < C; c++) {
                    if (line.charAt(c) == '1') toCheck.add(new Pos(c, r, 0));
                }
            }

            // System.out.println(solveOld(R, C, office, toCheck));
            System.out.println("Case #" + i + ": " + solve(R, C, toCheck));
        }
    }

    private static class Pos {
        int x;
        int y;
        int dist;

        public Pos(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    private static int solve(int R, int C, Queue<Pos> toCheck) {
        // long MAX = 1000000000;

        int[][] distTo = bfs(R, C, toCheck);
        int hi = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (distTo[r][c] > hi) hi = distTo[r][c];
            }
        }
        int lo = 0;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            System.out.println(lo + " " + mid + " " + hi);
            int min1 = R + C, min2 = R + C, max1 = -min1, max2 = -min2;
            boolean skip = true;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (distTo[r][c] > mid) {
                        skip = false;
                        min1 = Math.min(min1, r + c);
                        min2 = Math.min(min2, r - c);
                        max1 = Math.max(max1, r + c);
                        max2 = Math.max(max2, r - c);
                    }
                }
            }
            if (skip) { // don't even need extra office
                hi = mid;
                continue;
            }
            System.out.println(min1 + " " + min2 + " " + max1 + " " + max2);
            boolean poss = false;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    int dist = 0;
                    dist = Math.max(dist, Math.abs(r + c - max1));
                    dist = Math.max(dist, Math.abs(r + c - min1));
                    dist = Math.max(dist, Math.abs(r - c - max2));
                    dist = Math.max(dist, Math.abs(r - c - min2));
                    if (dist <= mid) {
                        poss = true;
                        break;
                    }
                }
                if (poss) break;
            }
            if (poss) {
                // System.out.println("poss");
                // if (mid == lo) return lo;
                hi = mid;
            }
            else lo = mid + 1;
        }
        return lo;
    }

    private static int[][] bfs(int R, int C, Queue<Pos> toCheck) {
        boolean[][] visited = new boolean[R][C];
        int[][] distTo = new int[R][C];
        // int maxDist = 0;
        while (!toCheck.isEmpty()) {
            Pos curr = toCheck.remove();
            int y = curr.y;
            int x = curr.x;
            int d = curr.dist;
            if (y < 0 || x < 0 || y == R || x == C) continue;
            if (visited[y][x]) continue;
            visited[y][x] = true;
            distTo[y][x] = d;
            // if (d > maxDist) {
            //     maxDist = d;
            // }
            toCheck.add(new Pos(x, y - 1, d + 1));
            toCheck.add(new Pos(x, y + 1, d + 1));
            toCheck.add(new Pos(x + 1, y, d + 1));
            toCheck.add(new Pos(x - 1, y, d + 1));
        }
        return distTo;
    }

    // brute force for debug
    // private static int solveOld(int R, int C, boolean[][] office, Queue<Pos> toCheck) {
    //     // long MAX = 1000000000;
    //     int minDist = 1000;
    //     boolean updated = false;
    //     for (int r = 0; r < R; r++) {
    //         for (int c = 0; c < C; c++) {
    //             if (office[r][c]) continue;
    //             office[r][c] = true;
    //             updated = true;
    //             Queue<Pos> toCheckInst = new LinkedList<Pos>(toCheck);
    //             toCheckInst.add(new Pos(c, r, 0));
    //             minDist = Math.min(minDist, bfsOld(R, C, office, toCheckInst));
    //             office[r][c] = false;
    //         }
    //     }
    //     if (!updated) return 0; // all are offices
    //     return minDist;
    // }
    //
    // private static int bfsOld(int R, int C, boolean[][] office, Queue<Pos> toCheck) {
    //     boolean[][] visited = new boolean[R][C];
    //     int[][] distTo = new int[R][C];
    //     int maxDist = 0;
    //     while (!toCheck.isEmpty()) {
    //         Pos curr = toCheck.remove();
    //         int y = curr.y;
    //         int x = curr.x;
    //         int d = curr.dist;
    //         if (y < 0 || x < 0 || y == R || x == C) continue;
    //         if (visited[y][x]) continue;
    //         visited[y][x] = true;
    //         distTo[y][x] = d;
    //         if (d > maxDist) {
    //             maxDist = d;
    //         }
    //         toCheck.add(new Pos(x, y - 1, d + 1));
    //         toCheck.add(new Pos(x, y + 1, d + 1));
    //         toCheck.add(new Pos(x + 1, y, d + 1));
    //         toCheck.add(new Pos(x - 1, y, d + 1));
    //     }
    //     return maxDist;
    // }
}
