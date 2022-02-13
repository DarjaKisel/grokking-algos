package com.dzinevich.leetcode.tree;

// O(logn) - we're not visiting all nodes
public class LowestCommonAncestorOfBinarySearchTree {

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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;

        while (curr != null) {
            if (p.val > curr.val && q.val > curr.val) { // both are in the right subtree
                curr = curr.right;
            } else if (p.val < curr.val && q.val < curr.val) { // both are in the left subtree
                curr = curr.left;
            } else {
                return curr;
            }
        }

        return curr;
    }

    public static void main(String[] args) {
        LowestCommonAncestorOfBinarySearchTree L = new LowestCommonAncestorOfBinarySearchTree();

        TreeNode t1 =
                new TreeNode(6,
                        new TreeNode(2,
                                new TreeNode(0),
                                new TreeNode(4,
                                        new TreeNode(3),
                                        new TreeNode(5))),
                        new TreeNode(8,
                                new TreeNode(7),
                                new TreeNode(9)));
        System.out.println(L.lowestCommonAncestor(t1, new TreeNode(2), new TreeNode(8)).val + " : ex 6");

        System.out.println(L.lowestCommonAncestor(t1, new TreeNode(2), new TreeNode(4)).val + " : ex 2");
    }

}
