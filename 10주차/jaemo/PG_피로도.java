import java.util.*;

class Solution {
    static int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        explore(k, dungeons, new boolean[dungeons.length], 0);
        return answer;
    }
    
    public void explore(int remain, int[][] dungeons, boolean[] visited, int depth) {
        for (int i=0;i<dungeons.length;i++) {
            if (!visited[i] && remain >= dungeons[i][0]) {
                visited[i] = true;
                explore(remain-dungeons[i][1], dungeons, visited, depth+1);
                visited[i] = false;
            }
        }
        
        answer = Math.max(answer, depth);
    }
}
