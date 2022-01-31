package com.dzinevich.leetcode.tree.depthFirstSearch;

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * Example of a VALID TREE:
 *
 *     (2)
 *   /    \
 * (1)    (3)
 *
 */
public class ValidateBinarySearchTree {

    public static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;

        TreeNode(){}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        return valid(root, null, null);
    }

    public boolean valid(TreeNode treeNode, Integer leftBoundary, Integer rightBoundary) {
        if (treeNode == null) {
            return true;
        }

        if (!(treeNode.val < rightBoundary && treeNode.val > leftBoundary)) {
            return false;
        }

        return valid(treeNode.left, leftBoundary, treeNode.val) && valid(treeNode.right, treeNode.val, rightBoundary);
    }


    public static void main(String[] args) {
        TreeNode root =
                new TreeNode(5,
                        new TreeNode(3),
                        new TreeNode(7,
                                new TreeNode(4, null, null),
                                new TreeNode(8, null, null)
                        )
                );

        System.out.println(new ValidateBinarySearchTree().isValidBST(root) + " => should be false");

        TreeNode root1 =
                new TreeNode(5,
                        new TreeNode(3),
                        new TreeNode(7,
                                new TreeNode(6, null, null),
                                new TreeNode(9, null, null)
                        )
                );

        System.out.println(new ValidateBinarySearchTree().isValidBST(root1) + " => should be true");
    }
}
