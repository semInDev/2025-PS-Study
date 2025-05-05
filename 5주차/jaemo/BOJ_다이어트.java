import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] BASE_NUTRIENTS = new int[4];
    static int MIN_PRICE = Integer.MAX_VALUE;
    static List<String> ANSWER_SELECTED = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            BASE_NUTRIENTS[i] = Integer.parseInt(st.nextToken());
        }
        int[][] info = new int[N][5];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            dfs(info, new ArrayList<>(), i, 0);
        }

        if (MIN_PRICE == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            Collections.sort(ANSWER_SELECTED);
            System.out.println(MIN_PRICE);
            System.out.println(ANSWER_SELECTED.get(0));
        }
    }

    public static void dfs(int[][] info, List<Integer> selected, int selectCnt, int depth) {
        if (selected.size() == selectCnt) {
            checkNutrients(info, selected, selectCnt);
            return;
        }

        for (Integer i = depth; i < N; i++) {
            selected.add(i);
            dfs(info, selected, selectCnt, i + 1);
            selected.remove(i);
        }
    }

    private static void checkNutrients(int[][] info, List<Integer> selected, int selectCnt) {
        int[] nutrients = new int[4];
        int price = 0;

        for (int i = 0; i < selectCnt; i++) {
            int selectedNum = selected.get(i);
            for (int j = 0; j < 4; j++) {
                nutrients[j] += info[selectedNum][j];
            }
            price += info[selectedNum][4];
        }


        for (int i = 0; i < 4; i++) {
            if (BASE_NUTRIENTS[i] > nutrients[i]){
                return;
            }
        }

        if (MIN_PRICE >= price) {
            if (MIN_PRICE > price) {
                ANSWER_SELECTED.clear();
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < selectCnt; i++) {
                sb.append(selected.get(i) + 1).append(" ");
            }
            ANSWER_SELECTED.add(sb.toString());
            MIN_PRICE = price;
        }
    }
}

// 같은 비용이면 사전순 가장 빠른 집합 출력
// 조건 만족 X -> -1 출력
