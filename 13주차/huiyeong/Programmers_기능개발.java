import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int len = speeds.length;
        int[] arr = new int[len];
        for (int i=0; i<len; i++) {
            arr[i] = (int)Math.ceil((100.0 - progresses[i]) / speeds[i]);
        }
        
        int i = 0;
        List<Integer> list = new ArrayList<>();
        while(i<len) {
            int count = 0;
            int current = arr[i];
            while (i < len && arr[i] <= current) {
                count++;
                i++;
            }
            list.add(count);
        }
        
        int[] answer = new int[list.size()];
        for (int j = 0; j < list.size(); j++)
            answer[j] = list.get(j);
    
        return answer;
    }
}
