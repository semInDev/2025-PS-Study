import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N];
		dp[0] = 1;
		int[] parents = new int[N];
		int max_index = 0;
		for(int i=1; i<N; i++) {
			dp[i] = 1;
			parents[i] = i;
			for(int j=0; j<i; j++) {
				if(arr[j] < arr[i]) {
					//dp[i] = Math.max(dp[i], dp[j]+1);
					if(dp[i] < dp[j]+1) {
						dp[i] = dp[j]+1;
						parents[i] = j;
					}
				}
			}
			if(dp[max_index] < dp[i]) {
				max_index = i;
			}
		}
		
		System.out.println(dp[max_index]);
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		while(parents[max_index] != max_index) {
			stack.push(arr[max_index]);
			max_index = parents[max_index];
		}
		stack.push(arr[max_index]);
		while(!stack.isEmpty()) {
			sb.append(stack.pop()+" ");
		}
		System.out.println(sb);
	}

}
