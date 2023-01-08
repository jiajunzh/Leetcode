package problem;

/**
 * 1. Problem
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 *
 * 2. Examples
 * Example 1
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 *
 * Example 2
 * Input: s = "aa", p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 *
 * Example 3
 * Input: s = "cb", p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 *
 * 3. Constraints
 * 0 <= s.length, p.length <= 2000
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '?' or '*'.
 */
public class WildcardMatching {

    /**
     * 1. Approach
     * Dynamic Programming.
     *
     * There three possible cases to consider here:
     * - If charP is an English letter, compare it with the current char in string s
     * - If charP is ?, skip the current char in string s
     * - If charP is *, there might be two case, but could be generalized into a model such that [pre-* strings] [* matching strings] [post-* string] (The string will match only if all three parts match)
     *  - * matches zero sequence in string s
     *  - * matches one or more sequence in string s
     *
     * Define dp[i][j] as whether string[0:j - 1] will match pattern[0:i - 1], following the three cases above:
     * - pattern[i] = c => dp[i][j] = dp[i - 1][j - 1] && p[i] == s[j]
     * - pattern[i] = '?' => dp[i][j]  = dp[i - 1][j - 1]
     * - pattern[i] = '*' => For the first dp[i - 1][k] = true for 0 <= k <= lengthS, dp[i][j] = true for k <= j <= lengthS
     *
     * 2. Complexity
     * - Time O(SP)
     * - Space O(SP)
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        final int lengthS = s.length(), lengthP = p.length();
        final boolean[][] isMatch = new boolean[lengthP + 1][lengthS + 1];
        isMatch[0][0] = true;
        for (int i = 1; i <= lengthP; i++) {
            final char cp = p.charAt(i - 1);
            if (cp == '*') {
                int j = 0;
                while (j <= lengthS && !isMatch[i - 1][j]) {
                    j++;
                }
                while (j <= lengthS) {
                    isMatch[i][j] = true;
                    j++;
                }
            } else {
                for (int j = 1; j <= lengthS; j++) {
                    isMatch[i][j] = isMatch[i - 1][j - 1] && (cp == '?' || cp == s.charAt(j - 1));
                }
            }
        }
        return isMatch[lengthP][lengthS];
    }

    /**
     * 1. Approach
     * Dynamic Programming (Recursion + Memoization)
     *
     * Case to consider:
     * - indexS == s.length() && indexP == p.length() => return true
     * - indexP == p.length() && indexS != s.length() => return false;
     * - charP = '*', there are two possibles cases:
     *      (1) * match nothing at all, then it depends on dp(indexS, indexP + 1)
     *      (2) * match one or more chars, then it depends on dp(indexS + 1, indexP) => will eventually boil down to case (1)
     * - charP = '?' => depend on dp(indexS + 1, indexP + 1)
     * - charP = 'charS' => depend on dp(indexS + 1, indexP + 1)
     *
     * 2. Complexity
     * - Time O(SP)
     * - Space O(SP)
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        final int lengthS = s.length(), lengthP = p.length();
        final Boolean[][] isMatch = new Boolean[lengthS + 1][lengthP + 1];
        return isMatchHelper(isMatch, s, p, 0, 0);
    }

    private boolean isMatchHelper(Boolean[][] isMatch, String s, String p, int indexS, int indexP) {
        if (isMatch[indexS][indexP] != null) return isMatch[indexS][indexP];
        if (s.length() == indexS && p.length() == indexP) return true;
        if (p.length() == indexP) return false;

        final char cp = p.charAt(indexP);
        if (cp == '*') {
            if (s.length() == indexS) {
                isMatch[indexS][indexP] = isMatchHelper(isMatch, s, p, indexS, indexP + 1);
            } else {
                isMatch[indexS][indexP] = isMatchHelper(isMatch, s, p, indexS, indexP + 1) || isMatchHelper(isMatch, s, p, indexS + 1, indexP);
            }
        } else {
            if (s.length() == indexS) {
                isMatch[indexS][indexP] = false;
            } else {
                isMatch[indexS][indexP] = (cp == '?' || cp == s.charAt(indexS)) && isMatchHelper(isMatch, s, p, indexS + 1, indexP + 1);
            }
        }
        return isMatch[indexS][indexP];
    }

    /**
     * 1. Approach
     * Backtracking. It is not required to test out all possible cases when meeting start as below:
     * - match zero char
     * - match one char
     * - ....
     * - match all subsequent chars
     * For example, the moment when we know that the whole string could match assuming * match zero char. We could simply
     * conclude the answer without testing the other cases.
     *
     * This approach uses backtracking techniques to test out possible cases and return as soon as one case is found
     *
     * 2. Complexity
     * - Time tricky one see https://leetcode.com/problems/wildcard-matching/solutions/294659/wildcard-matching/
     * - Space O(1)
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch3(String s, String p) {
        final int lengthS = s.length(), lengthP = p.length();
        int indexS = 0, indexP = 0, starIndex = -1, sBacktrackIndex = 0;
        while (indexS < lengthS) {
            if (indexP < lengthP && (p.charAt(indexP) == '?' || p.charAt(indexP) == s.charAt(indexS))) {
                indexS++;
                indexP++;
            } else if (indexP < lengthP && p.charAt(indexP) == '*') {
                starIndex = indexP;
                sBacktrackIndex = indexS;
                indexP++;
            } else if (starIndex == -1) {
                return false;
            } else {
                indexP = starIndex + 1;
                indexS = sBacktrackIndex + 1;
                sBacktrackIndex = indexS;
            }
        }

        while (indexP < lengthP && p.charAt(indexP) == '*') {
            indexP++;
        }
        return indexP == lengthP;
    }
}
