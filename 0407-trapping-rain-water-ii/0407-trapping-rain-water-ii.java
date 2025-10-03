class Cell {
    int h;
    int x;
    int y;

    Cell(int h, int x, int y) {
        this.h=h;
        this.x=x;
        this.y=y;
    }
}

class Solution {
    int[][] directions={{0,1}, {0,-1}, {1,0}, {-1,0}};
boolean[][] visited=new boolean[200][200];
    public boolean isValid(int i, int j, int m, int n) {
        return i>=0 && i<m && j>=0 && j<n;
    }
    public int trapRainWater(int[][] heightMap) {
        int m=heightMap.length;
        int n=heightMap[0].length;
        int ans=0;

        if(m<=2 || n<=2) {
            return 0;
        }

        PriorityQueue<Cell> pq = new PriorityQueue<Cell>((a,b) -> a.h-b.h);

        for(int j=0; j<n; j++) {
            pq.add(new Cell(heightMap[0][j], 0, j));
            visited[0][j]=true;
            pq.add(new Cell(heightMap[m-1][j], m-1, j));
            visited[m-1][j]=true;
        }
        for(int i=1; i<m-1; i++) {
            pq.add(new Cell(heightMap[i][0], i, 0));
            visited[i][0]=true;
            pq.add(new Cell(heightMap[i][n-1], i, n-1));
            visited[i][n-1]=true;
        }
        while(!pq.isEmpty()) {
            Cell ce = pq.poll();
            int h=ce.h;
            int i=ce.x;
            int j=ce.y;
            for(int[] arr:directions) {
                if(isValid(i+arr[0], j+arr[1], m, n) && !visited[i+arr[0]][j+arr[1]]) {
                    visited[i+arr[0]][j+arr[1]]=true;
                    if(h>heightMap[i+arr[0]][j+arr[1]])
                        ans+=h-heightMap[i+arr[0]][j+arr[1]];
                    Cell cell = new Cell(Math.max(heightMap[i+arr[0]][j+arr[1]], h), i+arr[0], j+arr[1]);
                    pq.add(cell);
                }
            }
        }
        return ans;
    }
}