public class Map {
    public static final int WALL = 1;
    public static final int EMPTY = 0;
    public static final int FOOD = 2;
    public static final int POWER = 3;

    private int[][] grid;

    public Map() {
        loadLevel();
    }

    private void loadLevel() {
        grid = new int[][] {
            {1,1,1,1,1,1},
            {1,0,2,0,3,1},
            {1,0,1,0,0,1},
            {1,2,0,2,0,1},
            {1,1,1,1,1,1}
        };
    }

    public int getCell(int row, int col) {
        return grid[row][col];
    }

    public void setCell(int row, int col, int value) {
        grid[row][col] = value;
    }

    public boolean isWall(int row, int col) {
        return grid[row][col] == WALL;
    }

    public boolean canMove(int row, int col) {
 
        if (row < 0 || row >= grid.length ||
            col < 0 || col >= grid[0].length) {
            return false;
        }

        return grid[row][col] != WALL;
    }

    public boolean hasFood(int row, int col) {
        return grid[row][col] == FOOD;
    }

    public boolean hasPower(int row, int col) {
        return grid[row][col] == POWER;
    }
    public void eat(int row, int col) {
        if (grid[row][col] == FOOD || grid[row][col] == POWER) {
            grid[row][col] = EMPTY;
        }
    }

    public boolean isAllFoodCleared() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == FOOD || grid[i][j] == POWER) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] getGrid() {
        return grid;
    }
}
