import java.util.*;

class Node{ 
    int x;
    int y;
    Node(int x, int y){ 
        this.x = x; 
        this.y = y; 
    }
}

class Solution {
    int N,M;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int solution(String[] board) {
        N = board.length;
        M = board[0].length();
        int[][] visit = new int[N][M];
        Queue<Node> q = new ArrayDeque<>();
        
        for(int i = 0 ; i < N; i++){ 
            String s = board[i];
            for(int j = 0 ; j < M; j++){ 
                if(s.charAt(j) == 'R'){
                    q.add(new Node(i,j));
                    visit[i][j] = 1;
                    break;
                }
            }
        }
        
        int answer = -1;
        while(!q.isEmpty()){
            Node cur = q.poll(); 
          
            if(board[cur.x].charAt(cur.y) == 'G'){
                answer = visit[cur.x][cur.y] - 1;
                break;
            }
            
            for(int dir = 0 ; dir < 4; dir++){ 
                int nxtX = cur.x + dx[dir]; 
                int nxtY = cur.y + dy[dir]; 
                while(true){
                    if(isInRange(nxtX, nxtY) && board[nxtX].charAt(nxtY) != 'D'){ 
                        nxtX += dx[dir]; 
                        nxtY += dy[dir]; 
                    }else{
                        nxtX -= dx[dir]; 
                        nxtY -= dy[dir]; 
                        break;
                    }
                }
                
                if(visit[nxtX][nxtY] == 0){ 
                    q.add(new Node(nxtX, nxtY)); 
                    visit[nxtX][nxtY] = visit[cur.x][cur.y] + 1; 
                }
            }
        }
        
        return answer;
    }

    // 코드 리펙토링하면서 배운 코드!
    public boolean isInRange(int x, int y){
        if(0 <= x && x < N && 0 <= y && y < M)
            return true;
        return false;
    }
  
}
