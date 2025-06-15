

class Solution {
    public int solution(int n, int[][] results) {
        int[][] graph = new int[n+1][n+1];
		for (int[] edge : results) {
			graph[edge[0]][edge[1]] = 1;
			graph[edge[1]][edge[0]] = -1;
		}

		// 중간
		for (int m=1;m<=n;m++) {
			// 시작
			for (int s=1;s<=n;s++) {
                // 끝
				for (int e=1;e<=n;e++) {
					if(graph[s][m] == 1 && graph[m][e] == 1) {
						graph[s][e] = 1;
						graph[e][s] = -1;
					}
					
					if(graph[s][m] == -1 && graph[m][e] == -1) {
						graph[s][e] = -1;
						graph[e][s] = 1;
					}
				}
            }
		}
		
		int answer = 0;
		for(int i=1;i<=n;i++) {
			int count = 0;
			for (int j=1;j<=n;j++) {
				if(graph[i][j] != 0) {
                    count++;
                }
			}
			if(count == n-1) {
                answer++;
            }
		}
    
        return answer;
    }
}
