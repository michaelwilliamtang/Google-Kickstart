/* *****************************************************************************
 *  Name: 2019 Google Kickstart B
 *  Date: 7/3/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class DiverseSubarray {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = in.nextInt();
            int S = in.nextInt();
            int[] types = new int[N];
            for (int j = 0; j < N; j++) types[j] = in.nextInt();

            System.out.println("Case #" + i + ": " + solve(N, S, types));
        }

        // test segment tree
        // int N = (int) Math.pow(2, Math.ceil(Math.log(11) / Math.log(2)));
        // int[] tree = new int[2 * N];
        // int[] max = new int[2 * N];
        // int[] arr = { -1, 10, 3, -5, -3, 9, 8, -1, 7, 2, 3 };
        // for (int i = 0; i < arr.length; i++) update(arr[i], i, tree, max, N);
        // System.out.println(Arrays.toString(tree));
        // System.out.println(Arrays.toString(max));
        // for (int i = 0; i < arr.length; i++)
        //     System.out.println(maxPrefixSum(i, arr.length - 1, tree, max, N));
    }

    private static void update(int val, int i, int[] tree, int[] max, int N) {
        i += N;
        tree[i] = val;
        max[i] = Math.max(0, val);
        for (i /= 2; i >= 1; i /= 2) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
            max[i] = Math.max(max[2 * i], tree[2 * i] + max[2 * i + 1]);
        }
    }

    private static int maxPrefixSum(int l, int r, int[] tree, int[] max, int N) {
        l += N;
        r += N;
        return maxPrefixSumHelper(l, r, tree, max);
    }

    private static int maxPrefixSumHelper(int l, int r, int[] tree, int[] max) {
        // System.out.println("prefixSum " + l + " " + r);
        if (l == r) return max[l];
        if (l % 2 != 0)
            return Math.max(max[l], tree[l] + maxPrefixSumHelper(l + 1, r, tree, max));
        if (r % 2 == 0) return Math.max(maxPrefixSumHelper(l, r - 1, tree, max),
                                        sumHelper(l, r - 1, tree) + max[r]);
        return maxPrefixSumHelper(l / 2, r / 2, tree, max);
    }

    private static int sumHelper(int l, int r, int[] tree) {
        int s = 0;
        while (l <= r) {
            if (l % 2 != 0) s += tree[l++];
            if (r % 2 == 0) s += tree[r--];
            l /= 2;
            r /= 2;
        }
        return s;
    }

    private static int solve(int N, int S, int[] types) {
        // build diff
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>(); // diff for index
        HashMap<Integer, LinkedList<Integer>> next
                = new HashMap<Integer, LinkedList<Integer>>(); // instances of type
        int[] diff = new int[N];
        for (int i = 0; i < N; i++) {
            if (!hm.containsKey(types[i])) {
                hm.put(types[i], 1);
                diff[i] = 1;
            }
            else {
                int alr = hm.get(types[i]);
                if (alr == S) {
                    hm.put(types[i], 0);
                    LinkedList<Integer> ll = new LinkedList<Integer>();
                    ll.add(i);
                    next.put(types[i], ll);
                    diff[i] = -S;
                }
                else if (alr == 0) {
                    next.get(types[i]).add(i);
                    diff[i] = 0;
                }
                else {
                    hm.put(types[i], alr + 1);
                    diff[i] = 1;
                }
            }
        }
        // System.out.println(Arrays.toString(diff));

        // initial
        int N2 = (int) Math.pow(2, Math.ceil(Math.log(N) / Math.log(2)));
        int[] tree = new int[2 * N2];
        int[] max = new int[2 * N2];
        for (int i = 0; i < N; i++) update(diff[i], i, tree, max, N2);
        int m = maxPrefixSum(0, N - 1, tree, max, N2);
        // System.out.println("tree " + Arrays.toString(tree));
        // System.out.println("max " + Arrays.toString(max));

        // iterate
        for (int i = 0; i < N - 1; i++) {
            if (!next.containsKey(types[i]) || next.get(types[i]).isEmpty()) continue;
            update(1, next.get(types[i]).getFirst(), tree, max, N2);
            next.get(types[i]).removeFirst();
            if (!next.get(types[i]).isEmpty())
                update(-S, next.get(types[i]).getFirst(), tree, max, N2);
            // System.out.println("tree " + Arrays.toString(tree));
            // System.out.println("max " + Arrays.toString(max));
            m = Math.max(m, maxPrefixSum(i + 1, N - 1, tree, max, N2));
        }
        return m;
    }
}
