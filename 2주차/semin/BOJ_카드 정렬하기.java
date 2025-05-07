import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args)throws IOException {
 
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while(N-->0) {
        	int newItem = Integer.parseInt(br.readLine());
        	pq.add(newItem);
        }
        int ans = 0;
        while(pq.size() != 1) {
        	int sum = pq.poll()+pq.poll();
        	ans += sum;
        	pq.add(sum);
        }
        
        System.out.println(ans);
	}

}
