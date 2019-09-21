https://leetcode.com/problems/count-different-palindromic-subsequences/submissions/

class Solution {
    
    public int countPalindromicSubsequences(String S) {
        int strlen = S.length();
        int[][] dp = new int[strlen+1][strlen+1];
        
        for (int i = 0; i < strlen; i++) {
            // every single character is a palindrome
            dp[i][i] = 1;
        }
            
        for (int L=2; L <= strlen; L++) {
            for (int i = 0; i < strlen; i++) {
                int k = L - i -1; 
                if (k+1 < strlen && k >= 0) {
                    System.out.println(i + " " + k + " " + S.charAt(i) + " " + S.charAt(k));
                    if (S.charAt(i) == S.charAt(k)) {
                        dp[i+1][k+1] = dp[i+1+1][k+1] + dp[i+1][k-1+1] +1;
                    } else {
                        dp[i+1][k+1] = dp[i+1+1][k+1] + dp[i+1][k-1+1] - dp[i+1+1][k-1+1];
                    }
                }
            }
        }  
          
        for (int i = 0; i < strlen+1; i++) {
             for (int j = 0; j < strlen+1; j++)  {
                 System.out.print(dp[i][j] + " ");
             }
            // every single character is a palindrome
            System.out.println();
        }
        
        return dp[1][strlen];    
        
    }    
}

//     Set<String> palSeqs = new HashSet<>();
//     public int countPalindromicSubsequences(String S) {
//         if (S.length() == 0) {
//             return 0;
//         }
        
//         if (S.length() == 1) {
//             return 1;
//         }
        
//         findSubSequence(S, "");
        
//         Set<String> palindromes = new HashSet<>();
        
//         for (String s : palSeqs) {
//             if (isPalindrome(s)) {
//                 palindromes.add(s);
//             }
//         }
//         return palindromes.size()-1;
//     }
    
//     void findSubSequence(String S, String currStr) {
//         if (S.length() == 0) {
//             palSeqs.add(currStr);   
//             return;
//         }
        
//         findSubSequence(S.substring(1), currStr);
//         Set<String> newStrs = new HashSet<>();
        
//         for (String s : palSeqs) {
//             String x = S.charAt(0) + s;
//             newStrs.add(x);
//         }
        
//         palSeqs.addAll(newStrs);
        
//         System.out.println(palSeqs);
//         //findSubSequence(S.substring(1), currStr);
//     }
    
//     boolean isPalindrome(String s) {
//         char[] strchars = s.toCharArray();
//         for (int i = 0 ; i < s.length()/2; i++) {
//             if (strchars[i] != strchars[s.length()-i-1]) {
//                 return false;
//             }
//         }
//         return true;
//     }