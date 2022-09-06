package problem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 1. Problem 
 * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
 *
 * For example, "ACGAATTCCG" is a DNA sequence.
 * When studying DNA, it is useful to identify repeated sequences within the DNA.
 *
 * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur
 * more than once in a DNA molecule. You may return the answer in any order.
 *
 * 2. Examples
 * Example 1
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC","CCCCCAAAAA"]
 * 
 * Example 2
 * Input: s = "AAAAAAAAAAAAA"
 * Output: ["AAAAAAAAAA"]
 *
 * 3. Constraints
 * 1 <= s.length <= 105
 * s[i] is either 'A', 'C', 'G', or 'T'.
 */
public class RepeatedDNASequences {

  /**
   * 1. Approach 
   * HashSet
   * 
   * 2. Complexity 
   * - Time O(L * (N - L))
   * - Space O(L * N)
   * 
   * @param s
   * @return
   */
  public List<String> findRepeatedDnaSequences(String s) {
    final int L = 10;
    final Set<String> set = new HashSet<>();
    final Set<String> result = new HashSet<>();
    for (int i = 0; i <= s.length() - L; i++) {
      final String sequence = s.substring(i, i + 10);
      if (set.contains(sequence)) {
        result.add(sequence);
      }
      set.add(sequence);
    }
    return new ArrayList<>(result);
  }

  /**
   * 1. Approach 
   * BitManipulation. A sequence like 'ACGTTAAC' could be converted to a bit string where each char takes 2 bits to 
   * be encoded => '0011223333000011'. As the length of window is 10 (which requires 20 bits to encode a sequence),
   * an integer should suffice to encode each sequence of length 10. Each time we move forward the window, we just need
   * to remove the one at the start of the sequence and then append the new one. This reduces time to O(N)
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param s
   * @return
   */
  public List<String> findRepeatedDnaSequences2(String s) {
    final int L = 10, n = s.length();
    final Set<Integer> set = new HashSet<>();
    final Set<String> result = new HashSet<>();
    final Map<Character, Integer> nucleotides = Map.of('A', 0, 'C', 1, 'G', 2, 'T', 3);
    int bitMask = 0;
    for (int i = 0; i < Math.min(n, L); i++) {
      final int label = nucleotides.get(s.charAt(i));
      bitMask <<= 2;
      bitMask |= label;
    }
    set.add(bitMask);
    for (int i = L; i < n; i++) {
      final int label = nucleotides.get(s.charAt(i));
      bitMask <<= 2; // Spare space for the new char
      bitMask |= label; // Add new char to the end of the sequence
      bitMask &= ~(3 << (2 * L)); // Remove the first two bits that represent the previous first char of the sequence 
      if (set.contains(bitMask)) {
        result.add(s.substring(i - L + 1, i + 1));
      }
      set.add(bitMask);
    }
    return new ArrayList<>(result);
  }
}
