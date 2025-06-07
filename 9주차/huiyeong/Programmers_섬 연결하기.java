import java.util.*;
class Solution {    
    public int solution(int n, int[][] costs) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) 
            graph[i] = new ArrayList<>();

        for (int[] cost : costs) {
            int a = cost[0];
            int b = cost[1];
            int c = cost[2];
            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }
        
        
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{0, 0});

        int answer = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int cost = curr[1];

            if (visited[node]) continue;

            visited[node] = true;
            answer += cost;

            for (int[] next : graph[node]) {
                if (!visited[next[0]]) {
                    pq.offer(new int[]{next[0], next[1]});
                }
            }
        }

        return answer;
    }
}
