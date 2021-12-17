package com.dzinevich.leetcode.depthFirstSearch;

/**
 * Given the root of a binary tree and an integer targetSum,
 * return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 *
 * A leaf is a node with no children.
 */
public class PathSum {

    private static class TreeNode {
        int val;

        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // inorder traversal DFS recursively
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(0, root, targetSum);
    }

    private static boolean dfs(int currSum, TreeNode currNode, int targetSum) {
        if(currNode == null) {
            return false;
        }

        currSum += currNode.val;

        if (currNode.left == null && currNode.right == null) {
            return currSum == targetSum;
        }

        return dfs(currSum, currNode.left, targetSum)
                || dfs(currSum, currNode.right, targetSum);
    }

//    public static void main(String[] args) {
//        TreeNode root = new TreeNode(5,
//                new TreeNode(4,
//                        new TreeNode(11,
//                                new TreeNode(7),
//                                new TreeNode(2)),
//                        null),
//                new TreeNode(8,
//                        new TreeNode(13),
//                        new TreeNode(4, null,
//                                new TreeNode(1))));
//        System.out.println(hasPathSum(root, 22) + " : should be true");
//        System.out.println(hasPathSum(root, 1) + " : should be false");
//    }

    private static boolean hasSumPath(TreeNode root, int targetSum) {
        return recursiveInorderDfs(0, root, targetSum);
    }

    private static boolean recursiveInorderDfs(int currSum, TreeNode currNode, int targetSum) {
        if (currNode == null) {
            return false;
        }

        currSum += currNode.val;

        if (currSum == targetSum && currNode.left == null && currNode.right == null) {
            return true;
        }

        return recursiveInorderDfs(currSum, currNode.left, targetSum)
                || recursiveInorderDfs(currSum, currNode.right, targetSum);
    }

    public static void main(String[] args) {

        var root = new TreeNode(5,
                new TreeNode(4,
                        new TreeNode(11,
                                new TreeNode(7), new TreeNode(2)),
                        null),
                new TreeNode(8,
                        new TreeNode(13),
                        new TreeNode(4, null, new TreeNode(1))
                )
        );

        System.out.println(hasSumPath(root, 22) + " -> should be true");
        System.out.println(hasSumPath(root, 23) + " -> should be false");
    }
}
