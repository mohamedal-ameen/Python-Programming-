#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
private:
    // Computes C(n, r) capped at 'cap' in O(r) time and O(1) space
    long long nCr(int n, int r, long long cap) {
        if (r < 0 || r > n) return 0;
        if (r == 0 || r == n) return 1;
        if (r > n - r) r = n - r;
        
        long long res = 1;
        for (int i = 1; i <= r; ++i) {
            res = (res * (n - i + 1)) / i;
            if (res >= cap) return cap;
        }
        return min(res, cap);
    }

public:
    string smallestPalindrome(string s, int k) {
        int n = s.length();
        int m = n / 2;

        // 1. Count character frequencies
        vector<int> full_cnt(26, 0);
        for (char c : s) {
            full_cnt[c - 'a']++;
        }

        // Half frequencies for building the first half
        vector<int> cnt(26, 0);
        char mid_char = 0;
        for (int i = 0; i < 26; ++i) {
            cnt[i] = full_cnt[i] / 2;
            if (full_cnt[i] % 2 != 0) {
                mid_char = 'a' + i;
            }
        }

        long long cap = k + 1;

        // Helper to compute total permutations of remaining counts
        auto count_permutations = [&](const vector<int>& current_cnt, int remaining_len) -> long long {
            long long ways = 1;
            int rem = remaining_len;
            for (int i = 0; i < 26; ++i) {
                if (current_cnt[i] == 0) continue;
                ways = ways * nCr(rem, current_cnt[i], cap);
                if (ways >= cap) return cap;
                rem -= current_cnt[i];
            }
            return ways;
        };

        // Check if total possible permutations < k
        if (count_permutations(cnt, m) < k) {
            return "";
        }

        // 2. Construct the first half greedily
        string prefix = "";
        prefix.reserve(m);
        int rem_len = m;

        for (int i = 0; i < m; ++i) {
            for (int c = 0; c < 26; ++c) {
                if (cnt[c] == 0) continue;

                // Try placing character c
                cnt[c]--;
                long long num_ways = count_permutations(cnt, rem_len - 1);

                if (num_ways >= k) {
                    prefix += (char)('a' + c);
                    rem_len--;
                    break; // Successfully placed character c at position i
                } else {
                    k -= num_ways;
                    cnt[c]++; // Backtrack and try next character
                }
            }
        }

        // 3. Construct the full palindrome
        string suffix = prefix;
        reverse(suffix.begin(), suffix.end());

        if (n % 2 != 0) {
            return prefix + mid_char + suffix;
        } else {
            return prefix + suffix;
        }
    }
};