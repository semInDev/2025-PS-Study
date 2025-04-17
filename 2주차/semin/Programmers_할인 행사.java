import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int total = Arrays.stream(number).sum(); // 총 개수
        if(total > discount.length){ // 둘이 길이가 다를 때 예외처리
            return 0;
        }
        
        HashMap<String, Integer> wantMap = new HashMap<>();
        for(int i = 0; i < want.length; i++){
            wantMap.put(want[i], number[i]);
        }
        HashMap<String, Integer> discountMap = new HashMap<>();
        for(int i = 0; i < total; i++){
            if(!discountMap.isEmpty() && discountMap.containsKey(discount[i]))
                discountMap.put(discount[i], discountMap.get(discount[i])+1);
            else
                discountMap.put(discount[i], 1);
        }
        if(wantMap.equals(discountMap)){
            answer++;
        }
        
        for(int i = 1; i < discount.length - total + 1; i++){
            // 맨 앞 원소(discount[i-1]) 빼고
            if(discountMap.get(discount[i-1]) == 1)
                discountMap.remove(discount[i-1]);
            else
                discountMap.put(discount[i-1], discountMap.get(discount[i-1])-1);
                
            // 맨 뒤 원소 추가(discount[i + total-1])
            if(discountMap.containsKey(discount[i + total-1]))
                discountMap.put(discount[i + total-1], discountMap.get(discount[i + total-1])+1);
            else
                discountMap.put(discount[i + total-1], 1);
            
            if(wantMap.equals(discountMap)) answer++;
        }
        return answer;
    }
}