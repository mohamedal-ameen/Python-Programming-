class Solution {
public:
    int minimumPushes(string word) {
        vector<int> freq(26, 0);
        for (char c : word) {
            freq[c - 'a']++;
        }

        // Sort frequency in descending order
        sort(freq.rbegin(), freq.rend());

        int totalPushes = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0) break;
            
            // i / 8 determines the push multiplier (1st, 2nd, 3rd, or 4th push position)
            int pushesPerChar = (i / 8) + 1;
            totalPushes += freq[i] * pushesPerChar;
        }

        return totalPushes;
    }
};