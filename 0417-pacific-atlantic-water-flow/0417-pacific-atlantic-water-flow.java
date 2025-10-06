class Solution {
    static int[][] dirs={{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        Queue<int[]> q1 = new ArrayDeque<>();
        Queue<int[]> q2 = new ArrayDeque<>();
        int n=heights.length, m=heights[0].length;
        boolean[][] vis1 = new boolean[n][m];
        boolean[][] vis2 = new boolean[n][m];
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i=0; i<n; i++) {
            q1.add(new int[]{i, 0});
            vis1[i][0] = true;
            q2.add(new int[]{i, m-1});
            vis2[i][m-1] = true;
        }

        for(int j=0; j<m; j++) {
            q1.add(new int[]{0, j});
            vis1[0][j] = true;
            q2.add(new int[]{n-1, j});
            vis2[n-1][j] = true;
        }
        bfs(q1, vis1, heights, n, m);
        bfs(q2, vis2, heights, n, m);

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(vis1[i][j] && vis2[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
    
        return result;
    }

    public boolean isValid(int i, int j, int n, int m) {
        if(i>=0 && i<n &&
            j>=0 && j<m)
            return true;
        return false;
    }

    public void bfs(Queue<int[]> q, boolean[][] vis, int[][] heights, int n, int m) {
        while(!q.isEmpty()) {
            int[] item= q.poll();
            for(int[] dir:dirs) {
                if(isValid(item[0]+dir[0], item[1]+dir[1], n, m) && !vis[item[0]+dir[0]][item[1]+dir[1]] && heights[item[0]+dir[0]][item[1]+dir[1]]>=heights[item[0]][item[1]]) {
                    vis[item[0]+dir[0]][item[1]+dir[1]]=true;
                    q.add(new int[]{item[0]+dir[0],item[1]+dir[1]});
                }
            }
        }
    }
}