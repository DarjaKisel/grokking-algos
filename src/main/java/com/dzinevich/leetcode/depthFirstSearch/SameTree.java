package com.dzinevich.leetcode.depthFirstSearch;

public class SameTree {

    private static class TreeNode {
        public int va;
        public TreeNode left;
        public TreeNode righ;
        public TreeNode() {
        }
        public TreeNode(int va) {
            this.va = va;
        }
        public TreeNode(int va, TreeNode left, TreeNode righ) {
            this.va = va;
            this.left = left;
            this.righ = righ;
        }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if ((p != null && q == null) || (p == null && q != null) || p.va != q.va) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.righ, q.righ);
    }


    public static void main(String[] args) {
        TreeNode treeNodeOne = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode treeNodeTwo = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(new SameTree().isSameTree(treeNodeOne, treeNodeTwo) + " should be true");
        TreeNode treeNodeThree = new TreeNode(1, new TreeNode(12), new TreeNode(3));
        System.out.println(new SameTree().isSameTree(treeNodeOne, treeNodeThree) + " should be false");
    }
}
