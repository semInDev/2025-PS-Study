import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
 
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[][] times = new int[N][2];
        for(int i=0; i<N; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            times[i][0] = start;
            times[i][1] = end;
        }
        Arrays.sort(times, (o1, o2)->{
        	if(o1[1] == o2[1]) {
        		return o1[0]-o2[0];
        	}else {
        		return o1[1]-o2[1];
        	}
        });
        
        int cur = -1;
        int ans = 0;
        for(int i=0; i<N; i++) {
        	if(times[i][0] >= cur) {
        		cur = times[i][1];
        		ans++;
        	}
        }
        System.out.println(ans);
	}

}