import java.util.*;
class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int totalPicks = (picks[0] + picks[1] + picks[2]) * 5;

        List<int[]> groups = new ArrayList<>();
        for (int i = 0; i < Math.min(minerals.length, totalPicks); i += 5) {
            int[] count = new int[3];
            for (int j = i; j < i + 5 && j < minerals.length; j++) {
                if (minerals[j].equals("diamond")) count[0]++;
                else if (minerals[j].equals("iron")) count[1]++;
                else count[2]++;
            }
            groups.add(count);
        }
        
        groups.sort((a, b) -> 
            (b[0] * 25 + b[1] * 5 + b[2]) - (a[0] * 25 + a[1] * 5 + a[2])
        );

        for (int[] group : groups) {
            if (picks[0] > 0) { 
                picks[0]--;
                answer += group[0] + group[1] + group[2];
            } else if (picks[1] > 0) { 
                picks[1]--;
                answer += group[0] * 5 + group[1] + group[2];
            } else if (picks[2] > 0) { 
                picks[2]--;
                answer += group[0] * 25 + group[1] * 5 + group[2];
            } else {
                break; 
            }
        }

        return answer;
    }
}
