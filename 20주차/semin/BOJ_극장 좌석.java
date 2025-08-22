import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[] dp = new int[41];
		dp[0] = 1; dp[1] = 1; dp[2] = 2;
		for(int i=3; i<41; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		if(M == 0) {
			System.out.println(dp[N]);
			return;
		}
		
		int[] arr = new int[M];
		for(int i=0; i<M; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int output = dp[arr[0]-1];
		for(int i=1; i<M; i++) {
			output *= dp[arr[i]-arr[i-1]-1];
		}
		output *= dp[N-arr[M-1]];
		
		System.out.println(output);
	}

}
