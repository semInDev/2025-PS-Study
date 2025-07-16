import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        for (int i=0;i<wires.length;i++) {
            List<List<Integer>> graph = new ArrayList<>();
            for (int j=0;j<=n;j++) {
                graph.add(new ArrayList<>());
            }
            
            for (int j=0;j<wires.length;j++) {
                if (j == i) {
                    continue;
                }
                int v1 = wires[j][0];
                int v2 = wires[j][1];
                graph.get(v1).add(v2);
                graph.get(v2).add(v1);
            }
            
            boolean[] visited = new boolean[n+1];
            int totalA = bfs(n, graph, visited, 1);
            int nextNode = 0;
            for (int j=1;j<=n;j++) {
                if (!visited[j]) {
                    nextNode = j;
                }
            }
            int totalB = bfs(n, graph, visited, nextNode);
            
            answer = Math.min(answer, Math.abs(totalA-totalB));
        }
        
        return answer;
    }
    
    public int bfs(int n, List<List<Integer>> graph, boolean[] visited, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        
        int cnt = 1;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            List<Integer> adjNodes = graph.get(now);
            for (int i=0;i<adjNodes.size();i++) {
                int adjNode = adjNodes.get(i);
                if (!visited[adjNode]) {
                    queue.offer(adjNode);
                    visited[adjNode] = true;
                    cnt++;
                }
            }
        }
        
        return cnt;
    }
}
