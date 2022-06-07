package problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 1. Problem 
 * This is an interactive problem.
 *
 * You are given an array of unique strings wordlist where wordlist[i] is 6 letters long, and one word in this list is
 * chosen as secret.
 *
 * You may call Master.guess(word) to guess a word. The guessed word should have type string and must be from the
 * original list with 6 lowercase letters.
 *
 * This function returns an integer type, representing the number of exact matches (value and position) of your guess to 
 * the secret word. Also, if your guess is not in the given wordlist, it will return -1 instead.
 *
 * For each test case, you have exactly 10 guesses to guess the word. At the end of any number of calls, if you have
 * made 10 or fewer calls to Master.guess and at least one of these guesses was secret, then you pass the test case.
 *
 * 2. Examples
 * Example 1
 * Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"], numguesses = 10
 * Output: You guessed the secret word correctly.
 * Explanation:
 * master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
 * master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
 * master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
 * master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
 * master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
 * We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
 * 
 * Example 2
 * Input: secret = "hamada", wordlist = ["hamada","khaled"], numguesses = 10
 * Output: You guessed the secret word correctly.
 *
 * 3. Constraints
 * 1 <= wordlist.length <= 100
 * wordlist[i].length == 6
 * wordlist[i] consist of lowercase English letters.
 * All the strings of wordlist are unique.
 * secret exists in wordlist.
 * numguesses == 10
 */
public class GuessTheWord {

  interface Master { 
    public int guess(String word);
  }
  
  /**
   * 1. Approach 
   * Pruning Search Space Candidates. We already know that the word must appear in the given search space wordlist.
   * Assume we have already known the secret word A and made a guess using another word B, then it is guaranteed that 
   * the returned integer will be equals to Similarity(A, B), meaning the final answer will be in one of the words in
   * list that has the same number of matched chars as B.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param wordlist
   * @param master
   */
  public void findSecretWord1(String[] wordlist, Master master) {
    final Random random = new Random();

    for (int i = 0; i < 10; i++) {
      int randomInt = random.nextInt(wordlist.length);
      String guessWord = wordlist[randomInt];
      int guess = master.guess(guessWord);
      if (guess == 6) return;

      final List<String> candidates = new ArrayList<>();
      for (String word : wordlist) {
        int match = getMatches(word, guessWord);
        if (match == guess) {
          candidates.add(word);
        }
      }
      wordlist = candidates.toArray(new String[0]);
    }
  }

  /**
   * 1. Approach 
   * A better optimization is add manipulation when selecting the guess word. For example, we could maintain a frequency 
   * map of each character in each position. The higher frequency of the character, the higher score that the candidate
   * word will get. By selecting the word with maximum scores, we could get the word with the most similarity to the 
   * whole candidate family, thus having a higher chance of pruning a bigger candidate family.
   * 
   * Reference https://leetcode.com/problems/guess-the-word/discuss/652210/Clean-Java-Solution-with-explanation-of-MinMax.
   *
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param wordlist
   * @param master
   */
  public void findSecretWord2(String[] wordlist, Master master) {
    for (int i = 0; i < 10; i++) {
      String guessWord = getGuessWordWithMaxScore(wordlist);
      int guess = master.guess(guessWord);
      if (guess == 6) return;

      final List<String> candidates = new ArrayList<>();
      for (String word : wordlist) {
        int match = getMatches(word, guessWord);
        if (match == guess) {
          candidates.add(word);
        }
      }
      wordlist = candidates.toArray(new String[0]);
    }
  }

  private String getGuessWordWithMaxScore(String[] wordlist) {
    final int[][] frequency = new int[6][26];

    for (String word : wordlist) {
      for (int i = 0; i < 6; i++) {
        char c = word.charAt(i);
        frequency[i][c - 'a']++;
      }
    }

    String guessWord = null;
    int maxScore = 0;
    for (String word : wordlist) {
      int score = 0;
      for (int i = 0; i < 6; i++) {
        char c = word.charAt(i);
        score += frequency[i][c - 'a'];
      }
      if (score > maxScore) {
        maxScore = score;
        guessWord = word;
      }
    }

    return guessWord;
  }
  
  private int getMatches(String target, String word) {
    int match = 0;

    for (int i = 0; i < 6; i++) {
      if (target.charAt(i) == word.charAt(i)) {
        match++;
      }
    }

    return match;
  }
}
