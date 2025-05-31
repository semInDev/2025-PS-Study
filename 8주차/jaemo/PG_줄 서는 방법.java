import java.util.*;

class Solution { 
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> numbers = new ArrayList<>();
        long[] factorial = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        k--;

        for (int i = 0; i < n; i++) {
            int index = (int) (k / factorial[n - 1 - i]);
            answer[i] = numbers.remove(index);
            k %= factorial[n - 1 - i];
        }

        return answer;
    }
}
