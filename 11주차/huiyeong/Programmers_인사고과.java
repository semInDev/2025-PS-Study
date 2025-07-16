import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int[] wanho = scores[0];
        int wanhoSum = wanho[0] + wanho[1];

        Arrays.sort(scores, (a, b) -> {
            if (b[0] == a[0]) return a[1] - b[1];
            return b[0] - a[0];
        });

        int max = 0;
        List<Integer> list = new ArrayList<>();

        for (int[] s : scores) {
            if (s[1] < max) {
                if (Arrays.equals(s, wanho)) return -1; 
                continue;
            }
            max = Math.max(max, s[1]);
            list.add(s[0] + s[1]);
        }
        list.sort(Collections.reverseOrder());

        int rank = 1;
        int count = 1;
        int prev = list.get(0);

        if (prev == wanhoSum) return rank;

        for (int i = 1; i < list.size(); i++) {
            int cur = list.get(i);

            if (cur == prev) {
                count++;
            } else {
                rank += count;
                count = 1;
                prev = cur;
            }

            if (cur == wanhoSum) return rank;
        }

        return -1;
    }
}
