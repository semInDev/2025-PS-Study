import java.io.*;
import java.util.*;

public class Main{
  
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
    
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> {
			return o2[1] - o1[1];
		});
		int[][] jewels = new int[N][2];
		int[] bags = new int[K];

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			jewels[n][0] = Integer.parseInt(st.nextToken());
			jewels[n][1] = Integer.parseInt(st.nextToken());
		}

		for (int k = 0; k < K; k++) {
			bags[k] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(jewels, (o1, o2) -> {
			return o1[0] - o2[0];
		});
		Arrays.sort(bags);

		int idx = 0;
		long ans = 0;

		for (int k = 0; k < K; k++) {
			int cur = bags[k];

			while (idx < N) {
				if (jewels[idx][0] <= cur) {
					pq.add(jewels[idx].clone());
					idx++;
				} else {
					break;
				}
			}
			if(!pq.isEmpty()) {
				ans += pq.poll()[1];
			}
		}
		System.out.println(ans);
	}
}
