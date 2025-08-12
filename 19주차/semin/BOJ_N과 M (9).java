import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
    		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
	    	StringBuilder sb = new StringBuilder();
		
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        
        int[] output = new int[M];
        boolean[] visited = new boolean[N];
        
        backtracking(N, M, 0, nums, output, visited, sb);
        
        System.out.print(sb);
        
	}
	
	private static void backtracking(int N, int M, int depth, int[] nums, int[] output, boolean[] visited, StringBuilder sb) {
	    	if(depth == M) {
		    	for(int num : output) {
			    	sb.append(num).append(" ");
	    		}
		    	sb.append("\n");
		    	return;
	    	}
		
		    for(int i=0; i<N; i++) {
		    	if(i != 0 && nums[i-1] == nums[i] && !visited[i-1]) continue;
			
	    		if(visited[i]) continue;
			
		    	visited[i] = true;
		    	output[depth] = nums[i];
		    	backtracking(N, M, depth+1, nums, output, visited, sb);
		    	visited[i] = false;
	      }
	}

}
