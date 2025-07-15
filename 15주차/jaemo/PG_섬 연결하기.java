import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        int[] arr = new int[n];
        for (int i=0;i<n;i++) {
            arr[i] = i;
        }
        
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        
        int answer = 0;
        for (int i=0;i<costs.length;i++) {
            int a = costs[i][0];
            int b = costs[i][1];
            int weight = costs[i][2];
            if (find(arr, arr[a]) == find(arr, arr[b])) {
                continue;
            }
            union(arr, a, b);
            answer += weight;
        }
        
        return answer;
    }
    
    public void union(int[] arr, int a, int b) {
        a = find(arr, a);
        b = find(arr, b);
        if (a < b) {
            arr[b] = a;
        } else {
            arr[a] = b;  
        } 
    }
    
    public int find(int[] arr, int node) {
        if (arr[node] == node) {
            return node;
        }
        return arr[node] = find(arr, arr[node]);
    }
}
