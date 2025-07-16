import java.io.*;
import java.util.*;

public class Main {
	static int M, L
  static int[] gun;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
    StringTokenizer st = new StringTokenizer(br.readline());
    M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readline());
		gun = new int[M];
		for(int i = 0; i < M; i++) {
			gun[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(gun);

		int result=0;
    int x=0, y=0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readline());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());

			if(possible(x, y)) {
				result++;
			}
		}
		
		System.out.println(result);
	}
	
	private static boolean possible(int x, int y) {
		int start = 0;
		int end = M - 1;
		int mid = 0;
		int distance = 0;
		
		while(start <= end) {

			mid = (start + end) / 2;
			distance = Math.abs(x - gun[mid]) + y;
			if(distance <= L) {
				return true;
			}
			
			if(x == gun[mid]) {
				return false;
			}
			
			// 앞/뒤
			if(x < gun[mid]) {
				end = mid - 1;
			}
			else if(x > gun[mid]) {
				start = mid + 1;
			}
		}
		
		return false;
	}

}
