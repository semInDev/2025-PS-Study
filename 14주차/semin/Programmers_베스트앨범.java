import java.util.*;

class Solution {
    private static class Music {
        int index;
        int play;
        
        public Music(int index, int play){
            this.index = index;
            this.play = play;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, PriorityQueue<Music>> map = new HashMap<>();
        Map<String, Integer> cntMap = new HashMap<>();
        
        int n = genres.length;
        
        for(int i = 0; i < n; i++){
            String genre = genres[i];
            int play = plays[i];
            cntMap.merge(genre, play, Integer::sum);
            
            if(map.containsKey(genre)){
                map.get(genre).offer(new Music(i, play));
            }
            else{
                PriorityQueue<Music> pq = new PriorityQueue<>((a,b) -> {
                        if(a.play != b.play){
                            return b.play - a.play;
                        }
                        else{
                            return a.index - b.index;
                        }
                    }
                );
                pq.offer(new Music(i, play));
                map.put(genre, pq);
            }
        }
        
        String[] res = cntMap.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .map(Map.Entry::getKey)
            .toArray(String[]::new);
        
        List<Integer> ans = new ArrayList<>();
        int index = 0;
        
        for(String s : res) {
            PriorityQueue<Music> val = map.get(s);
            
            if(!val.isEmpty()){
                ans.add(val.poll().index);
                index++;
            }
            
            if(!val.isEmpty()){
                ans.add(val.poll().index);
                index++;
            }
        }
        
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
