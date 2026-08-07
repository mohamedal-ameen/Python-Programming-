import java.util.*;

class Solution {
    private int[][] digitPrimes;

    public String smallestNumber(String num, long t) {
        // Step 1: Prime factorize t into powers of 2, 3, 5, 7
        int[] req = new int[10];
        long tempT = t;
        int[] primes = {2, 3, 5, 7};

        for (int p : primes) {
            while (tempT % p == 0) {
                req[p]++;
                tempT /= p;
            }
        }

        // If t has any prime factor other than 2, 3, 5, 7, no valid product exists
        if (tempT > 1) {
            return "-1";
        }

        // Step 2: Precompute prime factor contributions for digits '1'..'9'
        digitPrimes = new int[10][10];
        for (int d = 1; d <= 9; d++) {
            int val = d;
            for (int p : primes) {
                while (val % p == 0) {
                    digitPrimes[d][p]++;
                    val /= p;
                }
            }
        }

        int n = num.length();
        int firstZero = num.indexOf('0');

        // Step 3: Check if num itself (with no '0') is already divisible by t
        if (firstZero == -1) {
            int[] curr = new int[10];
            for (int i = 0; i < n; i++) {
                int d = num.charAt(i) - '0';
                for (int p : primes) {
                    curr[p] += digitPrimes[d][p];
                }
            }
            if (isSatisfied(curr, req)) {
                return num;
            }
        }

        // Step 4: Precompute prefix prime factor counts
        int[][] prefixPrimes = new int[n + 1][10];
        for (int i = 0; i < n; i++) {
            for (int p : primes) {
                prefixPrimes[i + 1][p] = prefixPrimes[i][p];
            }
            char ch = num.charAt(i);
            if (ch != '0') {
                int d = ch - '0';
                for (int p : primes) {
                    prefixPrimes[i + 1][p] += digitPrimes[d][p];
                }
            }
        }

        // Search limit up to firstZero (inclusive)
        int limit = (firstZero != -1) ? firstZero : n - 1;

        // Step 5: Try replacing position i with a larger valid digit
        for (int i = limit; i >= 0; i--) {
            int startDigit = (firstZero != -1 && i == firstZero) ? 1 : (num.charAt(i) - '0' + 1);

            for (int d = startDigit; d <= 9; d++) {
                int[] needed = new int[10];
                for (int p : primes) {
                    needed[p] = Math.max(0, req[p] - prefixPrimes[i][p] - digitPrimes[d][p]);
                }

                List<Integer> minDigits = getMinDigits(needed);
                int availSpace = n - 1 - i;

                if (minDigits.size() <= availSpace) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num.substring(0, i));
                    sb.append(d);

                    int ones = availSpace - minDigits.size();
                    for (int k = 0; k < ones; k++) {
                        sb.append('1');
                    }
                    for (int digit : minDigits) {
                        sb.append(digit);
                    }
                    return sb.toString();
                }
            }
        }

        // Step 6: If length n is insufficient, extend length to n + 1 (or min necessary length)
        List<Integer> minDigits = getMinDigits(req);
        int targetLen = Math.max(n + 1, minDigits.size());
        StringBuilder sb = new StringBuilder();

        int ones = targetLen - minDigits.size();
        for (int k = 0; k < ones; k++) {
            sb.append('1');
        }
        for (int digit : minDigits) {
            sb.append(digit);
        }

        return sb.toString();
    }

    private boolean isSatisfied(int[] curr, int[] req) {
        return curr[2] >= req[2] && curr[3] >= req[3] && curr[5] >= req[5] && curr[7] >= req[7];
    }

    private List<Integer> getMinDigits(int[] primes) {
        int c2 = Math.max(0, primes[2]);
        int c3 = Math.max(0, primes[3]);
        int c5 = Math.max(0, primes[5]);
        int c7 = Math.max(0, primes[7]);

        int minLen = getMinLen(c2, c3, c5, c7);

        List<Integer> res = new ArrayList<>();
        int remLen = minLen;

        for (int len = 0; len < minLen; len++) {
            remLen--;
            for (int d = 2; d <= 9; d++) {
                int p2 = digitPrimes[d][2];
                int p3 = digitPrimes[d][3];
                int p5 = digitPrimes[d][5];
                int p7 = digitPrimes[d][7];

                int n2 = Math.max(0, c2 - p2);
                int n3 = Math.max(0, c3 - p3);
                int n5 = Math.max(0, c5 - p5);
                int n7 = Math.max(0, c7 - p7);

                if (getMinLen(n2, n3, n5, n7) <= remLen) {
                    res.add(d);
                    c2 = n2;
                    c3 = n3;
                    c5 = n5;
                    c7 = n7;
                    break;
                }
            }
        }
        return res;
    }

    private int getMinLen(int c2, int c3, int c5, int c7) {
        int count = c5 + c7;
        int c8 = c2 / 3; c2 %= 3;
        int c9 = c3 / 2; c3 %= 2;
        int c4 = c2 / 2; c2 %= 2;
        int c6 = 0;
        if (c2 == 1 && c3 == 1) {
            c2 = 0;
            c3 = 0;
            c6 = 1;
        }
        return count + c8 + c9 + c4 + c6 + c2 + c3;
    }
}