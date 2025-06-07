import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, (int[] o1, int[] o2) -> o1[2] - o2[2]);
        
        int[] arr = new int[n];
        for (int i=0;i<n;i++) {
            arr[i] = i;
        }
        
        int answer = 0;
        for (int i=0;i<costs.length;i++) {
            int[] cost = costs[i];
            if (isEnd(arr)) {
                break;
            }
            if (find(arr, arr[cost[0]]) == find(arr, arr[cost[1]])) {
                continue;
            }
            
            union(arr, cost[0], cost[1]);
            answer += cost[2];
        }
        
        return answer;
    }
    
    public void union(int[] arr, int a, int b) {
        a = find(arr, arr[a]);
        b = find(arr, arr[b]);
        
        if (a < b) {
            arr[b] = a;
        } else {
            arr[a] = b;
        }
    }
    
    public int find(int[] arr, int n) {
        if (n == arr[n]) {
            return n;
        }
        return arr[n] = find(arr, arr[n]);
    }
    
    public boolean isEnd(int[] arr) {
        int base = find(arr, arr[0]);
        for (int i=1;i<arr.length;i++) {
            if (base != find(arr, i)) {
                return false;
            }
        }
        
        return true;
    }
}
