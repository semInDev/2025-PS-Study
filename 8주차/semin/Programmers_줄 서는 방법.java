import java.util.*;
import java.util.stream.IntStream;

class Solution {

    public int[] solution(int n, long k) {
        long[] dp = new long[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] * i;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i + 1);
        }

        List<Integer> responses = new ArrayList<>();

        while (true) {
            long num = dp[n - 1];
            if (k % num == 0) {
                responses.add(list.remove((int) (k / num) - 1));
                break;
            } else {
                responses.add(list.remove((int) (k / num)));
                k %= num;
            }
            n--;
        }

        if (!list.isEmpty()) {
            for (int i = list.size() - 1; i >= 0; i--) {
                responses.add(list.get(i));
            }
        }

        return responses.stream().mapToInt(Integer::intValue).toArray();
    }
}
