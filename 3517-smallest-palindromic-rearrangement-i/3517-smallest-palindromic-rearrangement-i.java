import java.util.Arrays;

class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        int halfLen = n / 2;
        
        // Extract the first half of characters
        char[] half = s.substring(0, halfLen).toCharArray();
        
        // Sort to make the first half lexicographically smallest
        Arrays.sort(half);
        
        String firstHalf = new String(half);
        String secondHalf = new StringBuilder(firstHalf).reverse().toString();
        
        // Handle odd vs even length strings
        if (n % 2 == 1) {
            return firstHalf + s.charAt(halfLen) + secondHalf;
        } else {
            return firstHalf + secondHalf;
        }
    }
}