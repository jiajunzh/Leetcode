public class DistanceBetweenNodeBST {
    class TreeNode {
        private int val;
        private TreeNode left; 
        private TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
    public int bstDistance(int[] nums, int node1, int node2) {
        TreeNode root = buildBST(nums, node1, node2);
        if (root == null) return -1;
        if (node1 > node2) {
            int tmp = node1;
            node1 = node2;
            node2 = tmp;
        }
        TreeNode lca = findLCA(root, node1, node2);
        return distance(lca, node1) + distance(lca, node2);
    }
    
    public int distance(TreeNode root, int node) {
        if (root == null) return -1;
        if (root.val == node) return 0;
        else if (root.val < node) return 1 + distance(root.right, node);
        else return 1 + distance(root.left, node);
    }
    
    public TreeNode findLCA(TreeNode root, int node1, int node2) {
        if (root == null) return null;
        if (root.val < node1) return findLCA(root.right, node1, node2);
        if (root.val > node2) return findLCA(root.left, node1, node2);
        return root;
    }
    
    public TreeNode buildBST(int[] nums, int node1, int node2) {
        TreeNode root = null;
        boolean existed1 = false;
        boolean existed2 = false;
        
        for (int num : nums) {
            if (num == node1) existed1 = true;
            if (num == node2) existed2 = true;
            
            TreeNode newNode = new TreeNode(num);
            if (root == null) {
                root = newNode;
                continue;
            }
            
            insertIntoBST(root, newNode);
        }
        return existed1 && existed2? root : null;
    }

    public TreeNode insertIntoBST(TreeNode root, TreeNode newNode) {
        if (root == null) return newNode;
        if (root.val > newNode.val) {
            root.left = insertIntoBST(root.left, newNode);
        } else if (root.val < newNode.val) {
            root.right = insertIntoBST(root.right, newNode);
        } 
        return root;
    }
    
    public static void main(String[] args) {
        DistanceBetweenNodeBST test = new DistanceBetweenNodeBST();
        int[][] nums = new int[][]{{2, 1, 3}, {2, 1, 3, 4, 5}, {2, 1, 3, 4, 5}, {1,3,2,5,4}};
        int[] node1 = new int[]{1, 1, 1, 1};
        int[] node2 = new int[]{3, 5, 6, 2};
        for (int i = 0; i < nums.length; i++) {
            System.out.println(test.bstDistance(nums[i], node1[i], node2[i]));
        }
    }
}
