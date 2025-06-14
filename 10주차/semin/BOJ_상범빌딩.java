import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] dh = { -1, 1, 0, 0, 0, 0 };
		int[] dx = { 0, 0, -1, 1, 0, 0 };
		int[] dy = { 0, 0, 0, 0, -1, 1 };

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			if (L == 0 && R == 0 && C == 0) break;

			char[][][] board = new char[L][R][C];
			int[][][] graph = new int[L][R][C];
			int[] start = null;
			int[] end = null;
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					String input = br.readLine();
					for (int k = 0; k < C; k++) {
						board[i][j][k] = input.charAt(k);
						if (board[i][j][k] == 'S') {
							start = new int[] { i, j, k };
						} else if (board[i][j][k] == 'E') {
							end = new int[] { i, j, k };
						}
					}
					Arrays.fill(graph[i][j], -1);
				}
				br.readLine();
			}

			Queue<int[]> queue = new ArrayDeque<>();
			queue.add(start);
			graph[start[0]][start[1]][start[2]] = 0;

			boolean trapped = true;
			while (!queue.isEmpty()) {
				int[] cur = queue.poll();
				int h = cur[0], x = cur[1], y = cur[2];
				
				if(Arrays.equals(cur, end)) {
				sb.append("Escaped in "+graph[h][x][y]+" minute(s).\n"); trapped = false;
				break; }
				
				for (int i = 0; i < 6; i++) {
					int nh = h + dh[i];
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (0 <= nh && nh < L && 0 <= nx && nx < R && 0 <= ny && ny < C) {
						if (board[nh][nx][ny] != '#' && graph[nh][nx][ny] == -1) {
							graph[nh][nx][ny] = graph[h][x][y] + 1;
							queue.add(new int[] {nh, nx, ny});
							/*
							 * if(nh == end[0] && nx == end[1] && ny == end[2]) {
							 * sb.append("Escaped in "+graph[nh][nx][ny]+" minute(s).\n"); trapped = false;
							 * break; }
							 */
						}
					}
				}
			}
			if (trapped) {
				sb.append("Trapped!\n");
			}
		}
		System.out.print(sb);
	}
}
