package com.dzinevich.leetcode.fastAndSlowPointers;

/**
 * Given the head of a singly linked list, return true if it is a palindrome.
 */
public class PalindromeLinkedListReverseSecondHalf {

    /**
     * PROPER SOLUTION WITH TWO POINTERS
     */
    private static boolean isPalindrome(ListNode head) {
        var fast = head;
        var slow = head;

        // find middle -> which is going to be a slow pointer.
        // since it moves twice slower it will be at the middle of the list when the fast on will be at the end of it)
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            //fast moves twice faster than slow pointer
            slow = slow.next;
        }

        //reverse the second half of the list
        ListNode current = slow; // current is 3-2-1
        ListNode previous = null; // previous is null
        ListNode next; // just a storage, we need to know which one to process next

        while (current != null) {
            next = current.next; // before any manipulations store (2) as next to visit
            current.next = previous; // REVERT ARROW HERE!! .. null <- 3  2 -> 1
            previous = current; // we processed (3), it becomes previous
            current = next; // move on to the next one
        }

        // actually check if it's a palindrome
        var left = head;
        var right = previous;

        while (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            } else {
                left = left.next;
                right = right.next;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("IS IT A PALINDROME? -- " + isPalindromeRepeat(
                new ListNode(1, new ListNode(2, new ListNode(3,
                        new ListNode(3, new ListNode(2, new ListNode(1)))))))
        );
    }


    private static boolean isPalindromeRepeat(ListNode head) {

        // move fast pointer to the end and slow pointer to the middle
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // revert the second half which is the slow
        ListNode current = slow;
        ListNode previous = null;
        ListNode next = null; // just a storage to visit next

        while (current != null) {
            next = current.next;
            current.next = previous; // revert the curr-->next to prev<--curr
            previous = current;
            current = next;
        }

        // actually check if it's a palindrome
        ListNode left = head;
        ListNode right = previous;
        while (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            } else {
                left = left.next;
                right = right.next;
            }
        }

        return true;
    }
}
