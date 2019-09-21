class Solution {
    final List<Integer> visited = new ArrayList<>();
    final Stack<Integer> stack = new Stack<Integer>();
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        final HashMap<Integer, List<Integer>> dependencyMap = new HashMap<>();
        
        // building dependency map
        for (int i=0; i<prerequisites.length; i++) { 
            final List<Integer> list = dependencyMap.get(prerequisites[i][0]);
            if(list == null) {
                List<Integer> newlist = new ArrayList<>();
                newlist.add(prerequisites[i][1]);
                dependencyMap.put(prerequisites[i][0], newlist);
            } else {
                dependencyMap.get(prerequisites[i][0]).add(prerequisites[i][1]);
            }
        }
        
        System.out.println(dependencyMap);
        
        // adding all courses to not visited 
        final List<Integer> notvisited = new ArrayList<>();
        for (int i= 0; i< numCourses; i++) {
            notvisited.add(i);
        }
        
        System.out.println("notvisited" + notvisited);
        
        // while not all nodes are visited
        while(notvisited.size() != 0) {
            // get the first non visited node 
            int firstNonVisitedNode = notvisited.get(0);
            // mark it visited and add to stack    
            visited.add(firstNonVisitedNode);
            stack.push(firstNonVisitedNode);
            
            // iterate through dependencies recursively 
            traverseDep(firstNonVisitedNode, dependencyMap);
            
            // remove all visited courses
            notvisited.removeAll(visited);
        }
        
        // in the end when all courses are visited if stack is not empty there are circular dependencies
        if (stack.size() > 0) {
            return false;
        } else {
            return true;
        }
    }
    
    void traverseDep(int curr, HashMap<Integer, List<Integer>> map) {
        // if curr has no dependecies// pop it out of stack
        if (map.get(curr) == null) {
            System.out.println("popped"+stack.pop());
            return;
        }
        
        final List<Integer> circularDep = new ArrayList<>();
        
        // for each prereq check if it is already visited
        for (int prereq : map.get(curr)) {
           // if not already visited add to visited 
           // add to stack and recurse  
           if (!visited.contains(prereq)) {
                visited.add(prereq);
                stack.push(prereq);
                traverseDep(prereq, map);
            } else {
                // if already visited check if it is still in stack
                // if still in stack circular dependency
               if (stack.contains(prereq)) {
                   circularDep.add(prereq);
               }
           }
        }
        
        // if this node has circular dependencies donot pop from stack
        if (circularDep.size() == 0){
             System.out.println("popped"+stack.pop());
        }
    }
   
}