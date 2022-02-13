package com.dzinevich.leetcode.tree.depthFirstSearch;


public class LowestCommonAncestorOfBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    TreeNode p;
    TreeNode q;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.p = p;
        this.q = q;

        return dfs(root);
    }

    // O(logn) - we're not visiting all nodes
    public TreeNode dfs(TreeNode curr) {
        if (curr == null) { // leaf base case
            return null;
        }
        // 1. found a node, one node
        if (curr.val == p.val || curr.val == q.val) {
            return curr;
        }
        // 2. didn't find a node, search
        TreeNode left = dfs(curr.left);
        TreeNode right = dfs(curr.right);

        // 3. one of them could be not null, or both

        // both vere found under the same current
        if (left !=null && right != null) {
            return curr;
        }

        // both are in separate sides
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        LowestCommonAncestorOfBinaryTree L = new LowestCommonAncestorOfBinaryTree();

        TreeNode t1 =
                new TreeNode(3,
                        new TreeNode(5,
                                new TreeNode(6),
                                new TreeNode(2,
                                        new TreeNode(7),
                                        new TreeNode(4))),
                        new TreeNode(1,
                                new TreeNode(0),
                                new TreeNode(8)));
        System.out.println(L.lowestCommonAncestor(t1, new TreeNode(5), new TreeNode(1)).val + " : ex 3");

        System.out.println(L.lowestCommonAncestor(t1, new TreeNode(5), new TreeNode(4)).val + " : ex 5");
    }

}
