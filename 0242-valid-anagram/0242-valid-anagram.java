class Solution {
    public boolean isAnagram(String s, String t) {
        // Step 1: Base check - if lengths differ, they can't be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        // Step 2: Create a frequency table for lowercase English letters
        int[] count = new int[26];

        // Step 3: Count character frequencies from both strings
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        // Step 4: Verify all counts are zero
        for (int val : count) {
            if (val != 0) {
                return false;
            }
        }

        return true;
    }
}