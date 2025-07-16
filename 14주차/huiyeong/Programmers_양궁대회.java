import java.util.*;
class Solution {
    int[] answer = new int[11];
    int max = Integer.MIN_VALUE;
    
    public int[] solution(int n, int[] info) {
        int[] lion = new int[11];
        DFS(n, info, lion, 0);
        
        if (max <= 0) {
            return new int[]{-1};
        }
        
        return answer;
    }
    private void DFS(int remaining, int[] info, int[] lion, int idx) {
        if (idx == 11) {
            if (remaining > 0) {
                lion[10] += remaining; 
            }
            
            int A = 0;
            int L = 0;
            
            for (int i = 0; i < 11; i++) {
                if (info[i] == 0 && lion[i] == 0) {
                    continue; 
                }
                if (info[i] >= lion[i]) {
                    A += (10 - i); 
                } else {
                    L += (10 - i);
                }
            }
            
            int diff = L - A;
            if (diff > max) {
                max = diff;
                answer = Arrays.copyOf(lion, 11);
            } 
            else if (diff == max) {
                for (int i = 10; i >=0; i--) {
                    if (lion[i] > answer[i]) {
                        answer = Arrays.copyOf(lion, 11);
                        break;
                    }
                    else if (lion[i] < answer[i]) {
                        break;
                    }
                }
            }
            return;
        }
        
        for (int i = remaining; i >= 0; i--) {
            lion[idx] = i;
            DFS(remaining - i, info, lion, idx + 1);
        }
    }
}
