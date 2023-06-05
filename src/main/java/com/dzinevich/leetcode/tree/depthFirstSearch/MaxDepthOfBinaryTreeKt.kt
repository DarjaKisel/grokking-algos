package com.dzinevich.leetcode.tree.depthFirstSearch

import java.util.*
import kotlin.math.max

class TreeNode(value: Int, var left: TreeNode? = null, var right: TreeNode? = null)

fun maxDepthDFS(root: TreeNode?): Int {
    if (root == null) return 0

    // return 1 + max(Left subtree, Right subtree)
    return 1 + max(maxDepthDFS(root.left), maxDepthDFS(root.right))
}

//if asked to solve without recursion - use BFS
// BFS uses Queue or a Deque

fun maxDepthBFS(root: TreeNode?): Int {
    if (root == null) return 0

    var level = 0
    val queue: Deque<TreeNode> = LinkedList()
    queue.add(root)
    while (queue.isNotEmpty()) {
        for (i in 0 until queue.size) {
            val node = queue.removeFirst()
            if (node.left != null) queue.add(node.left)
            if (node.right != null) queue.add(node.right)
        }
        level += 1
    }
    return level
}

fun main() {
    val root = TreeNode(3,
            left = TreeNode(9),
            right = TreeNode(20,
                    left = TreeNode(15),
                    right = TreeNode(7)
            )
    )
    println("${maxDepthDFS(root)} >>>> expected is 3")
    println("${maxDepthBFS(root)} >>>> expected is 3")

}