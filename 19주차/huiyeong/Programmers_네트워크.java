class Solution {
    private boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        
        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                answer++;
                DFS(n, computers, i);
            }
        }
        return answer;
    }
    private void DFS(int n, int[][] computers, int i) {
        visited[i] = true;
         for (int next = 0; next < n; next++) {
            if (!visited[next] && computers[i][next] == 1) {
                DFS(n, computers, next);
            }
        }
    }
    
}
