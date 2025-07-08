import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
        Map<String, Integer> total = new HashMap<>();
        Map<String, List<int[]>> each = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            total.put(genres[i], total.getOrDefault(genres[i], 0) + plays[i]);

            if (!each.containsKey(genres[i])) {
                each.put(genres[i], new ArrayList<>());
            }
            each.get(genres[i]).add(new int[]{i, plays[i]});
        }

        List<String> list = new ArrayList<>(total.keySet());
        list.sort((a, b) -> total.get(b) - total.get(a));

        List<Integer> result = new ArrayList<>();

        for (String genre : list) {
            List<int[]> songs = each.get(genre);

            songs.sort((a, b) -> {
                if (b[1] == a[1]) return a[0] - b[0];
                return b[1] - a[1];
            });

            result.add(songs.get(0)[0]);
            if (songs.size() > 1) result.add(songs.get(1)[0]);
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}
