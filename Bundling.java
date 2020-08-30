/* *****************************************************************************
 *  Name: 2020 Google Kickstart A
 *  Date: 6/12/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Bundling {
    private static class Node {
        Node parent;
        ArrayList<Character> poss;
        Node[] nexts;
        int num = 0;

        public Node(Node parent) {
            poss = new ArrayList<Character>();
            nexts = new Node[26];
            this.parent = parent;
        }

        // for root
        public Node() {
            poss = new ArrayList<Character>();
            nexts = new Node[26];
            parent = null;
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = in.nextInt();
            int K = in.nextInt();

            String[] words = new String[N];
            for (int j = 0; j < N; j++) words[j] = in.next();

            System.out.println("Case #" + i + ": " + solve(N, K, words));
        }
    }

    private static int solve(int N, int K, String[] words) {
        // add words to trie
        Node root = new Node();
        for (String word : words) {
            Node curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (curr.nexts[c - 'A'] == null) {
                    curr.nexts[c - 'A'] = new Node(curr);
                    curr.poss.add(c);
                }
                curr = curr.nexts[c - 'A'];
                curr.num++;
            }
        }

        // count max
        int total = 0;
        for (Character c : root.poss) {
            // System.out.println(c);
            int tmp = search(1, K, root.nexts[c - 'A']);
            // System.out.println(c + " " + tmp);
            total += tmp;
        }
        // leftover is 0 score, so no need to count
        return total;
    }

    private static int search(int depth, int K, Node curr) {
        if (curr.num < K) return 0;

        int total = 0;
        int orig = curr.num;
        for (Character c : curr.poss) { // get children totals
            // System.out.println(c);
            int tmp = search(depth + 1, K, curr.nexts[c - 'A']);
            // System.out.println(c + " " + tmp);
            total += tmp;
        }
        // System.out.println(curr.num);
        total += curr.num / K * depth; // add any leftover totals
        curr.parent.num -= (orig - curr.num) + curr.num / K * K; // carry over diffs
        return total;
    }
}
