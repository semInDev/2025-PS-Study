import java.util.*;

class Solution {
    static HashMap<String, String> map1 = new HashMap<>();
    static HashMap<String, Integer> map2 = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        for(int i=0; i<enroll.length; i++){
            map1.put(enroll[i], referral[i]);
            map2.put(enroll[i], 0);
        }

        for(int i=0; i<seller.length; i++){
            String current = seller[i];
            int total = amount[i] * 100;
            while(total > 0 && !map1.get(current).equals("-")){
                map2.put(current, map2.get(current) + total - (total/10));
                current = map1.get(current);
                total /= 10;
            }
            map2.put(current, map2.get(current) + total - (total/10));
        }

        int[] result = new int[enroll.length];
        for(int i=0; i<result.length; i++){
            result[i] = map2.get(enroll[i]);
        }
        return result;
    }
}
