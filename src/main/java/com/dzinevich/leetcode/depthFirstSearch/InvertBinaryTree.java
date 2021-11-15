package com.dzinevich.leetcode.depthFirstSearch;

import com.sun.source.tree.Tree;

/**
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
public class InvertBinaryTree {

    /**
     * complexity is O(n), where n is the number of nodes in the tree.
     * We cannot do better than that, since at the very least we have to visit each node to invert it.
     */
    public static TreeNode invertTree(TreeNode root) {

        //base scenario
        if (root == null) {
            return null;
        }

        TreeNode right = invertTree(root.right); //right - inverted right
        TreeNode left = invertTree(root.left); //left - inverted left

        root.left = right; // put new right sub-tree to the left of the root
        root.right = left; // put new left sub-tree to the right of the root

        return root;
    }

    public static void main(String[] args) {

        //         4          --->         4
        //      2     7       --->      7     2
        //    1  3   6  9     --->    9  6   3  1

        var one = new TreeNode(1);
        var three = new TreeNode(3);

        var six = new TreeNode(6);
        var nine = new TreeNode(9);

        var two = new TreeNode(2, one, three);
        var seven = new TreeNode(7, six, nine);

        var four = new TreeNode(4, two, seven);

        printTree(four);
        TreeNode inverted = invertTree(four);
        System.out.println();
        printTree(inverted);
    }

    public static void printTree(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.print(treeNode.val + " ");
            printTree(treeNode.left);
            printTree(treeNode.right);
        }
    }

}
