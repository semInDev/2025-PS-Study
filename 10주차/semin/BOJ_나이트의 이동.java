import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	int[] dx = {-2,-2,-1,-1,1,1,2,2};
    	int[] dy = {1,-1,2,-2,2,-2,1,-1};
    	
    	BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	StringBuilder sb = new StringBuilder();
    	int t = Integer.parseInt(br.readLine());
    	while(t-->0) {
    		int I = Integer.parseInt(br.readLine());
    		int dist[][] = new int[I][I];
    		for(int i=0;i<I;i++) {
    			Arrays.fill(dist[i], -1);
    		}
    		
    		st = new StringTokenizer(br.readLine());
    		int start_x = Integer.parseInt(st.nextToken());
    		int start_y = Integer.parseInt(st.nextToken());
    		Queue<int[]> queue = new ArrayDeque<>();
    		queue.add(new int[] {start_x, start_y});
    		dist[start_x][start_y] = 0;
    		
    		st = new StringTokenizer(br.readLine());
    		int finish_x = Integer.parseInt(st.nextToken());
    		int finish_y = Integer.parseInt(st.nextToken());
    		
    		while(dist[finish_x][finish_y] == -1) {
    			int[] cur = queue.poll();
    			for(int i=0;i<8;i++) {
    				int nx = cur[0]+dx[i];
    				int ny = cur[1]+dy[i];
    				if(0<=nx && nx<I
    						&&0<=ny && ny<I
    						&& dist[nx][ny] == -1) {
    					dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
    					queue.add(new int[] {nx,ny});
    				}
    			}
    		}
    		sb.append(dist[finish_x][finish_y]+"\n");
    	}
    	System.out.print(sb);
    }
}
