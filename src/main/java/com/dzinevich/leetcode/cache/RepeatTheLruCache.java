package com.dzinevich.leetcode.cache;

import java.util.HashMap;
import java.util.Map;

public class RepeatTheLruCache {

    private static class DoubleLinkedNode {
        public int key;
        public int value;

        public DoubleLinkedNode prevNode;
        public DoubleLinkedNode nextNode;

        public DoubleLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final DoubleLinkedNode lru = new DoubleLinkedNode(0, 0);
    private final DoubleLinkedNode mru = new DoubleLinkedNode(0, 0);

    private final int limit;
    private final Map<Integer, DoubleLinkedNode> cache = new HashMap<>();

    public RepeatTheLruCache(int limit) {
        this.limit = limit;
        //initially they're connected
        lru.nextNode = mru;
        mru.prevNode = lru;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {

            remove(cache.get(key));
            insert(cache.get(key));

            return cache.get(key).value;
        }

        return -1;
    }

    private void insert(DoubleLinkedNode node) {
        var willBePrev = mru.prevNode;
        var willBeNext = mru;

        node.prevNode = willBePrev;
        willBePrev.nextNode = node;

        node.nextNode = willBeNext;
        willBeNext.prevNode = node;
    }

    private void remove(DoubleLinkedNode node) {
        var willBeNext = node.nextNode;
        var willBePrev = node.prevNode;

        willBePrev.nextNode = willBeNext;
        willBeNext.prevNode = willBePrev;
    }

    public void put(int key, int val) {
        DoubleLinkedNode newNode = new DoubleLinkedNode(key, val);

        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }

        cache.put(key, newNode);
        insert(newNode);

        if (cache.size() > limit) {
            var leastRecentlyUsed = lru.nextNode;
            remove(leastRecentlyUsed);
            cache.remove(leastRecentlyUsed.key);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
