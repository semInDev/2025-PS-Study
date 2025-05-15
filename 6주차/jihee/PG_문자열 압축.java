class Solution {
    public int solution(String s) {
        int len = s.length();
        int min = s.length();

        int count = 1;

        for (int i = 1; i <= len / 2; i++) {
            StringBuilder sb = new StringBuilder();
            String str = s.substring(0, i);
            for (int j = i; j <= len; j += i) {
                int end = Math.min(len, j + i);
                String tmp = s.substring(j, end);

                if (str.equals(tmp)) {
                    count++;
                } else {
                    if (count >= 2) {
                        sb.append(count);
                    }
                    sb.append(str);
                    str = tmp;
                    count = 1;
                }
            }

            if (count > 1) {
                sb.append(count);
            }

            sb.append(str);
            min = Math.min(min, sb.length());
        }
        return min;
    }
}