class Solution {
    int count=0;
    Map<Integer,HashSet<Integer>> out,in;
    public int minReorder(int n, int[][] connections) {
        
        out = new HashMap<>();
        in = new HashMap<>();
        for (int i=0;i<n;i++) {
            out.put(i,new HashSet<Integer>());
            in.put(i,new HashSet<Integer>());
        }
        for (int[] edge:connections) {
            HashSet<Integer> set =in.get(edge[1]);
            set.add(edge[0]);
            in.put(edge[1],set);
            
            set=out.get(edge[0]);
            set.add(edge[1]);
            out.put(edge[0],set);
        }
        dfs(0);
        return count;
    }
    public void dfs(int node) {
        for (int x:out.get(node)) {
            count++;
            in.get(x).remove(node);
            dfs(x);
        }
        for (int y:in.get(node)) {
            out.get(y).remove(node);
            dfs(y);
        }
    }
}

****************************

class Solution {
    public int minReorder(int n, int[][] connections) {
        List<List<Integer>> al = new ArrayList<>();
        for(int i = 0; i < n; ++i) 
            al.add(new ArrayList<>());
        for (var c : connections) {
            al.get(c[0]).add(c[1]);
            al.get(c[1]).add(-c[0]);
        }
        return dfs(al, new boolean[n], 0);
    }
    public int dfs(List<List<Integer>> al, boolean[] visited, int from) {
        int change = 0;
        visited[from] = true;
        for (var to : al.get(from))
            if (!visited[Math.abs(to)])
                change += dfs(al, visited, Math.abs(to)) + (to > 0 ? 1 : 0);
        return change;   
    }
}
