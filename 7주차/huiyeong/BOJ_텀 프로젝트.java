import java.io.*;
import java.util.*;

public class Main {
    static int[] student;      
    static boolean[] visited;  
    static boolean[] finished; 
    static int count;         

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            student = new int[n + 1];
            visited = new boolean[n + 1];
            finished = new boolean[n + 1];
            count = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                student[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }

            System.out.println(n - count); 
        }
    }

    static void dfs(int current) {
        visited[current] = true;
        int next = student[current];

        if (!visited[next]) {
            dfs(next);
        } else if (!finished[next]) {
            int temp = next;
            while (temp != current) {
                count++;
                temp = student[temp];
            }
            count++; 
        }

        finished[current] = true;
    }
}
