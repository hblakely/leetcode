import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {


    /**
     *Approach #1 Depth First Search
     *
     * Treat the 2d grid map as an undirected graph and there is an edge between two horizontally or
     * vertically adjacent nodes of value '1'.
     *
     * Linear scan the 2d grid map, if a node contains a '1', then it is a root node that triggers a
     * Depth First Search. During DFS, every visited node should be set as '0' to mark as visited node.
     * Count the number of root nodes that trigger DFS, this number would be the number of islands since each
     * DFS starting at some root identifies an island.
     *
     * Time Complexity O(M*N) where M is num rows and N is num columns
     * Space Complexity O(M*N)
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 300
     * grid[i][j] is '0' or '1'.
     */


    public static int numIslandsDFS(char[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;

        int rows = grid.length;
        int columns = grid[0].length;
        int islands = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (grid[r][c] == '1') { // Land Ho!
                    islands++;
                    // We found the island, we increment, now we recur and "mark" each touching land as visited.
                    search(grid, r, c);
                }
            }
        }
        return islands;
    }
    static void search(char[][] grid, int r, int c) {
        int rows = grid.length;
        int columns = grid[0].length;

        // base case: return if row or column are out of bounds;
        // or if current location is water/has already been visited.
        if (r < 0 || c < 0 || r >= rows || c >= columns || grid[r][c] == '0')
            return;

        grid[r][c] = '0'; // Mark as visited so we don't recur forever.
        search(grid, r - 1, c);
        search(grid, r + 1, c);
        search(grid, r, c - 1);
        search(grid, r, c + 1);
    }




    /**
     * Approach #2: Breadth First Search
     * Linear scan the 2d grid map, if a node contains a '1', then it is a root node that
     * triggers a Breadth First Search. Put it into a queue and set its value as '0' to mark as visited node.
     * Iteratively search the neighbors of enqueued nodes until the queue becomes empty.
     *
     * Time Complexity O(M*N) where M is num rows and N is num columns
     *  O(min(M*N))
     */
    public static int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;

        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    grid[r][c] = '0'; // mark as visited
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int row = id / nc;
                        int col = id % nc;
                        if (row - 1 >= 0 && grid[row-1][col] == '1') {
                            neighbors.add((row-1) * nc + col);
                            grid[row-1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row+1][col] == '1') {
                            neighbors.add((row+1) * nc + col);
                            grid[row+1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col-1] == '1') {
                            neighbors.add(row * nc + col-1);
                            grid[row][col-1] = '0';
                        }
                        if (col + 1 < nc && grid[row][col+1] == '1') {
                            neighbors.add(row * nc + col+1);
                            grid[row][col+1] = '0';
                        }
                    }
                }
            }
        }
        return num_islands;
    }

    public static void main(String[] args){
        example1();
        example2();
    }

    private static void example1(){
        /*
        Input: grid = [
              ["1","1","1","1","0"],
              ["1","1","0","1","0"],
              ["1","1","0","0","0"],
              ["0","0","0","0","0"]
        ]
            Output: 1
         */
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        System.out.println("Example1 CORRECT!: "+numIslandsDFS(grid) +" island(s) found.");
    }
    private static void example2(){
    /*
    Input: grid = [
          ["1","1","0","0","0"],
          ["1","1","0","0","0"],
          ["0","0","1","0","0"],
          ["0","0","0","1","1"]
        ]
        Output: 3
     */
        char[][] grid = {
                {'1', '1', '0', '0', '0' },
                {'1', '1', '0', '0', '0' },
                {'0', '0', '1', '0', '0' },
                {'0', '0', '0', '1', '1' }
        };
        System.out.println("Example2 CORRECT!: "+numIslandsDFS(grid) +" island(s) found.");
    }
}
