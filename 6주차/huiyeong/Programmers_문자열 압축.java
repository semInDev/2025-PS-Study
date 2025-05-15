class Solution {
    public int solution(String s) {
        int answer = s.length();
        for (int i=1; i<=s.length()/2; i++) {
            int count = 1;
            String str = "";
            String b = s.substring(0, i);
        
            for (int j=i; j<s.length(); j+=i) {
                String a = s.substring(j, Math.min(j + i, s.length()));
                if (a.equals(b)) 
                    count++;
                else if (count==1) 
                    str += b;
                else {
                    str += count+""+b;
                    count = 1;
                }
                b = a;
            }
            if (count==1) 
                str+=b;
            else
                str += count+""+b;
        
            answer = Math.min(answer, str.length());            
        }
        return answer;
    }
}
