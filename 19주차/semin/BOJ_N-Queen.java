import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int n;
	static int cnt = 0;
	static boolean[] isused1, isused2, isused3;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));		        
        n = Integer.parseInt(br.readLine());
        isused1 = new boolean[2*n-1];
        isused2 = new boolean[2*n-1];
        isused3 = new boolean[2*n-1];
        
        backtracking(0);
        
        System.out.println(cnt);
	}
	
	private static void backtracking(int cur) {
        if (cur == n) {
            cnt++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!isused1[i] && !isused2[i + cur] && !isused3[cur - i + n - 1]) {
	            isused1[i] = true;
	            isused2[i + cur] = true;
	            isused3[cur - i + n - 1] = true;
	            backtracking(cur + 1);
	            isused1[i] = false;
	            isused2[i + cur] = false;
	            isused3[cur - i + n - 1] = false;
            }
        }
	}
}
