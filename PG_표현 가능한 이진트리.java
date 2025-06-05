import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        List<Integer> answer = new ArrayList<>();

        for (long number : numbers) {
            if (isBinaryTree(createBinaryString(Long.toBinaryString(number)))) {
                answer.add(1);
            } else {
                answer.add(0);
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private String createBinaryString(String binaryString) {
        int length = binaryString.length();
        int nodes = 1;
        int level = 1;
        while (length > nodes) {
            level *= 2;
            nodes += level;
        }
    
        return "0".repeat(nodes-length) + binaryString;
    }
    
    private boolean isBinaryTree(String binaryString) {
        if (binaryString.length() == 0) {
            return true;
        }

        int rootNode = binaryString.length() / 2;
        String leftSubTree = binaryString.substring(0, rootNode);
        String rightSubTree = binaryString.substring(rootNode+1);

        if (binaryString.charAt(rootNode) == '0') {
            return isZeroTree(binaryString.substring(0, rootNode)) && isZeroTree(binaryString.substring(rootNode+1));
        }

        return isBinaryTree(leftSubTree) && isBinaryTree(rightSubTree);
    }
    
    private boolean isZeroTree(String binaryString) {
        if (binaryString.length() == 0) {
            return true;
        }

        int rootNode = binaryString.length() / 2;
        String leftSubTree = binaryString.substring(0, rootNode);
        String rightSubTree = binaryString.substring(rootNode + 1);
    
        if (binaryString.charAt(rootNode) == '1') {
            return false;
        }

        return isZeroTree(leftSubTree) && isZeroTree(rightSubTree);
    }
}

// 루트 노드, (자식 노드를 가진) 내부 노드는 모두 1이어야 함
// 즉, 0은 리프 노드만 가능
