import java.util.*;
class Solution {
public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        int n = elements.length; 
        int[] arr = new int[n*2];
    
        for (int i=0; i<n; i++) {
            arr[i] = elements[i%n];
            arr[i+n] = elements[i%n];
        }
    
        for (int i=1; i<=n; i++) {
            for (int k = 0; k < n; k++) {
                int sum = 0;
                for (int j=0; j<i; j++) {
                   sum += arr[k + j];
                }
                set.add(sum);
            }
        }
        return set.size();
    }
}
