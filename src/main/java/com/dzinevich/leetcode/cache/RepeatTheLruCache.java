package com.dzinevich.leetcode.cache;

import java.util.HashMap;
import java.util.Map;

public class RepeatTheLruCache {

    private static class Node {
        final int key;
        final int value;

        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;

    //dummy nodes
    private final Node lru = new Node(0,0);
    private final Node mru = new Node(0,0);

    private final Map<Integer, Node> cache = new HashMap<>();

    public RepeatTheLruCache(int capacity) {
        this.capacity = capacity;

        //connect dummy nodes
        lru.next = mru;
        mru.prev = lru;
    }

    //helpers

    // HINT need to 2 steps
    // 1 <-> 3
    // 1 <-> 2 step1
    // 2 <-> 3 step2
    //always insert at the left of MRU!!!
    private void insert(Node nodeToInsert) {
        var prev = mru.prev;
        var next = mru;
        //insert between them

        prev.next = nodeToInsert;
        nodeToInsert.prev = prev;

        next.prev = nodeToInsert;
        nodeToInsert.next = next;
    }

    //remove is just change pointers
    // HINT need 1 step
    // 1 <-> 2 <-> 3
    // 1 <-> 3
    private void remove(Node nodeToRemove) {
        var prev = nodeToRemove.prev;
        var next = nodeToRemove.next;

        prev.next = next;
        next.prev = prev;
    }

    public int get(int key) {
        if (cache.get(key) != null) {
            // don't forget to remove the old pointer of that node
            remove(cache.get(key));
            //update the MRU with this key
            insert(cache.get(key));

            return cache.get(key).value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        //what if there's already such a key??
        if (cache.get(key) != null) {
            remove(cache.get(key)); //REMOVE ONLY FROM LINKED LIST
            // AT THE END WE'll REMOVE FROM CACHE TOO
        }

        Node newNode = new Node(key, value);
        //DON'T FORGET THAT WE OPERATE 2 THINGS: 1-hashmap, 2-linkedlist
        cache.put(key, newNode);
        insert(cache.get(key));

        if (cache.size() > capacity) {
            //DON'T FORGET THAT WE OPERATE 2 THINGS: 1-hashmap, 2-linkedlist
            cache.remove(lru.next.key); //1
            remove(lru.next); //2
        }
    }
}
