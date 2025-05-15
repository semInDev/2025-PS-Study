class Solution {
    public int solution(String s) {
        int answer = s.length();
		
        for (int i = 1; i <= s.length() / 2; i++) {
            int len = 1;
            String str = s.substring(0, i);
            StringBuilder sb = new StringBuilder();

            for (int j = i; j <= s.length(); j += i) {
                String nxt = s.substring(j, (j + i > s.length()) ? s.length() : j + i);
                
                if (str.equals(nxt)) {
                    len++;
                } else {
                    sb.append((len != 1 ? len : "") + str);
                    str = nxt;
                    len = 1;
                }
            }

            sb.append(str);
            answer = Math.min(answer, sb.length());
        }
        
        return answer;
    }
}
