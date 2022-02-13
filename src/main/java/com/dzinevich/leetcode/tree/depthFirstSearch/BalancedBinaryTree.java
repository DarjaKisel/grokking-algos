package com.dzinevich.leetcode.tree.depthFirstSearch;

/**
 * Given a binary tree, determine if it is height-balanced.
 * Aa height-balanced binary tree is defined as:
 * a binary tree in which the left and right subtrees of every node
 * differ in height by no more than 1.
 *
 * Example VALID:
 *       (3)
 *      /   \
 *    (9)   (20)
 *          /   \
 *       (15)   (7)
 *
 *  Height of 9 ==0, height of 20 == 1, diff =1
 *
 * Example INvALID:
 *          (1)
 *         /   \
 *       (2)   (2)
 *      /   \
 *    (3)   (3)
 *   /   \
 * (4)   (4)
 *
 *  Height of left 2 ==2, height of right 2 == 0, diff =2
 */
public class BalancedBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(){}
        public TreeNode(int val) {
            this.val = val;
        }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return (Math.abs(dfs(root.left) - dfs(root.right)) <= 1)
                && isBalanced(root.left)
                && isBalanced(root.right);
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(dfs(root.left), dfs(root.right));
    }

    public static void main(String[] args) {
        BalancedBinaryTree B = new BalancedBinaryTree();
        TreeNode t =
                new TreeNode(3,
                        new TreeNode(9),
                        new TreeNode(20,
                                new TreeNode(15),
                                new TreeNode(7)));
        System.out.println(B.isBalanced(t) + " : ex true");

        TreeNode t2 =
                new TreeNode(1,
                        new TreeNode(2,
                                new TreeNode(3,
                                        new TreeNode(4),
                                        new TreeNode(4)),
                                new TreeNode(3)),
                        new TreeNode(2)
                );
        System.out.println(B.isBalanced(t2) + " : ex false");
    }

}
