/* *****************************************************************************
 *  Name: 2020 Google Kickstart A
 *  Date: 6/12/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Contention {

    private static class Interval {
        int lo, hi;

        private boolean overlaps(Interval iv2) {
            return iv2.hi >= lo && hi >= iv2.lo;
        }
    }

    private static class Node {
        Interval iv;
        int max;
        Node left, right;

        public Node(Interval iv) {
            this.iv = iv;
            max = iv.hi;
        }
    }

    private static Node insert(Node root, Interval iv) {
        if (root == null) return new Node(iv);
        if (iv.lo < root.iv.lo) root.left = insert(root.left, iv);
        else root.right = insert(root.right, iv);
        root.max = Math.max(root.max, iv.hi);
        return root;
    }

    private static Node remove(Node root, Interval iv)

    private static Interval search(Node root, Interval iv) {
        if (root == null) return null;
        if (root.iv.overlaps(iv)) return root.iv;
        if (root.left != null && iv.lo <= root.left.max) return search(root.left, iv);
        return search(root.right, iv);
    }

    private static Interval searchAll(Node root, Interval iv, List<Interval> res) {
        if (root == null) return null;
        if (overlaps(root.iv, iv)) res.add(root.iv);
        if (root.left != null && iv.lo <= root.left.max) return search(root.left, iv);
        return search(root.right, iv);
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = in.nextInt();
            int Q = in.nextInt();
            ArrayList<Range> ranges = new ArrayList<Range>();
            ArrayList<Range> rangesL = new ArrayList<Range>();
            for (int q = 0; q < Q; q++) {
                Range ra = new Range(in.nextInt(), in.nextInt());
                ranges.add(ra);
                rangesL.add(ra);
            }
            Collections.sort(ranges);
            Collections.sort(rangesL);
            System.out.println("Case #" + i + ": " + solve(N, Q, ranges, rangesL));
        }
    }

    private static int solve(int N, int Q, ArrayList<Range> ranges, ArrayList<Range> rangesL) {
        // long MAX = 1000000000;
        int k = N;
        while (!ranges.isEmpty()) {
            Range ra = ranges.remove();
            int count = 0;
            for (int i = ra.l; i <= ra.r; i++) {
                if (!taken[i]) {
                    taken[i] = true;
                    count++;
                }
            }
            k = Math.min(k, count);
        }
        return k;
    }
}
