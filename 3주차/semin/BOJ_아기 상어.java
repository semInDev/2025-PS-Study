import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Main {
    
    static int n;
    static int[][] graph;
    static int dx[] = {-1,1,0,0};
    static int dy[] = {0,0,-1,1};
    static ArrayList<Node> fishes;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        graph = new int[n][n];
        Queue<Node> q = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
        	StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
            	graph[i][j] = Integer.parseInt(st.nextToken());
                if(graph[i][j] == 9){
                    q.add(new Node(i, j, 0));
                    graph[i][j] = 0;
                }
            }
        }
        
        int eat = 0; int time = 0; int age = 2;
        while(true){
            LinkedList<Node> fish = new LinkedList<>();
            int[][] dist = new int[n][n];
 
            while (!q.isEmpty()) {
                Node current = q.poll();
                
                for(int i=0; i<4; i++){
                    int nx = current.x+dx[i];
                    int ny = current.y+dy[i];
                 
                    if(nx >= 0 && ny >= 0 && nx < n && ny < n && dist[nx][ny]==0 && graph[nx][ny] <= age){
                        dist[nx][ny] = dist[current.x][current.y] + 1;
                        q.add(new Node(nx, ny, dist[nx][ny]));
                        if(1 <= graph[nx][ny] && graph[nx][ny] <= 6 && graph[nx][ny] < age) fish.add(new Node(nx, ny, dist[nx][ny]));
                    }
                }
            }
 
            if(fish.size() == 0){
                System.out.println(time);
                return;
            }
 
            Node currentFish = fish.get(0);
            for(int i = 1; i < fish.size(); i++){
                if(currentFish.dist > fish.get(i).dist) {
                    currentFish = fish.get(i);
                }
                else if(currentFish.dist == fish.get(i).dist) {
                    if(currentFish.x > fish.get(i).x) currentFish = fish.get(i);
                    else if(currentFish.x == fish.get(i).x){
                        if(currentFish.y > fish.get(i).y) currentFish = fish.get(i);   
                    }
                }   
            }
             
            time += currentFish.dist;
            eat++;
            graph[currentFish.x][currentFish.y] = 0;
            if(eat == age) {
                age++;
                eat = 0;
            }
            q.add(new Node(currentFish.x, currentFish.y, 0));
        }
    }
    
    public static class Node {
        int x;
        int y;
        int dist;
        
        public Node(int x, int y, int dist) {
            super();
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}   