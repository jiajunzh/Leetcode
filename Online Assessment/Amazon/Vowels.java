public class Vowels {

    /**
     * cause we can remove 2 substrings, so actually, the structure of string can represented as 
     * -vowels-removed-vowels-removed-volwels
     * so this reduced to the problem to find the longest substring of vowels
     */
    public int longestVowels(String s) {
        int i = 0, j = s.length() - 1;
        while (i <= j && isVowel(s.charAt(i))) i++;
        while (i <= j && isVowel(s.charAt(j))) j--;
        
        int sideLength = s.length() - 1 - j + i;
        int length = 0;
        int longest = 0;
        if (i > j) return sideLength;
        for (int start = i + 1; start < j; start++) {
            if (isVowel(s.charAt(start))) {
                length++;
            } else {
                longest = Math.max(longest, length);
                length = 0;
            }
        }
        return longest + sideLength;
    }

    public boolean isVowel(char c) {
        char lowerCase = new String().valueOf(c).toLowerCase().charAt(0);
        return lowerCase == 'a' || lowerCase == 'i' || lowerCase == 'u' || lowerCase == 'e' || lowerCase == 'o';
    }
    
    public static void main(String[] args) {
        String s1 = "earthproblem";
        String s2 = "letgosomewhere";
        String s3 = "yyyy";
        String s4 = "aaaaaaaaaaa";
        String s5 = "aaaaaayaaaaa";
        String s6 = "";
        String s7 = "AaaayAyaAyy";
        Vowels test = new Vowels();
        System.out.println(test.longestVowels(s1));
        System.out.println(test.longestVowels(s2));
        System.out.println(test.longestVowels(s3));
        System.out.println(test.longestVowels(s4));
        System.out.println(test.longestVowels(s5));
        System.out.println(test.longestVowels(s6));
        System.out.println(test.longestVowels(s7));
    }
}
