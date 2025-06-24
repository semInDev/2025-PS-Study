import java.util.*;

class Solution {
    public int solution(String dirs) {
        HashSet<String> set = new HashSet<>();
        int x=0, y=0, nx=0, ny=0;
        char current_dir;
        for(int i=0; i<dirs.length();i++){
            current_dir = dirs.charAt(i);
            if(current_dir == 'U' && y<5){
                ny++;
            }
            else if(current_dir == 'D' && y>-5){
                ny--;
            }
            else if(current_dir == 'L' && x>-5){
                nx--;
            }
            else if(current_dir == 'R' && x<5){
                nx++;
            }
            else{//-5~5범위를 넘어감
                continue;
            }
            set.add(x+"->"+nx+","+y+"->"+ny);
            set.add(nx+"->"+x+","+ny+"->"+y);
            x = nx; y = ny;
        }
        return set.size()/2;
    }
}
