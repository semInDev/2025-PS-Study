import java.util.*;

class Node implements Comparable<Node> {
    String name;
    int time;
    int duration;

    Node(String name, String time, String duration) {
        this.name = name;
        this.time = toMinute(time);
        this.duration = Integer.parseInt(duration);
    }

    private static int toMinute(String s) {
        String[] str = s.split(":");
        return Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
    }

    @Override
    public int compareTo(Node o) {
        return this.time - o.time;
    }
}
class Solution {
    public String[] solution(String[][] plans) {
        ArrayList<Node> list = new ArrayList<>();

        for (String[] p : plans) {
            list.add(new Node(p[0], p[1], p[2]));
        }
        Collections.sort(list);

        Stack<Node> stack = new Stack<>();
        List<String> answer = new ArrayList<>();

        int curTime = list.get(0).time;

        for (int i = 0; i < list.size() - 1; i++) {
            Node cur = list.get(i);
            Node next = list.get(i + 1);
            curTime = Math.max(curTime, cur.time);
            int timeLeft = next.time - curTime;

            if (cur.duration <= timeLeft) {
                curTime += cur.duration;
                answer.add(cur.name);

                while (!stack.isEmpty()) {
                    Node paused = stack.pop();
                    if (paused.duration <= next.time - curTime) {
                        curTime += paused.duration;
                        answer.add(paused.name);
                    } else {
                        paused.duration -= (next.time - curTime);
                        curTime = next.time;
                        stack.push(paused);
                        break;
                    }
                }
            } else {
                cur.duration -= timeLeft;
                curTime = next.time;
                stack.push(cur);
            }
        }

        Node last = list.get(list.size() - 1);
        curTime = Math.max(curTime, last.time);
        curTime += last.duration;
        answer.add(last.name);

        while (!stack.isEmpty()) {
            answer.add(stack.pop().name);
        }

        return answer.toArray(new String[0]);
    }
}