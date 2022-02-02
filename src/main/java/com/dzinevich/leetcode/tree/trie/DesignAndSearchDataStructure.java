package com.dzinevich.leetcode.tree.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 211
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
 * word may contain dots '.' where dots can be matched with any letter.
 */
public class DesignAndSearchDataStructure {
    //
    // PREFIX TRIE with DFS search BACKTRACKING
    // example - [bad, dad, mad]
    //        ()
    //      / | \
    //   (b) (d) (m)
    //    /   |   \
    //  (a)  (a)  (a)
    //   /    |    \
    // (d)   (d)   (d)
    //
    //basically it's a trie with 26 possible branches, every single one may have more 26 branches, etc

    private static class WordDictionary {
        private final TrieNode root;
        private String word;

        public WordDictionary() {
            root = new TrieNode();
        }

        // let's say we have
        //
        //             __(d)__(a)__(m)
        //            |__(l)__(e)__(x)
        // (root)___(a)    \__(l)
        //           \
        //            (p)__(p)__(l)__(e)
        //
        public void addWord(String word) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (!curr.children.containsKey(c)) {
                    curr.children.put(c, new TrieNode());
                }
                curr = curr.children.get(c);
            }
            curr.isEndOfWord = true;
        }

        // we search for "all"
        // curr.children.containsKey(a) - yes
        // curr = (a)__(l)__(e)__(x)
        //              \__(l)
        // curr.children.containsKey(l) - yes
        // curr = (l)__(e)__(x)
        //         \__(l)
        // curr.children.containsKey(l) - yes
        // return curr.isEndOfWord - true

        // we search for "a..."
        // curr.children.containsKey(a) - yes
        //           __(d)__(a)__(m)
        //          |__(l)__(e)__(x)
        // curr = (a)    \__(l)
        //          \
        //          (p)__(p)__(l)__(e)

        // if (c == '.') - yes
        // for (child : curr.children.values())
        //
        // child: values
        //  (d) : [(a)__(m)] -  1 child
        //  (l) : [(e)__(x), (l)] -  2 children
        //  (p) : [(p)__(l)__(e)] -  1 child

        // curr = (a)__(m)
        // if (c == '.') - yes
        // for (child : curr.children.values())
        // (a) : [(m)] -  1 child
        // curr = (m)
        // if (c == '.') - yes
        // for (child : curr.children.values())
        // (m) : [ ]
        // return curr.isEndOfWord - true

        public boolean search(String word) {
            this.word = word;

            return dfs(0, root);
        }

        public boolean dfs(int j, TrieNode currNode) {
            TrieNode curr = currNode;

            for (int i = j; i < word.length(); i++) {
                char c = word.charAt(i);

                if (c == '.') {
                    for (TrieNode child : curr.children.values()) {
                        if (dfs(i+1, child)) {
                            return true;
                        }
                    }
                    return false;

                } else {
                    if (!curr.children.containsKey(c)) {
                        return false;
                    } else {
                        curr = curr.children.get(c);
                    }
                }
            }
            return curr.isEndOfWord;
        }
    }

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>(26);

        // isEndOfWord is true if the node represents the end of a word
        boolean isEndOfWord;

        TrieNode() {
            isEndOfWord = false;
        }
    }

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */
    public static void main(String[] args) {
        WordDictionary dict = new WordDictionary();
        dict.addWord("adam");
        dict.addWord("alex");
        dict.addWord("all");
        dict.addWord("apple");
        System.out.println(dict.search("a...") + " : should return true");

        System.out.println(dict.search("all") + " : should return true");

        System.out.println(dict.search("aley") + " : should return false");
    }
}
