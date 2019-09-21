Cartesian-tree
https://leetcode.com/discuss/interview-question/124671/Facebook-or-Phone-Screen-or-Cartesian-tree

class TreeNode {
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
    int val;
    TreeNode left;
    TreeNode right;
}

class CartesianTree {

    TreeNode insert(int currVal, TreeNode root) {
        if (root == null) {
            TreeNode newroot = new TreeNode(currVal);
            return newroot;
        }
        
        if (currVal > root.val) {
            root.right = insert(currVal, root.right);
            return root;
        } else {
            TreeNode newroot = new TreeNode(currVal);
            newroot.left = root;
            return newroot;
        }
    }
}