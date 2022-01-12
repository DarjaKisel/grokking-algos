package com.dzinevich.leetcode.depthFirstSearch;


public class MaxDepthOfBinaryTree {
    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int maxDepth(TreeNode root) {
        return dfs(root);
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(dfs(node.left), dfs(node.right));
    }

    public static void main(String[] args) {
        TreeNode root =
                new TreeNode(3,
                        new TreeNode(9),
                        new TreeNode(20,
                                new TreeNode(15),
                                new TreeNode(7)
                        )
                );

        System.out.println(new MaxDepthOfBinaryTree().maxDepth(root) + " should be 3");
    }


}
