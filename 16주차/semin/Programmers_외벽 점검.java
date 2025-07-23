class Solution {
    static public int[] weak_cases;
    static public int answer;

    public int solution(int n, int[] weak, int[] dist) {
        answer = Integer.MAX_VALUE;
        weak_cases = new int[weak.length * 2];
        int i = 0;
        while (i < weak.length) {
            weak_cases[i] = weak[i];
            weak_cases[i + weak.length] = weak[i++] + n;
        }

        for (int k = 0; k < weak.length; k++) {
            dfs(k, 0, dist, new boolean[dist.length], new int[dist.length]);
        }

        if (answer == Integer.MAX_VALUE) return -1;
        return answer;
    }

    public void dfs(int start, int depth, int[] dist, boolean[] visited, int[] dist_order) {
        if (depth == dist.length) {
            answer = Math.min(answer, check(start, start + weak_cases.length / 2, dist_order));
            return;
        }
        for (int i = 0; i < dist.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dist_order[depth] = dist[i];
            dfs(start, depth + 1, dist, visited, dist_order);
            visited[i] = false;
        }
    }

    public int check(int start, int end, int[] dist_order) {
        int friend = 1;
        int pos = weak_cases[start] + dist_order[friend - 1];

        for (int i = start; i < end; i++) {
            if (pos < weak_cases[i]) {
                friend++;
                if (friend > dist_order.length) return Integer.MAX_VALUE;
                pos = weak_cases[i] + dist_order[friend - 1];
            }
        }
        return friend;
    }
}
