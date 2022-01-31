package com.dzinevich.leetcode.tree.breadthFirstSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the level order traversal of its nodes' values.
 * (i.e., from left to right, level by level).
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 *
 * Input: root = [1]
 * Output: [[1]]
 *
 * Input: root = []
 * Output: []
 */
public class TreeLevelOrderTraversal {

    static class TreeNode{
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


    // O(N) complex and O(N) memory
    // we use queue FIFO
    //
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            int levelItems = queue.size(); // we need to take size before looping
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelItems; i++) { // go through 1 level at a time

                TreeNode node = queue.poll();
                if (node != null) {
                    level.add(node.val);

                    queue.add(node.left);
                    queue.add(node.right);
                }
            }

            if (!level.isEmpty()) {
                result.add(level);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20,
                        new TreeNode(15),
                        new TreeNode(7)));
        System.out.println(new TreeLevelOrderTraversal().levelOrder(root) +
                " : should be [3],[9,20],[15,7]");
    }
}
