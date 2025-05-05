import java.util.*;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        
        for (int i=0;i<course.length;i++) {
            Map<String, Integer> courseMenuMap = new HashMap<>();
            int courseSize = course[i];
            
            for (int j=0;j<orders.length;j++) {
                String[] orderMenus = orders[j].split("");
                Arrays.sort(orderMenus);
                putCourseMenu(courseMenuMap, orderMenus, new StringBuilder(), courseSize, 0, new boolean[orderMenus.length], 0);
            }
            
            int max = 0;
            for (int value : courseMenuMap.values()) {
                max = Math.max(max, value);
            }
            
            for (String key : courseMenuMap.keySet()) {
                int val = courseMenuMap.get(key);
                if (val == max && val > 1) {
                    answer.add(key);
                }
            }
        }
    
        Collections.sort(answer);
        
        return answer.stream().toArray(String[]::new);
    }
    
    public void putCourseMenu(Map<String, Integer> courseMenuMap, String[] orderMenus, StringBuilder sb, int courseSize, int depth, boolean[] visited, int idx) {
        if (depth == courseSize) {
            String courseMenu = sb.toString();
            courseMenuMap.put(courseMenu, courseMenuMap.getOrDefault(courseMenu, 0)+1);
            return;
        }
        
        for(int i=idx; i<orderMenus.length; i++) {
            visited[i] = true;
            sb.append(orderMenus[i]);
            putCourseMenu(courseMenuMap, orderMenus, sb, courseSize, depth+1, visited, i+1);
            sb.setLength(sb.length()-1);
            visited[i] = false;
        }
    }
}

// 코스요리 메뉴는 최소 2가지 이상의 (최소 2명 이상의 손님으로부터 주문된) 단품메뉴로 구성
// 손님들의 주문 내역을 바탕으로, 코스요리 메뉴를 구성하여 문자열 형태로 배열에 담아 오름차순 정렬로 반환하기
