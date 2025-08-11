import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        List<String> listA = make(str1);
        List<String> listB = make(str2);

        Map<String, Integer> mapA = getCountMap(listA);
        Map<String, Integer> mapB = getCountMap(listB);

        int all = getAll(mapA, mapB);
        int union = getUnion(mapA, mapB);

        double d = (union == 0) ? 1 : (double) all / union;
        return (int) Math.floor(d * 65536);
    }

    private List<String> make(String str) {
        List<String> list = new ArrayList<>();
        str = str.toLowerCase();
        for (int i = 0; i < str.length() - 1; i++) {
            char a = str.charAt(i);
            char b = str.charAt(i + 1);
            if (Character.isLetter(a) && Character.isLetter(b)) {
                list.add("" + a + b);
            }
        }
        return list;
    }

    private Map<String, Integer> getCountMap(List<String> list) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : list) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        return map;
    }

    private int getAll(Map<String, Integer> mapA, Map<String, Integer> mapB) {
        int count = 0;
        for (String key : mapA.keySet()) {
            if (mapB.containsKey(key)) {
                count += Math.min(mapA.get(key), mapB.get(key));
            }
        }
        return count;
    }

    private int getUnion(Map<String, Integer> mapA, Map<String, Integer> mapB) {
        Set<String> keys = new HashSet<>();
        keys.addAll(mapA.keySet());
        keys.addAll(mapB.keySet());

        int count = 0;
        for (String key : keys) {
            count += Math.max(mapA.getOrDefault(key, 0), mapB.getOrDefault(key, 0));
        }
        return count;
    }
}
