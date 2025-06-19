import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int a = scores[0][0];
        int b = scores[0][1];
        
        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });
        
        // 인센티브 대상 제외
        int maxB = scores[0][1];
        for (int i=1;i<scores.length;i++) {
            if (scores[i][1] < maxB) {
                if (scores[i][0] == a && scores[i][1] == b) {
                    return -1;
                }
                scores[i][0] = -1;
                scores[i][1] = -1;
            } else {
                maxB = scores[i][1];
            }
        }
        
        // 순위 구하기
        int rank = 1;
        for (int i=0;i<scores.length;i++) {
            if (scores[i][0] == -1 && scores[i][1] == -1) {
                continue;
            }
            if (scores[i][0] == a && scores[i][1] == b) {
                continue;
            }
            int totalScore = scores[i][0]+scores[i][1];
            if (totalScore > a + b) {
                rank++;
            }
        }
        
        return rank;
    }
}
