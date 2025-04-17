import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int days = 10;

        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < want.length; i++){
            map.put(want[i], number[i]);
        }

        for(int i = 0; i < discount.length - days + 1; i++){
            Map<String, Integer> dMap = new HashMap<>();

            for(int j = 0; j < days; j++){
                dMap.put(discount[i + j], dMap.getOrDefault(discount[i + j], 0) + 1);
            }

            Boolean ischecked = true;

            for(String key : map.keySet()){
                if(map.get(key) != dMap.get(key)){
                    ischecked = false;
                    break;
                }
            }

            answer += ischecked ? 1 : 0;
        }

        return answer;
    }
}