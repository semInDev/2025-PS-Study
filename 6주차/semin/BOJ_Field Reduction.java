import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        int[][] points = new int[N][2];
        int[][] sortedByX = new int[N][2];
        int[][] sortedByY = new int[N][2];
        TreeMap<Integer, Integer> xCount = new TreeMap<>();
        TreeMap<Integer, Integer> yCount = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            points[i][0] = x;
            points[i][1] = y;
            xCount.put(x, xCount.getOrDefault(x, 0) + 1);
            yCount.put(y, yCount.getOrDefault(y, 0) + 1);
            sortedByX[i][0] = x;
            sortedByX[i][1] = i;
            sortedByY[i][0] = y;
            sortedByY[i][1] = i;
        }

        Arrays.sort(sortedByX, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(sortedByY, Comparator.comparingInt(a -> a[0]));

        Set<Integer> candidateIndices = new HashSet<>();
        for (int i = 0; i < 3 && i < N; i++) {
            candidateIndices.add(sortedByX[i][1]);
            candidateIndices.add(sortedByY[i][1]);
            candidateIndices.add(sortedByX[N - 1 - i][1]);
            candidateIndices.add(sortedByY[N - 1 - i][1]);
        }

        List<Integer> candidates = new ArrayList<>(candidateIndices);
        int minArea = Integer.MAX_VALUE;

        for (int i = 0; i < candidates.size(); i++) {
            for (int j = i + 1; j < candidates.size(); j++) {
                for (int k = j + 1; k < candidates.size(); k++) {
                    int a = candidates.get(i), b = candidates.get(j), c = candidates.get(k);

                    decreaseCount(xCount, points[a][0]);
                    decreaseCount(xCount, points[b][0]);
                    decreaseCount(xCount, points[c][0]);
                    decreaseCount(yCount, points[a][1]);
                    decreaseCount(yCount, points[b][1]);
                    decreaseCount(yCount, points[c][1]);

                    int width = xCount.lastKey() - xCount.firstKey();
                    int height = yCount.lastKey() - yCount.firstKey();
                    minArea = Math.min(minArea, width * height);

                    increaseCount(xCount, points[a][0]);
                    increaseCount(xCount, points[b][0]);
                    increaseCount(xCount, points[c][0]);
                    increaseCount(yCount, points[a][1]);
                    increaseCount(yCount, points[b][1]);
                    increaseCount(yCount, points[c][1]);
                }
            }
        }

        System.out.println(minArea);
    }

    private static void decreaseCount(TreeMap<Integer, Integer> map, int key) {
        map.put(key, map.get(key) - 1);
        if (map.get(key) == 0) map.remove(key);
    }

    private static void increaseCount(TreeMap<Integer, Integer> map, int key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
    }
}
