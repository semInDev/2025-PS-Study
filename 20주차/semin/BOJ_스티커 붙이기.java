import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K, cnt=0;
	static boolean[][] graph, curS;
	static Queue<boolean[][]> stickers;
	
	public static void main(String[] args)throws IOException {
        // 입력
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());        
        K = Integer.parseInt(st.nextToken());
		graph = new boolean[N][M]; // int[][]면 0으로 초기화해야해서 boolean[][]으로 바꿈
        
		stickers = new ArrayDeque<>();
        for(int i=0; i<K; i++) {
        	st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken()); 
            boolean[][] sticker = new boolean[R][C];
            for(int r=0; r<R; r++) {
            	st = new StringTokenizer(br.readLine());
            	for(int c=0; c<C; c++) {
            		int val = Integer.parseInt(st.nextToken());
            		if(val == 1) sticker[r][c] = true;
            	}
            }
            stickers.add(sticker);
        }
        
        while(!stickers.isEmpty()) {
        	attach();
        }
        
        System.out.println(cnt);
	}
	private static void attach() {
		curS = stickers.poll();
		for(int i=0; i<4; i++) {
	        if(curS.length<=N && curS[0].length<=M) {// 삐져 나오면 바로 90도 회전
	        	// 비교하기
	        	for(int startX=0; startX<N-curS.length+1; startX++) {
	        		for(int startY=0; startY<M-curS[0].length+1; startY++) {
	        			// 모든 경우의 수로 넣어보기
	        			if(isPossible(startX, startY)) {
	        				updateGraph(startX, startY);
	        				return;
	        			}
	        		}
	        	}
	        }
	        if(i<3) rotate();
		}
	}

	private static boolean isPossible(int x, int y) {
		for(int i=0; i<curS.length; i++) {
			for(int j=0; j<curS[0].length; j++) {
				if(curS[i][j] && graph[i+x][j+y]) return false;
			}
		}
		return true;
	}
	
	private static void updateGraph(int x, int y) {
		for(int i=0; i<curS.length; i++) {
			for(int j=0; j<curS[0].length; j++) {
				if(curS[i][j]) {
					graph[i+x][j+y] = true;
					cnt++;
				}
			}
		}
	}
	
	private static void rotate() {
		boolean[][] newS = new boolean[curS[0].length][curS.length];
		for(int i=0; i<curS.length; i++) {
			for(int j=0; j<curS[0].length; j++) {
				newS[j][curS.length-i-1] = curS[i][j];
			}
		}
		curS = newS;
	}
	
}
