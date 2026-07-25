#include <string>
#include <algorithm>
#include <vector>

class Solution {
public:
    int maxProduct(int n) {
        std::string s = std::to_string(n);
        std::sort(s.begin(), s.end());
        
        int len = s.length();
        int max1 = s[len - 1] - '0';
        int max2 = s[len - 2] - '0';
        
        return max1 * max2;
    }
};