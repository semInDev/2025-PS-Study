import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int minCost = 987654321;
    static String answer = "";
    static int[] standard;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        standard = new int[4];

        String[] mins = br.readLine().split(" ");
        for(int i=0; i<4; i++) {
            standard[i] = Integer.parseInt(mins[i]);
        }

        arr = new int[N+1][5];
        for(int i=1; i<=N; i++){
        	mins = br.readLine().split(" ");
            for(int j=0; j<5; j++) {
                arr[i][j] = Integer.parseInt(mins[j]);
            }
        }

        dfs("", 1, 0, 0, 0, 0, 0);

        if(answer.isEmpty()) {
            System.out.println(-1);
        } else {
            System.out.println(minCost);
            System.out.println(answer.trim());
        }
    }

    public static void dfs(String comb, int i, int p, int f, int s, int v, int c) {
        if(c>=minCost) {
            return;
        }
        if(p >= standard[0] && f >= standard[1] && s >= standard[2] && v >= standard[3]) {
            minCost = c;
            answer = comb;
            return;
        }

        if(i>N){
            return;
        }

        dfs(comb + " " + i, i+1, p+arr[i][0], f+arr[i][1], s+arr[i][2], v+arr[i][3], c+arr[i][4]);
        dfs(comb, i+1, p, f, s, v, c);
    }
}