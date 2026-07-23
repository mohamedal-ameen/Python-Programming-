class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        
        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            // Expand for odd length palindromes (center is at i)
            int len1 = expandAroundCenter(s, i, i);
            // Expand for even length palindromes (center is between i and i + 1)
            int len2 = expandAroundCenter(s, i, i + 1);
            
            int maxLen = Math.max(len1, len2);
            
            // Update maximum palindrome bounds
            if (maxLen > end - start) {
                start = i - (maxLen - 1) / 2;
                end = i + maxLen / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Length of the palindrome found
        return right - left - 1;
    }
}