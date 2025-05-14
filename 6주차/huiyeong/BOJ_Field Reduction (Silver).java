import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> set = new HashSet<>();

        Integer[] idx = new Integer[N];
        for (int i = 0; i < N; i++) idx[i] = i;
        Arrays.sort(idx, Comparator.comparingInt(i -> arr[i][0]));
        for (int i = 0; i < 3; i++) {
            set.add(idx[i]);
            set.add(idx[N - 1 - i]);
        }

        Arrays.sort(idx, Comparator.comparingInt(i -> arr[i][1]));
        for (int i = 0; i < 3; i++) {
            set.add(idx[i]);
            set.add(idx[N - 1 - i]);
        }

        List<Integer> list = new ArrayList<>(set);
        int answer = Integer.MAX_VALUE;

        int size = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                for (int k = j + 1; k < size; k++) {
                    Set<Integer> remove = Set.of(list.get(i), list.get(j), list.get(k));
                    answer = Math.min(answer, area(remove));
                }
            }
        }

        System.out.println(answer);
    }

    static int area(Set<Integer> remove) {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            if (remove.contains(i)) continue;
            minX = Math.min(minX, arr[i][0]);
            maxX = Math.max(maxX, arr[i][0]);
            minY = Math.min(minY, arr[i][1]);
            maxY = Math.max(maxY, arr[i][1]);
        }

        return (maxX - minX) * (maxY - minY);
    }
}
