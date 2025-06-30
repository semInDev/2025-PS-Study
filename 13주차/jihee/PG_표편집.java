import java.util.*;

class Solution {
    public class Node {
        int pre, cur, nxt;

        public Node(int pre, int cur, int nxt) {
            this.pre = pre;
            this.cur = cur;
            this.nxt = nxt;
        }
    }

    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        int[] pre = new int[n];
        int[] next = new int[n];

        for (int i = 0; i < n; ++i) {
            pre[i] = i - 1;
            next[i] = i + 1;
        }

        next[n - 1] = -1;

        Deque<Node> dq = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder("O".repeat(n));

        for (int i = 0; i < cmd.length; ++i) {
            char type = cmd[i].charAt(0);

            if (type == 'U') {
                int num = Integer.parseInt(cmd[i].substring(2));
                while (num-- > 0) {
                    k = pre[k];
                }
            } else if (type == 'D') {
                int num = Integer.parseInt(cmd[i].substring(2));

                while (num-- > 0) {
                    k = next[k];
                }
            } else if (type == 'C') {
                dq.addFirst(new Node(pre[k], k, next[k]));
                if (pre[k] != -1) next[pre[k]] = next[k];

                if (next[k] != -1) pre[next[k]] = pre[k];

                sb.setCharAt(k, 'X');

                if (next[k] != -1) k = next[k];
                else k = pre[k];

            } else {
                Node node = dq.removeFirst();

                if (node.pre != -1) next[node.pre] = node.cur;
                if (node.nxt != -1) pre[node.nxt] = node.cur;

                sb.setCharAt(node.cur, 'O');
            }
        }

        return answer = sb.toString();
    }
}