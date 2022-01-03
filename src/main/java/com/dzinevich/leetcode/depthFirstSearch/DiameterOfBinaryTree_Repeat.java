package com.dzinevich.leetcode.depthFirstSearch;

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
public class DiameterOfBinaryTree_Repeat {

    //1 - WE SHOULD GO BOTTOM - UP
    //2 - FOR EACH LEVEL WE CALCULATE [HEIGHT and DIAMETER]
    //
    // HEIGHT = 1 + Max(L, R) -- 1 is for the edge
    // DIAMETER = 2 + L + R -- 2 is for 2 edges
    //
    // height of a leaf node is 0
    // height of a zero node is -1

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }


    private int result;

    public int findMaxDiameter(Node node) {
        dfs_for_height(node);
        return result;
    }

    // we dont' return diameter
    // we update diameter inside
    private int dfs_for_height(Node node) {
        if (node == null) {
            return -1;
        }

        var left = dfs_for_height(node.left);
        var right = dfs_for_height(node.right);

        result = Math.max(result, 2 + left + right);

        return 1 + Math.max(left, right);
    }


    public static void main(String[] args) {
        Node node =
                new Node(1,
                        new Node(2,
                                new Node(3,
                                        new Node(5), null),
                                new Node(4,
                                        null, new Node(6))),
                        null);
        System.out.println(new DiameterOfBinaryTree_Repeat().findMaxDiameter(node) + ": should be 4");

        Node root =
                new Node(1,
                        new Node(2,
                                new Node(4, null, null),
                                new Node(5, null, null)),
                        new Node(3, null, null)
                );

        System.out.println(new DiameterOfBinaryTree_Repeat().findMaxDiameter(root) + ": should be 3");
    }
}
