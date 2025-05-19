import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][][] graph = new int[N][M][2];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				Arrays.fill(graph[i][j], -1);
			}
		}
		
		char[][] board = new char[N][M];
		for(int i=0; i<N; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		Queue<int[]> queue = new ArrayDeque<>();
		graph[0][0][0] = graph[0][0][1] = 1;//시작점 초기화
		queue.add(new int[] {0,0,0});
		queue.add(new int[] {0,0,1});
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0], y = cur[1], broken = cur[2];
			if(x == N-1 && y == M-1) {
				System.out.println(graph[x][y][broken]);
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(0<=nx && nx<N && 0<=ny && ny<M) {
					//1. cur에서 이미 벽 부숴져있는 상태 && nxt가 벽이 아닌 상태
					if(board[nx][ny]=='0' && graph[nx][ny][broken]==-1) {
						graph[nx][ny][broken] = graph[x][y][broken] + 1;
						queue.add(new int[] {nx, ny, broken});
					}
					//2. cur에서 이미 벽 안 부숴져 있음 -> nxt가 벽인 상태 ->지금 부술 경우
					///nxt가 벽이 아닌 상태 -> 안 부술 경우
					else if(broken == 0 && board[nx][ny]=='1' && graph[nx][ny][broken]==-1) {
						graph[nx][ny][1] = graph[x][y][broken] + 1;
						queue.add(new int[] {nx, ny, 1});
					}
				}
			}
		}
		System.out.println(-1);
	}

}
