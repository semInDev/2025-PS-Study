import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int HAS_TEAM_CNT;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            HAS_TEAM_CNT = 0;
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n + 1];
            boolean[] visited = new boolean[n + 1];
            boolean[] hasTeam = new boolean[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                if (i == arr[i]) {
                    hasTeam[i] = true;
                    HAS_TEAM_CNT++;
                }
            }

            for (int i = 1; i <= n; i++) {
                if (!hasTeam[i]) {
                    dfs(visited, hasTeam, arr, i);
                }
            }

            int result = n - HAS_TEAM_CNT;
            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }

    public static void dfs(boolean[] visited, boolean[] hasTeam, int[] arr, int node) {
        if (visited[node]) {
            hasTeam[node] = true;
            HAS_TEAM_CNT++;
        } else {
            visited[node] = true;
        }

        if (!hasTeam[arr[node]]) {
            dfs(visited, hasTeam, arr, arr[node]);
        }

        hasTeam[node] = true;
        visited[node] = false;
    }
}

// 모든 학생이 동일 팀 가능
// 함께 하고 싶은 팀원 한명만 선택, 자기자신 가능


// 방문 여부 체크해야 하는 이유?
// 각 노드 체크 시 무한 루프 방지

// 팀 완성 여부 체크하는 방식
// 결국, 모든 탐색이 끝났을 때 자기 자신을 가리킨다면 (즉, 사이클을 형성한다면) 팀 결성한거임.
// 따라서, 이미 본인은 방문했는데 다시 본인 차례가 오는거면 사이클을 형성한거고, 이는 팀을 결성한거임. -> 팀 결성 여부 체크 및 팀 결성 인원 수 증가

// 마지막에 팀 결성 여부 체크해야 하는 이유?
// 이미 팀 결성한 노드에 대해서도 불필요한 판단 로직이 수행되면 시간 초과 발생. 이를 방지하기 위함
