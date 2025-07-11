import java.util.*;

class Solution {
    public int[][] solution(int[][] nodeinfo) {
        Node[] nodes = new Node[nodeinfo.length];
        for (int i=0;i<nodeinfo.length;i++) {
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i+1);
        }
        
        Arrays.sort(nodes);
        
        Node root = nodes[0];
        for (int i=1;i<nodes.length;i++) {
            initTree(root, nodes[i]);
        }
        
        List<Node> preorderNodes = new ArrayList<>();
        preorder(preorderNodes, root);
        
        List<Node> postorderNodes = new ArrayList<>();
        postorder(postorderNodes, root);
        
        int[][] answer = new int[2][2];
        answer[0] = preorderNodes.stream().mapToInt(node -> node.v).toArray();
        answer[1] = postorderNodes.stream().mapToInt(node -> node.v).toArray();
        
        return answer;
    }
    
    public void initTree(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                initTree(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                initTree(parent.right, child);
            }
        }
    }
    
    public void preorder(List<Node> searchNodes, Node node) {
        if (node == null) {
            return;
        }
        
        searchNodes.add(node);
        preorder(searchNodes, node.left);
        preorder(searchNodes, node.right);
    }
    
    public void postorder(List<Node> searchNodes, Node node) {
        if (node == null) {
            return;
        }
        
        postorder(searchNodes, node.left);
        postorder(searchNodes, node.right);
        searchNodes.add(node);
    }
}

class Node implements Comparable<Node> {
    int x, y, v;
    Node left, right;
    
    public Node(int x, int y, int v) {
        this.x = x;
        this.y = y;
        this.v = v;
    }
    
    @Override
    public int compareTo(Node o) {
        if (this.y == o.y) {
            return this.x - o.x;
        }
        return o.y - this.y;
    }
}
