package com.dzinevich.leetcode.strings;

import java.util.*;

/**
 * 1. Imagine you are given a real file system, how will you search files? DFS or BFS?
 * 2. If the file content is very large (GB level), how will you modify your solution?
 * 3. If you can only read the file by 1kb each time, how will you modify your solution?
 * 4. What is the time complexity of your modified solution? What is the most time-consuming part and memory-consuming part of it? How to optimize?
 * 5. How to make sure the duplicated files you find are not false positive?
 *
 * 1. BFS can be used for great concurrency. (many files in the same directory , neighbors)
 * Also, seek time would be greatly reduced as the files are co-located.
 * Whereas DFS would be requiring a lock on root node, if you are simultaneous processing the contents.
 *
 * 2. For very large files we should do the following comparisons in this order:
 *
 * - compare sizes, if not equal, then files are different and stop here!
 * - hash them with a fast algorithm e.g. MD5 or use SHA256 (no collisions found yet), if not equal then stop here!
 * - compare byte by byte to avoid false positives due to collisions.
 *
 * 3. If we can read only, 1Kb at a time, we can still use checksum for the blocks and calculate till the point they differ.
 * May be read 0.5 kb from file 1 and 0.5 kb from file2 to check if they differ.
 *
 * 4. Time Complexity:
 *
 * Runtime - Worst case (which is very unlikely to happen): O(N^2 * L) where L is the size of the maximum bytes that need to be compared
 * Space - Worst case: all files are hashed and inserted in the hashmap, so O(H^2*L), H is the hash code size and L is the filename size
 *
 * 5. Should consider reading the whole file content byte to byte.
 */
public class DuplicateFileInSystem {

    // TIME = O(n*s.length)  where s.length is average length of the string
    public List<List<String>> findDuplicate(String[] paths) {

        Map<String, List<String>> contentWithPathes = new HashMap<>();

        for (String s : paths) { // O(n)

            String[] pathAndFiles = s.split(" ");
            String path = pathAndFiles[0];

            for (int i = 1; i < pathAndFiles.length; i++) { // O(pathAndFiles.length)

                String string = pathAndFiles[i];
                String content = string.substring(string.indexOf('(') + 1, string.indexOf(')')); // substr(inc, exc)
                String filename = string.substring(0, string.indexOf('('));

                List<String> pathes = contentWithPathes.getOrDefault(content, new ArrayList<>());
                pathes.add(path + "/" + filename);
                contentWithPathes.put(content, pathes);
            }

        }

        List<List<String>> values = new ArrayList<>(contentWithPathes.values());
        values.removeIf(value -> value.size() < 2);

        return values;
    }

    public static void main(String[] args) {
        DuplicateFileInSystem du = new DuplicateFileInSystem();

        System.out.println(
                du.findDuplicate(new String[]{
                        "root/a 1.txt(abcd) 2.txt(efgh)",
                        "root/c 3.txt(abcd)",
                        "root/c/d 4.txt(efgh)",
                        "root 4.txt(efgh)"
                }));
    }
}
