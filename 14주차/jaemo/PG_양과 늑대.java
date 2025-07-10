import java.util.*;

class Solution {
    static List<List<Integer>> graph = new ArrayList<>();
    static int answer = 0;
    
    public int solution(int[] info, int[][] edges) {
        int n = info.length;
        for (int i=0;i<n;i++) {
            graph.add(new ArrayList<>());
        }
        for (int i=0;i<edges.length;i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
        }
        
        dfs(info, new ArrayList<>(), 0, 0, 0);
        
        return answer;
    }
    
    public void dfs(int[] info, List<Integer> waiting, int now, int sheep, int wolves) {
        if (info[now] == 0) {
            sheep++;
        } else {
            wolves++;  
        }
        
        if (sheep <= wolves) {
            return;
        }
        
        answer = Math.max(answer, sheep);
        
        waiting.remove(Integer.valueOf(now));
        waiting.addAll(graph.get(now));
        
        for (int i=0;i<waiting.size();i++) {
            List<Integer> newWaiting = new ArrayList<>(waiting);
            dfs(info, newWaiting, waiting.get(i), sheep, wolves);
        }
    }
}
