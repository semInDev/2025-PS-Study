import java.util.*;
class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }
        
        long[] f = new long[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            f[i] = f[i - 1] * i;
        }
        
        k--;
        int j = 0;
        for (int i = n; i >= 1; i--) {
            int idx = (int)(k / f[i - 1]);
            answer[j++] = numbers.get(idx);
            numbers.remove(idx);
            k %= f[i - 1];
        }
        
        return answer;
    }
}
