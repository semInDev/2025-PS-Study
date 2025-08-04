class Solution {
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            int k = n%3;
            if (k==0) {
                k=4;
                n--;
            }
            sb.insert(0, k);
            n /= 3;
        }
        return sb.toString();
    }
}
