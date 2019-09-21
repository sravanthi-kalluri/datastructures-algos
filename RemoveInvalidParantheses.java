class Solution {
    public List<String> removeInvalidParentheses(String s) {
        final List<String> results = new ArrayList<>();
        if (s.equals("")) {
            results.add("");
            return results;
        }
        
        Stack<Character> stack = new Stack<Character>();
        List<Integer> invalidpositions = new ArrayList<>();
        List<Integer> closedpositions = new ArrayList<>();
        
        for (int i = 0 ; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push('(');
            }
            
            if (ch == ')') {
                closedpositions.add(i);
                if (stack.empty()) {
                    invalidpositions.add(i);
                    continue;
                }
                
                char peeked = stack.peek();
                if (peeked == '(') {
                    stack.pop();
                }
            
            }
        
        }
        
        if (stack.size() > 0) {
            s = s.substring(0, s.length() - stack.size());
        }
        
    
        for (Integer pos: invalidpositions) {
            for (Integer closedpos: closedpositions) {
                if (closedpos < pos -1) {
                    String newString = s.substring(0, closedpos) + s.substring(closedpos+1, s.length());
                    results.add(newString);
                }
            }
            
            s = s.substring(0, pos) + s.substring(pos+1, s.length());
            results.add(s);
        }
        
        if (!results.contains(s)) {
            results.add(s);
        }
        return results;
    }
}