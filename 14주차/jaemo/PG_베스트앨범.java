import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genreTotalPlays = new HashMap<>();
        Map<String, Queue<Music>> map = new HashMap<>();
        for (int i=0;i<genres.length;i++) {
            String genre = genres[i];
            Music music = new Music(i, plays[i]);
            if (map.containsKey(genre)) {
                map.get(genre).offer(music);
                genreTotalPlays.put(genre, genreTotalPlays.get(genre) + plays[i]);
            } else {
                Queue<Music> pq = new PriorityQueue<>();
                pq.offer(music);
                map.put(genre, pq);
                genreTotalPlays.put(genre, plays[i]);
            }
        }
        
        Queue<Genre> sortedGenres = new PriorityQueue<>();
        for (String genre : genreTotalPlays.keySet()) {
            sortedGenres.offer(new Genre(genre, genreTotalPlays.get(genre)));
        }
        
        List<Integer> answer = new ArrayList<>();
        while (!sortedGenres.isEmpty()) {
            Genre genre = sortedGenres.poll();
            Queue<Music> musics = map.get(genre.name);
            int cnt = 0;
            while (!musics.isEmpty() && cnt < 2) {
                answer.add(musics.poll().id);
                cnt++;
            }
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}

class Genre implements Comparable<Genre> {
    String name;
    int maxPlays;
    
    public Genre(String name, int maxPlays) {
        this.name = name;
        this.maxPlays = maxPlays;
    }
    
    @Override
    public int compareTo(Genre o) {
        return o.maxPlays - this.maxPlays;
    }
}

class Music implements Comparable<Music> {
    int id, plays;
    
    public Music(int id, int plays) {
        this.id = id;
        this.plays = plays;
    }
    
    @Override
    public int compareTo(Music o) {
        if (this.plays == o.plays) {
            return this.id - o.id;
        }
        return o.plays - this.plays;
    }
}
