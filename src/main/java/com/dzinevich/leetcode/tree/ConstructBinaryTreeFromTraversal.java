package com.dzinevich.leetcode.tree;

import java.util.Arrays;

/**
 * Given two integer arrays preorder and inorder
 * where preorder is the preorder traversal of a binary tree
 * and inorder is the inorder traversal of the same tree,
 * construct and return the binary tree.
 *
 * Example:
 *      (3)
 *     /  \
 *   (9)  (20)
 *        /   \
 *      (15)  (7)
 *
 * Input:
 * preorder = [3,9,20,15,7],
 * inorder = [9,3,15,20,7]
 *
 * Output: [3,9,20,null,null,15,7]
 */
public class ConstructBinaryTreeFromTraversal {


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

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


    public TreeNode buildTree(int[] preorder, int[] inorder) { // FIRST item in PREORDER is always the ROOT


        // FIRST item in PREORDER is always the ROOT
        // at the same time this is MID value in the INORDER array
        //
        // Example: pre: [20,15,7] ino: [15,20,7].
        // preorder: 20 is the ROOT.
        // inorder: 20 is in the MIDDLE of inorder.
        // everything left from 20 is the left subtree. everything at the right is the right subtree of 20


        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);

        int mid = 0;
        while (inorder[mid] != root.val) {
            mid+=1;
        }

        // preorder = [3, 9 | 20,15,7],
        //                ^   ^
        //                1  mid+1
        // inorder = [9 | 3,15,20,7]
        //            ^   ^
        //            0   mid
        int[] preorderNewLeft = Arrays.copyOfRange(preorder, 1, mid+1);
        int[] inorderNewLeft = Arrays.copyOfRange(inorder, 0, mid);

        int[] preorderNewRight = Arrays.copyOfRange(preorder, mid+1, preorder.length);
        int[] inorderNewRight = Arrays.copyOfRange(inorder, mid+1, inorder.length);

        root.left = buildTree(preorderNewLeft, inorderNewLeft);
        root.right = buildTree(preorderNewRight, inorderNewRight);

        return root;
    }


    public static void main(String[] args) {
        var c = new ConstructBinaryTreeFromTraversal();
        var result = c.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        print(result);
    }

    private static void print(TreeNode root) {
        if (root != null) {

            System.out.print(root.val + " ");
            print(root.left);
            print(root.right);

        }
    }
}
