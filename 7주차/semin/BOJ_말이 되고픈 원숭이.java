import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		int[] dx1 = {-2,-2,-1,-1,1,1,2,2};
		int[] dy1 = {-1,1,-2,2,-2,2,-1,1};
		int[] dx2 = {-1,1,0,0};
		int[] dy2 = {0,0,-1,1};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[][][] dis = new int[H][W][K+1];
		int[][] graph = new int[H][W];
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				Arrays.fill(dis[i][j], -1);
			}
		}
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {0,0,0});
		dis[0][0][0] = 0;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0], y = cur[1], k = cur[2];
			if(x == H-1 && y == W-1) {
				System.out.println(dis[x][y][k]);
				return;
			}
			if(k<K) {
				for(int i=0; i<8; i++) {
					int nx = x + dx1[i];
					int ny = y + dy1[i];
					if(0<=nx && nx<H && 0<=ny && ny<W
							&& graph[nx][ny] == 0
							&& dis[nx][ny][k+1]==-1) {
						dis[nx][ny][k+1] = dis[x][y][k] + 1;
						queue.add(new int[] {nx, ny, k+1});
					}
				}
			}
			for(int i=0; i<4; i++) {
				int nx = x + dx2[i];
				int ny = y + dy2[i];
				if(0<=nx && nx<H && 0<=ny && ny<W
						&& graph[nx][ny] == 0
						&& dis[nx][ny][k]==-1) {
					dis[nx][ny][k] = dis[x][y][k] + 1;
					queue.add(new int[] {nx, ny, k});
				}
			}
		}
		System.out.println(-1);
	}
}
