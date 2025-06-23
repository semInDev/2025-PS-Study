import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Solution {
    static class Data implements Comparable<Data> {
        int stage;
        double fail;

        public Data(int stage, double fail) {
            this.stage = stage;
            this.fail = fail;
        }

        @Override
        public int compareTo(Data o) {
            if (this.fail == o.fail) {
                return this.stage - o.stage;
            } else if (this.fail > o.fail) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public int[] solution(int N, int[] stages) {
        int[] cur = new int[N + 2];
        int[] clear = new int[N + 1];


        for (int stage : stages) {
            for (int i = 1; i < stage; i++) {
                clear[i]++;
            }
            cur[stage]++;
        }
        ArrayList<Data> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            int total = cur[i] + clear[i];
            double failP = 0;

            if (total > 0) {
                failP = (double) cur[i] / total;
            }

            list.add(new Data(i, failP));
        }

        Collections.sort(list);

        int[] answer = new int[N];

        for (int i = 0; i < N; i++) {
            Data data = list.get(i);
            answer[i] = data.stage;
        }


        return answer;
    }
}