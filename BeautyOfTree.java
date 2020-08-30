/* *****************************************************************************
 *  Name: 2020 Google Kickstart D
 *  Date: 7/12/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class BeautyOfTree {

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = in.nextInt();
            int A = in.nextInt();
            int B = in.nextInt();
            int[] nums = new int[N + 1];
            for (int j = 2; j <= N; j++) nums[j] = in.nextInt();

            System.out.println("Case #" + i + ": " + solve(N, A, B, nums));
        }
    }

    private static class Node {
        private ArrayList<Node> children = new ArrayList<>();
        // private HashMap<Integer, Integer> dists = new HashMap<>();
        private int as = 0, bs = 0;
    }

    private static double solve(int N, int A, int B, int[] nums) {
        Node[] tree = new Node[N + 1];
        tree[1] = new Node();
        for (int i = 2; i <= N; i++) {
            tree[i] = new Node();
            tree[nums[i]].children.add(tree[i]);
        }

        dfs(tree[1], A, B);

        double ev = 0;
        for (int i = 1; i <= N; i++) {
            ev += (1 - (double) tree[i].as / N) * (1 - (double) tree[i].bs / N);
        }
        return N - ev;
    }

    private static void dfs(Node c, int A, int B) {
        Stack<Node> nodeSt = new Stack<Node>();
        Stack<ArrayList<Node>> pathSt = new Stack<>();
        Stack<Node> nodeSt2 = new Stack<Node>();
        Stack<ArrayList<Node>> pathSt2 = new Stack<>();
        nodeSt.push(c);
        nodeSt2.push(c);
        ArrayList<Node> path = new ArrayList<>();
        path.add(c);
        pathSt.push((ArrayList<Node>) path.clone());
        pathSt2.push((ArrayList<Node>) path.clone());

        // pre-recurse items
        while (!nodeSt.isEmpty()) {
            c = nodeSt.pop();
            path = pathSt.pop();

            for (Node c2 : c.children) {
                nodeSt.push(c2);
                nodeSt2.push(c2);
                ArrayList<Node> path2 = (ArrayList<Node>) path.clone();
                path2.add(c2);
                pathSt.push((ArrayList<Node>) path2.clone());
                pathSt2.push((ArrayList<Node>) path2.clone());
            }
        }

        // post-recurse items
        while (!nodeSt2.isEmpty()) {
            c = nodeSt2.pop();
            path = pathSt2.pop();
            int index = path.size() - 1;

            // for paint level 0
            c.as++;
            c.bs++;

            // for paint level > 0
            if (index >= A) path.get(index - A).as += c.as;
            if (index >= B) path.get(index - B).bs += c.bs;
        }
    }
}
