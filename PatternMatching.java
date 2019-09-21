boolean regularExpressionMatching(String s, String p) {
    boolean[][] dp = new boolean[p.length()+1][s.length()+1];
    char[] sarr = s.toCharArray();
    char[] parr = p.toCharArray();
    
    dp[0][0] = true;
    
    // init pattern first column
    for (int i = 1; i <= parr.length; i++) {
        if (parr[i-1] == '*') {
            dp[i][0] = true;
        } else {
            dp[i][0] = false;
        }  
    }
    
    // init string first row
    for (int i = 1; i <= sarr.length; i++) {
        dp[0][i] = false;
    }
    
    for (int i = 1; i <= parr.length; i++) {
        for (int j = 1; j <= sarr.length; j++) {
            // if string and pattern character are same. we can take value from i-1, j-1 
            if (parr[i-1] == sarr[j-1]) {
                dp[i][j] = dp[i-1][j-1];
            } else {
                // if character is not same. we cam substitute . with any character
                if (parr[i-1] == '.') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (parr[i-1] == '*') {
                    // else if pattern character is * check the character before *. 
                    // if char matches/. take value from j-1 not considering curr char
                    char ch = parr[i-1-1];
                    if (ch == sarr[j-1] || ch == '.') {
                        dp[i][j] = dp[i][j-1];
                    } else {
                        // else take value from before character before *
                        dp[i][j] = dp[i-2][j];
                    }
                }
            }
        }
    }

    return dp[parr.length][sarr.length] ;
}
