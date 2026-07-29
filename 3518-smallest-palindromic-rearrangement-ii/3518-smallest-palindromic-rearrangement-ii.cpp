#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
private:
    // Helper function to calculate nCr capped at CAP
    long long nCr(int n, int r, long long CAP) {
        if (r < 0 || r > n) return 0;
        if (r == 0 || r == n) return 1;
        if (r > n - r) r = n - r;
        
        long long res = 1;
        for (int i = 1; i <= r; ++i) {
            __int128 next = (__int128)res * (n - i + 1) / i;
            if (next >= CAP) return CAP;
            res = (long long)next;
        }
        return res;
    }

    // Calculates the number of unique permutations for remaining character frequencies
    long long countPermutations(const vector<int>& freq, int rem_len, long long CAP) {
        long long total = 1;
        int current_len = rem_len;
        for (int f : freq) {
            if (f == 0) continue;
            long long ways = nCr(current_len, f, CAP);
            __int128 next = (__int128)total * ways;
            if (next >= CAP) return CAP;
            total = (long long)next;
            current_len -= f;
        }
        return total;
    }

public:
    string smallestPalindrome(string s, int k) {
        int n = s.length();
        vector<int> count(26, 0);
        for (char c : s) {
            count[c - 'a']++;
        }
        
        vector<int> freq(26, 0);
        char mid = 0;
        for (int i = 0; i < 26; ++i) {
            freq[i] = count[i] / 2;
            if (count[i] % 2 != 0) {
                mid = (char)('a' + i);
            }
        }
        
        int m = n / 2;
        long long CAP = (long long)k + 1;
        
        // If total distinct palindromic permutations is less than k, return ""
        if (countPermutations(freq, m, CAP) < k) {
            return "";
        }
        
        string left = "";
        for (int i = 0; i < m; ++i) {
            for (int c = 0; c < 26; ++c) {
                if (freq[c] == 0) continue;
                
                freq[c]--;
                long long ways = countPermutations(freq, m - 1 - i, CAP);
                if (k <= ways) {
                    left += (char)('a' + c);
                    break; // Character fixed at current position
                } else {
                    k -= ways;
                    freq[c]++; // Backtrack and try next character
                }
            }
        }
        
        string right = left;
        reverse(right.begin(), right.end());
        
        if (n % 2 != 0) {
            return left + mid + right;
        } else {
            return left + right;
        }
    }
};