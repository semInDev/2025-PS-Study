import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int L, C;
	static char[] chars, output;
	static boolean[] isVowel;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		char[] vowels = {'a', 'e', 'i', 'o', 'u'};
		
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));		
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        output = new char[L];
        
        st = new StringTokenizer(br.readLine());
        chars = new char[C];
        for (int i = 0; i < C; i++) {
            chars[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(chars);

        isVowel = new boolean[C];
        for (int i = 0; i < C; i++) {
            for (char vowel : vowels) {
                if (chars[i] == vowel) {
                    isVowel[i] = true;
                    break;
                }
            }
        }
        
        backtracking(0,-1,0);
        
        System.out.print(sb);
	}
	
	private static void backtracking(int depth, int preIdx, int vowelsCnt) {
		if(depth == L) {
			// vowelsCnt이 1개 이상이고, L-vowelsCnt이 2개 이상
			if(vowelsCnt >= 1 && L - vowelsCnt >= 2) {
				for(char c : output) {
					sb.append(c);
				}
				sb.append("\n");
			}
			return;
		}
		for(int i=preIdx+1; i<C; i++) {
			// isVowel이면 vowelsCnt 늘려서 더하기
			output[depth] = chars[i];
			if(isVowel[i]) backtracking(depth+1, i, vowelsCnt+1);
			else backtracking(depth+1, i, vowelsCnt);
		}
	}
}
