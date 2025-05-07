import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {
	static int N;
	static int M;
	
	static int[][] graph;
	static ArrayList<int[]> order;
	static ArrayList<Integer> result;
	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        graph = new int[N][M];
        order = new ArrayList<>();
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<M; j++) {
        		graph[i][j] = Integer.parseInt(st.nextToken());
        		if(0 < graph[i][j] && graph[i][j] < 6) {
        			order.add(new int[] {graph[i][j], i, j}); // CCTV 번호, x좌표 y좌표
        		}
        	}
        }
        result = new ArrayList<>();
        
        dfs(0);
        int minResult = N * M;
        for(int val : result) {
        	if(minResult > val) {
        		minResult = val;
        	}
        }
        System.out.println(minResult);
	}
	private static void dfs(int depth) {
		if(depth == order.size()) {
			countZero();
			return;
		}
		int[] cur = order.get(depth);
		for(int i=0; i<4; i++) {
			if(cur[0] == 2 && i > 1) {
				continue;
			}
			if(cur[0] == 5 && i > 0) {
				continue;
			}
			ArrayList<Character> dir = dirs(cur[0], i);
			paintshap(dir, cur[1], cur[2], depth + 7); // depth를 이용해 고유값으로 칠하기
			dfs(depth + 1);
			removeshap(dir, cur[1], cur[2], depth + 7);
		}
	}
	private static ArrayList<Character> dirs(int cctv, int caseNum) {
		ArrayList<Character> dir = new ArrayList<>();
		if(cctv == 1) {
			switch(caseNum) {
			case 0: dir.add('u'); break;
			case 1: dir.add('l'); break;
			case 2: dir.add('d'); break;
			case 3: dir.add('r'); break;
			}
		}else if(cctv == 2) {
			switch(caseNum) {
			case 0: dir.add('u'); dir.add('d'); break;
			case 1: dir.add('l'); dir.add('r'); break;
			}
		}else if(cctv == 3) {
			switch(caseNum) {
			case 0: dir.add('u'); dir.add('l'); break;
			case 1: dir.add('l'); dir.add('d'); break;
			case 2: dir.add('d'); dir.add('r'); break;
			case 3: dir.add('r'); dir.add('u'); break;
			}
		}else if(cctv == 4){
			switch(caseNum) {
			case 0: dir.add('u'); dir.add('l'); dir.add('d'); break;
			case 1: dir.add('l'); dir.add('d'); dir.add('r'); break;
			case 2: dir.add('d'); dir.add('r'); dir.add('u'); break;
			case 3: dir.add('r'); dir.add('u'); dir.add('l'); break;
			}
		}else { // cctv == 5
			dir.add('u'); dir.add('l'); dir.add('d'); dir.add('r');
		}
		return dir;
	}

	private static void paintshap(ArrayList<Character> dir, int x, int y, int mark) {
		for(char cur : dir) {
			if(cur == 'u') {
				for(int i=x-1; i>=0; i--) {
					if(graph[i][y] == 0) {
						graph[i][y] = mark;
					}else if(graph[i][y] == 6) {
						break;
					}
				}
			}
			else if(cur == 'l') {
				for(int i=y-1; i>=0; i--) {
					if(graph[x][i] == 0) {
						graph[x][i] = mark;
					}else if(graph[x][i] == 6) {
						break;
					}
				}
			}
			else if(cur == 'd') {
				for(int i=x+1; i<N; i++) {
					if(graph[i][y] == 0) {
						graph[i][y] = mark;
					}else if(graph[i][y] == 6) {
						break;
					}
				}
			}
			else if(cur == 'r') {
				for(int i=y+1; i<M; i++) {
					if(graph[x][i] == 0) {
						graph[x][i] = mark;
					}else if(graph[x][i] == 6) {
						break;
					}
				}
			}
		}
	}
	private static void removeshap(ArrayList<Character> dir, int x, int y, int mark) {
		for(char cur : dir) {
			if(cur == 'u') {
				for(int i=x-1; i>=0; i--) {
					if(graph[i][y] == mark) {
						graph[i][y] = 0;
					}else if(graph[i][y] == 6) {
						break;
					}
				}
			}
			else if(cur == 'l') {
				for(int i=y-1; i>=0; i--) {
					if(graph[x][i] == mark) {
						graph[x][i] = 0;
					}else if(graph[x][i] == 6) {
						break;
					}
				}
			}
			else if(cur == 'd') {
				for(int i=x+1; i<N; i++) {
					if(graph[i][y] == mark) {
						graph[i][y] = 0;
					}else if(graph[i][y] == 6) {
						break;
					}
				}
			}
			else if(cur == 'r') {
				for(int i=y+1; i<M; i++) {
					if(graph[x][i] == mark) {
						graph[x][i] = 0;
					}else if(graph[x][i] == 6) {
						break;
					}
				}
			}
		}
	}
	private static void countZero() {
		int sum = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(graph[i][j] == 0) {
					sum++;
				}
			}
		}
		result.add(sum);
	}
}
