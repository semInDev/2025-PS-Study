class Solution {
    public int solution(String s) {
        int answer = s.length();
        int iterCnt = 1;
        for (int i=1;i<=s.length()/2;i++) { // 자르는 단위
            String base = s.substring(0, i);
            StringBuilder sb = new StringBuilder();
            for (int j=i;j<=s.length();j+=i) {
                String now = s.substring(j, Math.min(j+i, s.length()));
                if (now.equals(base)) {
                    iterCnt++;
                } else {
                    if (iterCnt > 1) {
                        sb.append(iterCnt);
                    }
                    sb.append(base);
                    base = now;
                    iterCnt = 1;
                }
            }
            sb.append(base);
            answer = Math.min(answer, sb.toString().length());
        }
        return answer;
    }
}
