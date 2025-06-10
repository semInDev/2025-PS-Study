import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        List<String> list = new ArrayList<>();
        char str = words[0].charAt(words[0].length()-1);
        list.add(words[0]);
        for (int i=1; i<words.length; i++) {
            if (list.contains(words[i]) || str != words[i].charAt(0)) {
                answer[0] = (i % n) + 1;
                answer[1] = (i / n) + 1;
                break;
            }
            list.add(words[i]);
            str = words[i].charAt(words[i].length()-1);
        }

        return answer;
    }
}
