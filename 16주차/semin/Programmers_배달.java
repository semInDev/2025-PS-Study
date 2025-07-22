import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int[][] graph =new int[N][N]; 
        
        for (int i = 0; i < graph.length; i++) { 
            for (int j = 0; j < graph[0].length; j++) { 
                if (i == j){                                  
                    continue;
                }
                graph[i][j] = 500001;   
            }
        }

        for(int[] data:road){
            if(graph[data[0]-1][data[1]-1]<data[2]){ 
                continue;
            }
            graph[data[0]-1][data[1]-1]=data[2]; 
            graph[data[1]-1][data[0]-1]=data[2]; 
        }

        for(int i=0; i<graph.length; i++) { 
            for(int j=0; j<graph.length; j++) { 
                for(int k=0; k<graph.length; k++) { 
                    if(graph[j][k] > graph[j][i] + graph[i][k]) 
                        graph[j][k] = graph[j][i] + graph[i][k]; 
                }
            }
        }

        for (int i = 0; i < graph[0].length; i++) { 
            if (graph[0][i] <= K)  
                answer++;
        }
        
        return answer;
    }
}
