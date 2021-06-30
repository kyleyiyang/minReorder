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
