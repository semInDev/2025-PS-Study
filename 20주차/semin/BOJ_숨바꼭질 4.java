import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] dist = new int[100001];
		int[] parent = new int[100001];
		Arrays.fill(dist, -1);
		
		dist[N] = 0;
		parent[N] = N;
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(N);
		while(dist[K] == -1) {
			int cur = queue.poll();
			for(int nxt : new int[] {cur-1, cur+1, cur*2}) {
				if(0<=nxt && nxt<100001 && dist[nxt] == -1) {
					dist[nxt] = dist[cur] + 1;
					parent[nxt] = cur;
					queue.add(nxt);
				}
			}
		}
		System.out.println(dist[K]);
		Stack<Integer> stack = new Stack<>();
		int idx = K;
		stack.push(K);
		while(parent[idx]!=idx) {
			stack.push(parent[idx]);
			idx = parent[idx];
		}
		while(!stack.isEmpty()) {
			System.out.print(stack.pop()+" ");
		}
	}

}
