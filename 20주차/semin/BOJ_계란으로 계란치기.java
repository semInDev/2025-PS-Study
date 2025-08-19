import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, maxCount=0;
	static int[] durabilitys, weights;
	static boolean[] isCracked;

	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
	    N = Integer.parseInt(br.readLine());
	
		StringTokenizer st;
		durabilitys = new int[N];
		weights = new int[N];
		isCracked = new boolean[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			durabilitys[i] = Integer.parseInt(st.nextToken());
			weights[i] = Integer.parseInt(st.nextToken());
		}
	
		bt(0,0);
	
		System.out.println(maxCount);
	}
	
	private static void bt(int curIdx, int cnt) {
		if(curIdx == N) {
			maxCount = Math.max(maxCount, cnt);
			return;
		}
		if(isCracked[curIdx]) {
			bt(curIdx+1, cnt);
			return;
		}
		if(!isCracked[curIdx]) {
			boolean crackSomthing = false;
			for(int i=0; i<N; i++) {
				if(curIdx == i || isCracked[i]) {
					continue;
				}
				
				crackSomthing = true;
				
				durabilitys[i] = durabilitys[i] - weights[curIdx];
				durabilitys[curIdx] = durabilitys[curIdx] - weights[i];
	
				if(durabilitys[i] <= 0) {
					isCracked[i] = true;
					cnt++;
				}
				if(durabilitys[curIdx] <= 0) {
					isCracked[curIdx] = true;
					cnt++;
				}
	
				bt(curIdx+1, cnt);
	
				if(durabilitys[i] <= 0) {
					isCracked[i] = false;
					cnt--;
				}
				if(durabilitys[curIdx] <= 0) {
					isCracked[curIdx] = false;
					cnt--;
				}
	
				durabilitys[i] = durabilitys[i] + weights[curIdx];
				durabilitys[curIdx] = durabilitys[curIdx] + weights[i];
	
			}
			if(!crackSomthing) bt(curIdx+1, cnt);
		}
	}
}
