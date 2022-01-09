import java.util.Arrays;
import java.util.List;

public class MaximumAverageSubtree {
    private double max = 0.0;
    private Node maxNode = new Node(-1, null); // return -1 if no node found
    public static class Node {
        public int val;
        public List<Node> children;
        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public Node maximumAverageSubtree(Node root) {
        helper(root);
        return maxNode;
    }
    
    public int[] helper(Node root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        
        int sum = root.val;
        int num = 1;
        
        if (root.children != null) {
            for (Node children: root.children) {
                int[] childInfo = helper(children);
                sum += childInfo[0];
                num += childInfo[1];
            }
        }
        
        double average = (double) sum / (double) num;
        
        if (num > 0) { // decided by the definition of the subtree may be num > 1 ? ?
            if (average > max) {
                max = average;
                maxNode = root;
            }
            
        }
        
        return new int[]{sum, num};
    }

    public static void main(String[] args) {
        Node n1 = new Node(11, null);
        Node n2 = new Node(2, null);
        Node n3 = new Node(3, null);
        Node n4 = new Node(12, Arrays.asList(n1,n2,n3));
        Node n5 = new Node(15, null);
        Node n6 = new Node(8, null);
        Node n7 = new Node(18, Arrays.asList(n6,n5));
        Node n8 = new Node(20, Arrays.asList(n4,n7));
        MaximumAverageSubtree test = new MaximumAverageSubtree();
        //System.out.println(test.maximumAverageSubtree(n8).val);
        System.out.println(test.maximumAverageSubtree(null).val);
    }
    
}
