import java.util.*;
class Solution {
    private boolean[] visited;
    private int answer = 0;

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        DFS(k, dungeons, 0);
        return answer;
    }

    private void DFS(int k, int[][] dungeons, int count) {
        answer = Math.max(answer, count);

        for (int i = 0; i < dungeons.length; i++) {
            int a = dungeons[i][0];
            int b = dungeons[i][1];

            if (!visited[i] && k >= a) {
                visited[i] = true;
                DFS(k - b, dungeons, count + 1);
                visited[i] = false;
            }
        }
    }
}
