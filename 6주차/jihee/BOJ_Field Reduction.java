import java.io.*;
import java.io.IOException;
import java.util.*;

class Node {
    int x, y, idx;

    Node(int idx, int x, int y) {
        this.idx = idx;
        this.x = x;
        this.y = y;
    }
}
public class Main {
    static int N, ans = Integer.MAX_VALUE;
    static Node[] arr;
    static Node[] cow;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Node[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i] = new Node(i, x, y);
        }

        Set<Integer> set = new HashSet<>();
        Node[] xNode = arr.clone();
        Node[] yNode = arr.clone();
        Arrays.sort(xNode, Comparator.comparingInt(a -> a.x));
        Arrays.sort(yNode, Comparator.comparingInt(a -> a.y));

        for (int i = 0; i < 3; i++) {
            set.add(xNode[i].idx);
            set.add(xNode[N - 1 - i].idx);
            set.add(yNode[i].idx);
            set.add(yNode[N - 1 - i].idx);
        }


        List<Node> list = new ArrayList<>();
        for (int i : set) list.add(arr[i]);
        cow = list.toArray(new Node[0]);
        visited = new boolean[cow.length];


        recursive(0, 0);

        System.out.println(ans);
    }

    private static void recursive(int cur, int start) {
        if (cur == 3) {
            calc();
            return;
        }

        for (int i = start; i < cow.length; i++) {
            visited[i] = true;
            recursive(cur + 1, i + 1);
            visited[i] = false;
        }
    }

    private static void calc() {
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

        for (int i = 0; i < cow.length; i++) {
            if (visited[i]) continue;

            minX = Math.min(minX, cow[i].x);
            maxX = Math.max(maxX, cow[i].x);
            minY = Math.min(minY, cow[i].y);
            maxY = Math.max(maxY, cow[i].y);
        }

        for (Node node : arr) {
            boolean isDeleted = false;
            for (int i = 0; i < cow.length; i++) {
                if (visited[i] && node.idx == cow[i].idx) {
                    isDeleted = true;
                    break;
                }
            }
            if (isDeleted) continue;

            minX = Math.min(minX, node.x);
            maxX = Math.max(maxX, node.x);
            minY = Math.min(minY, node.y);
            maxY = Math.max(maxY, node.y);
        }

        ans = Math.min(ans, (maxX - minX) * (maxY - minY));
    }
}