/* *****************************************************************************
 *  Name: 2019 Google Kickstart B
 *  Date: 7/3/20
 *  Description:
 **************************************************************************** */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.Scanner;

public class BuildingPalindromes {

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = in.nextInt();
            int Q = in.nextInt();
            String s = in.next();
            int[][] questions = new int[Q][2];
            for (int j = 0; j < Q; j++) {
                questions[j][0] = in.nextInt();
                questions[j][1] = in.nextInt();
            }

            System.out.println("Case #" + i + ": " + solve(N, Q, s, questions));
        }
    }

    private static int solve(int N, int Q, String s, int[][] questions) {
        // long MAX = 1000000000;
        BitSet[] odd = new BitSet[N + 1];
        for (int i = 0; i < N; i++) odd[i] = new BitSet(26);
        BitSet curr = new BitSet(26);
        odd[0] = (BitSet) curr.clone();
        for (int i = 1; i <= s.length(); i++) {
            curr.flip(s.charAt(i - 1) - 'A');
            odd[i] = (BitSet) curr.clone();
        }

        int poss = 0;
        for (int i = 0; i < questions.length; i++) {
            int L = questions[i][0];
            int R = questions[i][1];
            BitSet bs = (BitSet) odd[R].clone();
            bs.xor(odd[L - 1]);
            if (bs.cardinality() <= 1) poss++;

        }

        return poss;
    }
}
