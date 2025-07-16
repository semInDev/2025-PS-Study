import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		while(T-->0) {
			TreeMap<Integer, Integer> Q = new TreeMap<>();
			int k = Integer.parseInt(br.readLine());
			while(k-->0) {
				st = new StringTokenizer(br.readLine());
				char cmd = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());
				
				if(cmd == 'I') {
					Q.put(num, Q.getOrDefault(num, 0) + 1);
				}else {
					if(Q.isEmpty()) {
						continue;
					}
					int key = (num == 1) ? Q.lastKey() : Q.firstKey();
                    int count = Q.get(key);
                    if (count == 1) {
                        Q.remove(key);
                    } else {
                        Q.put(key, count - 1);
                    }
				}
			}
			if(Q.isEmpty()) {
				sb.append("EMPTY").append("\n");
			}else {
				sb.append(Q.lastKey()).append(" ").append(Q.firstKey()).append("\n");
			}
		}
		System.out.print(sb);
	}
}
