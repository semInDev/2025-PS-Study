import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		if(N==1) {
			System.out.println(10);
			return;
		}
		int[][] dp = new int[10][N];
		for(int i=0; i<10; i++) {
			dp[i][0] = 1;
		}
		
		for(int i=1; i<N; i++) {
			for(int j=0; j<10; j++) {
				for(int k=0; k<j+1; k++) {
					dp[j][i] = (dp[j][i] + dp[k][i-1]) % 10007;
				}
			}
		}
		
		int output = dp[0][N-1];
		for(int i=1; i<10; i++) {
			output = (output + dp[i][N-1]) % 10007; 
		}
		System.out.println(output);
	}
}
