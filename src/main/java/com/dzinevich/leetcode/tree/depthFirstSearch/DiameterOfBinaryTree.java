package com.dzinevich.leetcode.tree.depthFirstSearch;

/**
 * Given the root of a binary tree,
 * return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 *
 * This path may or may not pass through the root.
 *
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 * Input: root = [1,2,3,4,5]
 *       (1)
 *      /   \
 *    (2)   (3)
 *   /   \
 * (4)   (5)
 *
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Input: root = [1,2]
 * Output: 1
 */
public class DiameterOfBinaryTree {

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

    private static int result = 0;

    // returns the diameter (which is the result)
    public static int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return result;
    }

    // dfs returns the height !!!! not diameter !!!
    // we're going to update the global variable result
    private static int dfs(TreeNode currNode) {

        if (currNode == null) {
            // return height.
            // height of an empty tree is -1
            // it is not 0, because height 0 is for a single node (imagine just a circle with a value)
            return -1;
        }

        // recursively find the height of the left subtree and right subtree
        int left = dfs(currNode.left);
        int right = dfs(currNode.right);

        // find diameter of the current root that we're traversing and update result if it's the biggest value so far
        // how do we find diameter? H = 2 + L + R
        result = Math.max(result, 2 + left + right);


        // find the height of the current node
        //
        //     (..)
        //    /   \          height is 2
        //  (L)   (R)
        //         \
        //         (Rn)
        //
        // the L and R have their own height
        //
        // for L this will be 1 + (-1) = 0
        // for R this will be 1 + Rn = 1 + 0 = 1
        // for Rn this will be 1 + (-1) = 0
        //
        // for root it will be 1 + max(L,R) = 1 + max(0, 1) = 1 + 1 = 2

        return 1 + Math.max(left, right);
    }



    public static void main(String[] args) {
        TreeNode root =
                new TreeNode(1,
                        new TreeNode(2,
                                new TreeNode(4, null, null),
                                new TreeNode(5, null, null)),
                        new TreeNode(3, null, null)
                );

        System.out.println(diameterOfBinaryTree(root) + " -> should be 3");
        System.out.println("----repeat----");
        System.out.println(new DiameterOfBinaryTree().findDiameter(root) + " -> should be 3");
    }



    private int diameter = 0;

    private int findDiameter(TreeNode root) {
        heightDfs(root);
        return diameter;
    }

    private int heightDfs(TreeNode currNode) {
        if(currNode == null) {
            return -1;
        }

        int leftHeight = heightDfs(currNode.left);
        int rightHeight = heightDfs(currNode.right);

        //update the diameter before moving recursively to the next node
        int newDiameter = 2 + leftHeight + rightHeight;
        diameter = Math.max(diameter, newDiameter);

        //return the height of the left or the right node, the bigger one
        // + plus the edge from root to left or right node
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
