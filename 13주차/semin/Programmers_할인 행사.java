import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int total = Arrays.stream(number).sum();
        if (total > discount.length) {
            return 0;
        }

        HashMap<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        HashMap<String, Integer> discountMap = new HashMap<>();
        for (int i = 0; i < total; i++) {
            discountMap.put(discount[i], discountMap.getOrDefault(discount[i], 0) + 1);
        }

        if (wantMap.equals(discountMap)) {
            answer++;
        }

        for (int i = 1; i < discount.length - total + 1; i++) {
            String removeItem = discount[i - 1];
            if (discountMap.get(removeItem) == 1) {
                discountMap.remove(removeItem);
            } else {
                discountMap.put(removeItem, discountMap.get(removeItem) - 1);
            }

            String addItem = discount[i + total - 1];
            discountMap.put(addItem, discountMap.getOrDefault(addItem, 0) + 1);

            if (wantMap.equals(discountMap)) {
                answer++;
            }
        }
        return answer;
    }
}
