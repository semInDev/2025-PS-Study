import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, mp, mf, ms, mv;
    static int[][] arr;
    static int min = Integer.MAX_VALUE;
    static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());

        arr = new int[N][5];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0, 0, 0, 0, 0, 0, new ArrayList<>());

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
            for (int idx : answer) {
                System.out.print(idx + " ");
            }
        }
    }

    static void DFS(int index, int tp, int tf, int ts, int tv, int cost, List<Integer> selected) {
        if (tp >= mp && tf >= mf && ts >= ms && tv >= mv) {
            if (cost < min) {
                min = cost;
                answer = new ArrayList<>(selected);
            } else if (cost == min) {
                if (small(selected, answer)) {
                    answer = new ArrayList<>(selected);
                }
            }
        }

        if (index == N) return;

        selected.add(index + 1); // 1-based index
        DFS(index + 1, tp + arr[index][0], tf + arr[index][1], ts + arr[index][2],  tv + arr[index][3], cost + arr[index][4], selected);
        selected.remove(selected.size() - 1);

        DFS(index + 1, tp, tf, ts, tv, cost, selected);
    }

    static boolean small(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }
        return a.size() < b.size();
    }
}
