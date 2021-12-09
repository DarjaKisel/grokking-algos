package com.dzinevich.leetcode.cache;

import java.util.HashMap;
import java.util.Map;

public class LruCache {

    /**
     * cache will be a hashmap
     * where key is an int and value is a reference to a double-linked Node
     *
     * |------|----------|---------|----------|
     * |   K  |     1    |    2    |    3     |
     * |------|----------|---------|----------|
     * |   V  |    1,10  |   2,20  |    3,30  |
     * |------|----------|---------|----------|
     *
     *
     * LRU -> [1,10] <-> [2,20] <-> [3,30] <- MRU
     *
     * the MRU item will be moved here --^ before the MRU node pointer
     */

    private static class DoubleLinkedNode {

        final int key;
        final int value;

        DoubleLinkedNode prevNode;
        DoubleLinkedNode nextNode;

        public DoubleLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, DoubleLinkedNode> hashMap = new HashMap();

    private final DoubleLinkedNode leastRecentlyUsed = new DoubleLinkedNode(0, 0); //LRU is left
    private final DoubleLinkedNode mostRecentlyUsed = new DoubleLinkedNode(0, 0); //MRU is right

    public LruCache(int capacity) {
        this.capacity = capacity;

        //initially they're connected, and we're going to put nodes between them
        leastRecentlyUsed.nextNode = mostRecentlyUsed;
        mostRecentlyUsed.prevNode = leastRecentlyUsed;
    }

    // HELPERS

    // remove from list
    private void remove(DoubleLinkedNode nodeToRemove) {
        // let's say we have [1] <-> [2] <-> [3]
        // how do we remove [2] ?
        // just connect [1] and [3] as prev and next

        var prev = nodeToRemove.prevNode;
        var next = nodeToRemove.nextNode;

        prev.nextNode = next;
        next.prevNode = prev;
    }

    //insert to the list at the right
    private void insert(DoubleLinkedNode nodeToInsert) {
        // let's say we have [1] <-> [3]
        // how do we insert [2] ?
        // just connect [1] and [2] as prev and next
        // then connect [2] and [3] as prev and next

        var prev = mostRecentlyUsed.prevNode;
        var next = mostRecentlyUsed;

        prev.nextNode = nodeToInsert;
        nodeToInsert.prevNode = prev;

        next.prevNode = nodeToInsert;
        nodeToInsert.nextNode = next;
    }

    public int get(int key) {
        if (hashMap.get(key) != null) {
            // every time we get smth, we need to update the most recent
            remove(hashMap.get(key));
            insert(hashMap.get(key));

            return hashMap.get(key).value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (hashMap.get(key) != null) {
            remove(hashMap.get(key));
        }
        DoubleLinkedNode newNode = new DoubleLinkedNode(key, value);
        hashMap.put(key, newNode);
        insert(hashMap.get(key));

        if (hashMap.size() > capacity) {
            // remove from the linked list and remove the LRU from the cache (hashmap)
            DoubleLinkedNode lru = leastRecentlyUsed.nextNode;
            remove(lru);
            hashMap.remove(lru.key);
        }
    }
}
