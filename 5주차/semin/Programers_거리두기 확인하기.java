class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] arr;
    static boolean[][] check;
    static boolean tf;
    
    static int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for (int i=0; i<5; i++) {
        	tf = false;
        	arr = new char[5][5];
            for (int j=0; j<5; j++) {
            	arr[j] = places[i][j].toCharArray();
            }
   
            for (int r=0; r<5; r++) {
                for (int c=0; c<5; c++) {
                	if (arr[r][c] == 'P') {
                		check = new boolean[5][5];
	                    dfs(0, r, c);
	                    if (tf) {
	                    	break;  	
	                    }
                	}
                }
                if (tf) break;
            }
            if (tf) answer[i] = 0;
            else answer[i] = 1;
        }
        
        return answer;
        
        
    }
    
    static void dfs(int depth, int r, int c) {
        if (depth >= 2) return;
        check[r][c] = true;
        for (int i=0; i<4; i++) {
            int x = r+dx[i];
            int y = c+dy[i];
            
            if (x < 0 || x >= 5 || y < 0 || y >= 5 || check[x][y]) continue;
            
            if (arr[x][y] == 'O') dfs(depth+1, x, y);
            else if (arr[x][y] == 'P') {
            	tf = true;
                return;
            }
            else if (arr[x][y] == 'X') {
                continue;
            }
 
        }
    }

}